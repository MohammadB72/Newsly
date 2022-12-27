package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.domain.model.*
import app.newsly.core.domain.model.enums.HeadlineType
import app.newsly.core.domain.model.enums.MarkupType
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.core.network.BuildConfig
import app.newsly.core.network.model.ContentItemApiModel
import app.newsly.core.network.model.NewsDetailApiModel
import app.newsly.core.network.model.enums.ContentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(postId: Int): Flow<RequestResult<NewsDetail>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository
                .getNewsDetail(postId = postId)
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { newsDetailApiModel ->
                    emit(RequestResult.Success(newsDetailApiModel.toNewsDetail()))
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }

    private fun NewsDetailApiModel.toNewsDetail(): NewsDetail {
        val apiContentList = this.apiContentList?.filter {
            if (BuildConfig.FLAVOR == BuildConfig.MOCK || BuildConfig.FLAVOR == BuildConfig.DEVELOPMENT) {
                true
            } else {
                it.hasValidContent
            }
        }

        return NewsDetail(
            id = this.apiId ?: -1,
            title = this.apiTitle ?: "",
            imageUrl = this.apiImageUrl,
            author = Author(
                name = this.apiAuthor?.apiName ?: "",
                avatar = this.apiAuthor?.apiAvatar ?: ""
            ),
            date = this.apiDate ?: "",
            link = this.apiLink ?: "",
            contentList = apiContentList?.toContentList() ?: emptyList()
        )
    }

    private fun List<ContentItemApiModel>.toContentList(): ArrayList<ContentItem> {
        val newContentListItem = ArrayList<ContentItem>()
        var paragraphText = ""
        val markups = ArrayList<Markup>()
        this.forEach { content ->
            content
                .doOnTextContent { markupType, text, href ->
                    markups.addMarkUp(
                        type = markupType,
                        start = paragraphText.length,
                        end = paragraphText.length + text.length,
                        href = href
                    )
                    paragraphText += text
                }
                .doOnOtherContent {
                    newContentListItem.addTextContent(
                        text = paragraphText,
                        markups = markups,
                        contentSuccessfullyAdded = {
                            paragraphText = ""
                            markups.clear()
                        })
                    if (content.apiType == ContentType.IMAGE) {
                        newContentListItem.add(ContentItemImage(url = content.apiHref ?: ""))
                    } else if (content.apiType == ContentType.H1) {
                        newContentListItem.add(
                            ContentItemTextHeadline(
                                type = HeadlineType.H1,
                                text = content.apiText ?: ""
                            )
                        )
                    } else if (content.apiType == ContentType.H2) {
                        newContentListItem.add(
                            ContentItemTextHeadline(
                                type = HeadlineType.H2,
                                text = content.apiText ?: ""
                            )
                        )
                    } else {
                        newContentListItem.add(
                            ContentItemUnknown(
                                type = content.apiType.toString(),
                                attr = content.apiAttr
                            )
                        )
                    }
                }
        }
        newContentListItem.addTextContent(
            text = paragraphText,
            markups = markups,
            contentSuccessfullyAdded = {
                paragraphText = ""
                markups.clear()
            })
        return newContentListItem
    }

    private fun ArrayList<ContentItem>.addTextContent(
        text: String,
        markups: List<Markup>,
        contentSuccessfullyAdded: () -> Unit
    ) {
        if (text.isNotBlank() && markups.isNotEmpty()) {
            this.add(ContentItemText(text = text, markups = markups.toMutableList()))
            contentSuccessfullyAdded()
        }
    }

    private fun ArrayList<Markup>.addMarkUp(
        type: MarkupType,
        start: Int,
        end: Int,
        href: String?
    ) {
        this.add(
            Markup(
                type = type,
                start = start,
                end = end,
                href = href
            )
        )
    }

    private fun ContentItemApiModel.doOnTextContent(
        action: (
            markupType: MarkupType,
            text: String,
            href: String?
        ) -> Unit
    ): ContentItemApiModel {
        if (apiType == ContentType.TEXT && this.apiText != null) {
            action.invoke(MarkupType.TEXT, this.apiText!!, null)
        } else if (apiType == ContentType.BOLD && this.apiText != null) {
            action.invoke(MarkupType.BOLD, this.apiText!!, null)
        } else if (apiType == ContentType.LINK && this.apiText != null && this.apiHref != null) {
            action.invoke(MarkupType.LINK, this.apiText!!, this.apiHref)
        }
        return this
    }

    private fun ContentItemApiModel.doOnOtherContent(action: (ContentItemApiModel) -> Unit) {
        if (apiType != ContentType.TEXT && apiType != ContentType.BOLD && apiType != ContentType.LINK) {
            action(this)
        }
    }
}
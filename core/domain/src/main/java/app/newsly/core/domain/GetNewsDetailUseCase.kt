package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.domain.model.*
import app.newsly.core.domain.model.enums.MarkupType
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.core.network.model.ContentItemApiModel
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
                    val contentList = newsDetailApiModel.contentItems?.filter {
                        it.hasValidContent
                    }
                    if (contentList != null) {
                        emit(
                            RequestResult.Success(
                                NewsDetail(
                                    id = newsDetailApiModel.id ?: -1,
                                    imageUrl = newsDetailApiModel.imageUrl ?: "",
                                    content = contentList.toDomainContentList()
                                )
                            )
                        )
                    }
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }

    private fun ContentItemApiModel.doOnTextContent(
        action: (
            markupType: MarkupType,
            text: String,
            href: String?
        ) -> Unit
    ): ContentItemApiModel {
        this.type?.let { type ->
            if (type == ContentType.TEXT && this.text != null) {
                action.invoke(MarkupType.Simple, this.text!!, null)
            } else if (type == ContentType.TEXT_BOLD && this.text != null) {
                action.invoke(MarkupType.Bold, this.text!!, null)
            } else if (type == ContentType.LINK && this.text != null && this.href != null) {
                action.invoke(MarkupType.Link, this.text!!, this.href!!)
            }
        }
        return this
    }

    private fun ContentItemApiModel.doOnOtherContent(action: (ContentItemApiModel) -> Unit): Unit {
        this.type?.let { type ->
            if (type != ContentType.TEXT && type != ContentType.TEXT_BOLD && type != ContentType.LINK)
                action(this)
        }
    }

    private fun List<ContentItemApiModel>.toDomainContentList(): ArrayList<Content> {
        val newContentList = ArrayList<Content>()
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
                }.doOnOtherContent {
                    if (paragraphText.isNotBlank() && markups.isNotEmpty()) {
                        newContentList.add(
                            ContentText(
                                text = paragraphText,
                                markups = markups.toMutableList()
                            )
                        )
                        paragraphText = ""
                        markups.clear()
                    }
                    if (content.type == ContentType.IMAGE) {
                        newContentList.add(ContentImage(url = content.href ?: ""))
                    } else if (content.type == ContentType.H1) {
                        newContentList.add(ContentTextH1(text = content.text ?: ""))
                    }
                }

            /*if (content.type == ContentType.TEXT) {
                content.text?.let {
                    markups.addAsSimpleMarkUp(
                        start = paragraphText.length,
                        end = paragraphText.length + it.length
                    )
                    paragraphText += it
                }
            } else if (content.type == ContentType.TEXT_BOLD) {
                content.text?.let {
                    markups.addAsBoldMarkUp(
                        start = paragraphText.length,
                        end = paragraphText.length + it.length
                    )
                    paragraphText += it
                }
            } else if (content.type == ContentType.LINK) {
                val text = content.text
                val link = content.href
                if (text != null && link != null) {
                    markups.addAsLinkMarkUp(
                        start = paragraphText.length,
                        end = paragraphText.length + text.length,
                        href = link
                    )
                    paragraphText += text
                }
            } else {
                if (paragraphText.isNotBlank() && markups.isNotEmpty()) {
                    newContentList.add(
                        ContentText(
                            text = paragraphText,
                            markups = markups.toMutableList()
                        )
                    )
                    paragraphText = ""
                    markups.clear()
                }
                if (content.type == ContentType.IMAGE) {
                    newContentList.add(ContentImage(url = content.href ?: ""))
                } else if (content.type == ContentType.H1) {
                    newContentList.add(ContentTextH1(text = content.text ?: ""))
                }
            }*/
        }
        if (paragraphText.isNotBlank() && markups.isNotEmpty()) {
            newContentList.add(
                ContentText(
                    text = paragraphText,
                    markups = markups.toMutableList()
                )
            )
            paragraphText = ""
            markups.clear()
        }
        return newContentList
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
}
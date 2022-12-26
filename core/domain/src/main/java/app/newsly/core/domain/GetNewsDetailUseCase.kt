package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.domain.model.*
import app.newsly.core.domain.model.enums.MarkupType
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.core.network.model.ContentApiModel
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
                .doOnSuccess { data ->
                    emit(
                        RequestResult.Success(
                            NewsDetail(
                                id = data.id ?: -1,
                                imageUrl = data.imageUrl ?: "",
                                content = data.content?.toDomainContent() ?: emptyList()
                            )
                        )
                    )
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }

    private fun List<ContentApiModel>.toDomainContent(): ArrayList<Content> {
        val newContentList = ArrayList<Content>()
        var paragraphText = ""
        val markups = ArrayList<Markup>()
        this.forEach { content ->
            if (content.type == ContentType.TEXT) {
                content.text?.let {
                    markups.add(
                        Markup(
                            type = MarkupType.Simple,
                            start = paragraphText.length,
                            end = paragraphText.length + it.length
                        )
                    )
                    paragraphText += it
                }
            } else if (content.type == ContentType.TEXT_BOLD) {
                content.text?.let {
                    markups.add(
                        Markup(
                            type = MarkupType.Bold,
                            start = paragraphText.length,
                            end = paragraphText.length + it.length
                        )
                    )
                    paragraphText += it
                }
            } else if (content.type == ContentType.LINK) {
                val text = content.text
                val link = content.href
                if (text != null && link != null) {
                    markups.add(
                        Markup(
                            type = MarkupType.Link,
                            start = paragraphText.length,
                            end = paragraphText.length + text.length,
                            href = link
                        )
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
            }
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
}
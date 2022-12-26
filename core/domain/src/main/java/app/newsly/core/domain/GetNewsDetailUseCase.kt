package app.newsly.core.domain

import app.newsly.core.data.repository.NewsRepository
import app.newsly.core.domain.model.*
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import app.newsly.core.network.model.ContentApiModel
import app.newsly.core.network.model.enums.ContentType
import app.newsly.shared.util.fromJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<RequestResult<NewsDetail>> {
        return flow {
            emit(RequestResult.Loading)
            newsRepository
                .getNewsDetail()
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { data ->
                    emit(
                        RequestResult.Success(
                            NewsDetail(
                                id = data.id ?: -1,
                                content = data.content?.toDomainContent()
                            )
                        )
                    )
                }
                .doOnFailure { exception ->
                    emit(RequestResult.Fail(exception))
                }
        }
    }

    fun List<ContentApiModel>?.toDomainContent(): List<Content<Any>>? =
        this?.map {
            when (it.type) {
                ContentType.TEXT -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentText>()
                    )
                }
                ContentType.IMAGE -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentImage>()
                    )
                }
                ContentType.LINK -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentLink>()
                    )
                }
                ContentType.TEXT_BOLD -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentTextBold>()
                    )
                }
                ContentType.H1 -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentTextH1>()
                    )
                }
                null -> {
                    Content(
                        type = it.type,
                        it.attr.fromJson<ContentImage>()
                    )
                }
            }
        }

}
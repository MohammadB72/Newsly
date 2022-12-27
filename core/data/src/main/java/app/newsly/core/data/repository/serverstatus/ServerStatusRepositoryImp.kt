package app.newsly.core.data.repository.serverstatus

import app.newsly.core.model.RequestResult
import app.newsly.core.network.datasource.serverstatus.ServerStatusRemoteDataSource
import app.newsly.core.network.model.EmptyResponseApiModel
import javax.inject.Inject

class ServerStatusRepositoryImp @Inject constructor(
    private val networkDataSource: ServerStatusRemoteDataSource
) : ServerStatusRepository {

    override suspend fun getServerStatus(): RequestResult<EmptyResponseApiModel> =
        networkDataSource.getServerStatus()
}
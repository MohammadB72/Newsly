package app.newsly.core.data.repository

import app.newsly.core.model.RequestResult
import app.newsly.core.network.datasource.server.RemoteDataSource
import app.newsly.core.network.model.EmptyResponseNetworkModel
import javax.inject.Inject

class ServerStatusRepositoryImp @Inject constructor(
    private val networkDataSource: RemoteDataSource
) : ServerStatusRepository {

    override suspend fun getServerStatus(): RequestResult<EmptyResponseNetworkModel> =
        networkDataSource.getServerStatus()
}
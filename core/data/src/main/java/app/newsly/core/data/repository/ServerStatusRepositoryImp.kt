package app.newsly.core.data.repository

import app.newsly.core.model.ApiResult
import app.newsly.core.model.network.ServerStatusNetworkModel
import app.newsly.core.network.RemoteDataSource
import javax.inject.Inject

class ServerStatusRepositoryImp @Inject constructor(
    private val networkDataSource: RemoteDataSource
) : ServerStatusRepository {

    override suspend fun getServerStatus(): ApiResult<ServerStatusNetworkModel> =
        networkDataSource.getServerStatus()
}
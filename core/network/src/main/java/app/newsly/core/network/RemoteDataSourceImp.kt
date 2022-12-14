package app.newsly.core.network

import app.newsly.core.model.Result
import app.newsly.core.model.network.ServerStatusNetworkModel
import app.newsly.core.network.retrofit.ServerStatusApi
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val serverStatusApi: ServerStatusApi
) : RemoteDataSource {

    override suspend fun getServerStatus(): Result<ServerStatusNetworkModel> =
        apiCallWithResult { serverStatusApi.getServerStatus() }

}
package app.newsly.core.network

import android.content.Context
import app.newsly.core.model.ApiResult
import app.newsly.core.model.network.ServerStatusNetworkModel
import app.newsly.core.network.retrofit.ServerStatusApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val serverStatusApi: ServerStatusApi
) : RemoteDataSource {

    override suspend fun getServerStatus(): ApiResult<ServerStatusNetworkModel> =
        apiCallWithResult(
            context = context,
            apiCallBlock = { serverStatusApi.getServerStatus() }
        )
}
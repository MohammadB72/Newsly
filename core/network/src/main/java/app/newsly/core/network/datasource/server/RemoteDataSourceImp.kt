package app.newsly.core.network.datasource.server

import android.content.Context
import app.newsly.core.model.RequestResult
import app.newsly.core.model.network.EmptyResponseNetworkModel
import app.newsly.core.network.retrofit.apiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val serverStatusApi: ServerStatusApi,
) : RemoteDataSource {

    override suspend fun getServerStatus(): RequestResult<EmptyResponseNetworkModel> =
        apiCall(
            context = context,
            block = { serverStatusApi.getServerStatus() }
        )
}
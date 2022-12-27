package app.newsly.core.network.datasource.serverstatus

import android.content.Context
import app.newsly.core.model.RequestResult
import app.newsly.core.network.model.EmptyResponseApiModel
import app.newsly.core.network.retrofit.apiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ServerStatusRemoteDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val serverStatusApi: ServerStatusApi,
) : ServerStatusRemoteDataSource {

    override suspend fun getServerStatus(): RequestResult<EmptyResponseApiModel> =
        apiCall(
            context = context,
            block = { serverStatusApi.getServerStatus() }
        )
}
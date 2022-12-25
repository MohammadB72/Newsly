package app.newsly.core.model.network.newsdetail

import app.newsly.core.model.domain.BaseDomainModel
import app.newsly.core.model.domain.DomainModelList
import app.newsly.core.model.network.BaseNetworkModel


class NetworkModelList<JsonModel : BaseNetworkModel> : ArrayList<JsonModel>() {

    fun toDomainModel(): DomainModelList<BaseDomainModel> {
        val domainModel = DomainModelList<BaseDomainModel>()
        domainModel.addAll(this.map { it.toDomainModel() })
        return domainModel
    }
}

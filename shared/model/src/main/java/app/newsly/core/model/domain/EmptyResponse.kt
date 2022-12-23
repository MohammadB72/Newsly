package app.newsly.core.model.domain

class EmptyResponse : BaseDomainModel() {
    override val hasValidData: Boolean
        get() = true
}

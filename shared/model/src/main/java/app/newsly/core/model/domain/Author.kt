package app.newsly.core.model.domain

data class Author(
    override val hasValidData: Boolean,
    val name: String,
    val avatar: String
) : BaseDomainModel() {

    companion object {
        val EMPTY = Author(hasValidData = false, name = "", avatar = "")
    }
}

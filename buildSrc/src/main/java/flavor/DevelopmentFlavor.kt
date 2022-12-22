package flavor

import flavor.dimension.FlavorDimension

object DevelopmentFlavor : ProductFlavor {
    override val name = "development"
    override val dimension = FlavorDimension.MODE.value
    override val versionNameSuffix = "-dev"
    override val applicationIdSuffix = ".dev"
}
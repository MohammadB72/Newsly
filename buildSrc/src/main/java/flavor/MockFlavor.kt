package flavor

import flavor.dimension.FlavorDimension


object MockFlavor : ProductFlavor {
    override val name = "mock"
    override val dimension = FlavorDimension.MODE.value
    override val versionNameSuffix = "-mock"
    override val applicationIdSuffix = ""
}
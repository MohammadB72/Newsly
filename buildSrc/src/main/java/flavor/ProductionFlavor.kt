package flavor

import flavor.dimension.FlavorDimension

object ProductionFlavor : ProductFlavor {
    override val name = "production"
    override val dimension = FlavorDimension.MODE.value
    override val versionNameSuffix = ""
    override val applicationIdSuffix = ""
}
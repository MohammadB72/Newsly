package app.newsly.feature.main.navigation

import app.newsly.core.designsystem.icon.Icon
import app.newsly.core.designsystem.icon.Icon.ImageVectorIcon
import app.newsly.core.designsystem.icon.NewslyIcons
import app.newsly.shared.resources.R


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val titleTextId: Int
) {
    NEWS(
        selectedIcon = ImageVectorIcon(NewslyIcons.News),
        unselectedIcon = ImageVectorIcon(NewslyIcons.NewsBorder),
        titleTextId = R.string.news
    ),
    CATEGORIES(
        selectedIcon = ImageVectorIcon(NewslyIcons.Categories),
        unselectedIcon = ImageVectorIcon(NewslyIcons.CategoriesBorder),
        titleTextId = R.string.categories
    ),
    Apps(
        selectedIcon = ImageVectorIcon(NewslyIcons.Star),
        unselectedIcon = ImageVectorIcon(NewslyIcons.StarBorder),
        titleTextId = R.string.apps
    )
}
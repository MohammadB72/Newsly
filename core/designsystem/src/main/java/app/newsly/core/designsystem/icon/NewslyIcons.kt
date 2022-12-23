package app.newsly.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


object NewslyIcons {
    val News = Icons.Rounded.Newspaper
    val NewsBorder = Icons.Outlined.Newspaper

    val EditorChoice = Icons.Rounded.Star
    val EditorChoiceBorder = Icons.Outlined.Star

    val Categories = Icons.Rounded.Category
    val CategoriesBorder = Icons.Outlined.Category

    val Apps = Icons.Rounded.Apps
    val AppsBorder = Icons.Outlined.Apps

    val NoAccounts = Icons.Rounded.NoAccounts
    val NoAccountsBorder = Icons.Outlined.NoAccounts
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()

    @Composable
    fun ToIcon() {
        when (this) {
            is ImageVectorIcon -> {
                androidx.compose.material3.Icon(imageVector = imageVector, contentDescription = "")
            }
            is DrawableResourceIcon -> {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = id), contentDescription = ""
                )
            }
        }
    }
}

package app.newsly.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Interests
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


object NewslyIcons {
    val News = Icons.Rounded.Newspaper
    val NewsBorder = Icons.Outlined.Newspaper

    val EditorChoice = Icons.Rounded.Interests
    val EditorChoiceBorder = Icons.Outlined.Interests

    val Categories = Icons.Rounded.Category
    val CategoriesBorder = Icons.Outlined.Category

    val Apps = Icons.Rounded.Apps
    val AppsBorder = Icons.Outlined.Apps
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

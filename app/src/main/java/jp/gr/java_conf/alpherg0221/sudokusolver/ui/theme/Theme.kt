package jp.gr.java_conf.alpherg0221.sudokusolver.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = RoyalBlue200,
    primaryVariant = RoyalBlue700,
    secondary = Jade200,
)

private val LightColors = lightColors(
    primary = RoyalBlue500,
    primaryVariant = RoyalBlue700,
    secondary = Jade200,
)

@Composable
fun SudokuSolverTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
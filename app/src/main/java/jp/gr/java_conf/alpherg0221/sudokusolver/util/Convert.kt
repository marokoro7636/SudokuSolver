package jp.gr.java_conf.alpherg0221.sudokusolver.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import jp.gr.java_conf.alpherg0221.sudokusolver.R
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme

@Composable
fun AppTheme.toThemeString(): String {
    return when (this) {
        AppTheme.Dark -> stringResource(id = R.string.dark_theme)
        AppTheme.Light -> stringResource(id = R.string.light_theme)
        AppTheme.Default -> stringResource(id = R.string.default_theme)
    }
}
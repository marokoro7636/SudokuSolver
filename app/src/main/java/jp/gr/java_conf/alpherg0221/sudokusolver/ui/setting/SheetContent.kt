package jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import jp.gr.java_conf.alpherg0221.compose.material.BottomSheetLayout
import jp.gr.java_conf.alpherg0221.compose.material.RadioButtonItem
import jp.gr.java_conf.alpherg0221.sudokusolver.R
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme
import jp.gr.java_conf.alpherg0221.sudokusolver.util.toThemeString

@Composable
fun SelectThemeContent(
    currentTheme: AppTheme,
    onChecked: (AppTheme) -> Unit,
) {
    BottomSheetLayout(title = stringResource(id = R.string.theme)) {
        AppTheme.values().forEach { appTheme ->
            RadioButtonItem(
                label = appTheme.toThemeString(),
                selected = currentTheme == appTheme,
                onChecked = { onChecked(appTheme) }
            )
        }
    }
}
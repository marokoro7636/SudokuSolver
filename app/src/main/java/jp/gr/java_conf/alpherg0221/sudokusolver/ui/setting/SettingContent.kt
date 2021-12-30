package jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Brightness4
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import jp.gr.java_conf.alpherg0221.compose.material.AppDivider
import jp.gr.java_conf.alpherg0221.compose.material.InsetAwareTopAppBar
import jp.gr.java_conf.alpherg0221.compose.material.PreferencesItem
import jp.gr.java_conf.alpherg0221.compose.material.PreferencesTitle
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme
import jp.gr.java_conf.alpherg0221.sudokusolver.R
import jp.gr.java_conf.alpherg0221.sudokusolver.util.toThemeString

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingContent(
    onBack: () -> Unit,
    onCloseBottomSheet: () -> Unit = {},
    backHandlerEnable: Boolean,
    sheetState: ModalBottomSheetState,
    theme: AppTheme,
    onClickItem: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit
) {
    BackHandler(enabled = backHandlerEnable, onBack = onCloseBottomSheet)
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = sheetContent,
    ) {
        Scaffold(
            topBar = {
                InsetAwareTopAppBar(
                    title = { Text(text = stringResource(id = R.string.settings)) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            content = {
                Column {
                    PreferencesTitle(title = stringResource(id = R.string.general))
                    PreferencesItem(
                        title = stringResource(id = R.string.theme),
                        subtitle = theme.toThemeString(),
                        onClick = onClickItem,
                        icon = Icons.Rounded.Brightness4,
                    )
                    AppDivider()
                }
            }
        )
    }
}
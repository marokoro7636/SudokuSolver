package jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel,
    onBack: () -> Unit,
) {
    val uiState by settingViewModel.uiState.collectAsState()
    val theme by settingViewModel.theme.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    SettingContent(
        onBack = onBack,
        onCloseBottomSheet = { scope.launch { sheetState.hide() } },
        backHandlerEnable = sheetState.isVisible,
        sheetState = sheetState,
        theme = theme,
        onClickItem = { scope.launch { sheetState.show() } },
        sheetContent = {
            SelectThemeContent(
                currentTheme = theme,
                onChecked = { theme ->
                    scope.launch { sheetState.hide() }
                    settingViewModel.setTheme(theme)
                },
            )
        }
    )
}
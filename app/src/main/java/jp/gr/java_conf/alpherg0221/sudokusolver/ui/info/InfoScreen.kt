package jp.gr.java_conf.alpherg0221.sudokusolver.ui.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun InfoScreen(
    infoViewModel: InfoViewModel,
    onBack: () -> Unit,
) {
    val uiState by infoViewModel.infoUiState.collectAsState()

    InfoContent(
        onBack = onBack,
        onVersionClick = {},
        onOSSClick = {}
    )
}
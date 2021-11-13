package jp.gr.java_conf.alpherg0221.sudokusolver.ui.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import jp.gr.java_conf.alpherg0221.sudokusolver.util.NoAnswerException
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onBack: () -> Unit,
) {
    val uiState by homeViewModel.uiState.collectAsState()
    val field by homeViewModel.field.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    HomeContent(
        list = field,
        loading = uiState.loading,
        errorMessage = uiState.errorMessage,
        onClickItem = { r, c ->
            homeViewModel.updateSquare(r, c)
            scope.launch { sheetState.show() }
        },
        onCalculate = {
            homeViewModel.calculate()
        },
        deleteAll = {
            homeViewModel.deleteAll()
        },
        sheetState = sheetState,
        onCloseBottomSheet = {
            scope.launch { sheetState.hide() }
        },
        sheetContent = {
            InputBottomSheet(
                r = uiState.currentRow,
                c = uiState.currentColumn,
                onClick = { number ->
                    homeViewModel.updateField(uiState.currentRow, uiState.currentColumn, number)
                    scope.launch { sheetState.hide() }
                },
                onDelete = {
                    homeViewModel.updateField(uiState.currentRow, uiState.currentColumn, 0)
                    scope.launch { sheetState.hide() }
                }
            )
        },
    )
}
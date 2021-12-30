package jp.gr.java_conf.alpherg0221.sudokusolver.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import jp.gr.java_conf.alpherg0221.compose.material.InsetAwareTopAppBar
import jp.gr.java_conf.alpherg0221.sudokusolver.R

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    list: List<List<Int>>,
    loading: Boolean = false,
    errorMessage: Boolean = false,
    onClickItem: (Int, Int) -> Unit = { _, _ -> },
    onCalculate: () -> Unit = {},
    deleteAll: () -> Unit = {},
    openDrawer: () -> Unit,
    sheetState: ModalBottomSheetState,
    onCloseBottomSheet: () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit = {}
) {
    BackHandler(enabled = sheetState.isVisible, onBack = onCloseBottomSheet)
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetContent = sheetContent,
    ) {
        Scaffold(
            topBar = {
                InsetAwareTopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = openDrawer) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = deleteAll) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BoxWithConstraints {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(18.dp)
                            .border(
                                BorderStroke(
                                    width = (3.dp),
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 1f)
                                )
                            ),
                        cells = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.Center,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        repeat(3) { index1 ->
                            repeat(3) { index2 ->
                                item {
                                    Mass(
                                        list = list,
                                        position = index1 to index2,
                                        squareSize = (maxWidth - 42.dp) / 9,
                                        onClickItem = onClickItem,
                                    )
                                }
                            }
                        }
                    }
                }
                OutlinedButton(onClick = onCalculate) {
                    Text(text = stringResource(id = R.string.calculate))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = if (errorMessage) stringResource(id = R.string.error_msg) else "",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}

@Composable
fun Square(
    modifier: Modifier = Modifier,
    text: String = " ",
    onClickItem: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clickable { onClickItem() },
        border = BorderStroke(
            width = (0.5.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = .5f)
        ),
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Mass(
    list: List<List<Int>>,
    position: Pair<Int, Int>,
    squareSize: Dp,
    onClickItem: (Int, Int) -> Unit = { _, _ -> },
) {
    Column(
        modifier = Modifier
            .border(
                BorderStroke(
                    width = (1.5.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 1f)
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        list.forEachIndexed { index1, list2 ->
            if (index1 / 3 == position.first) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    list2.forEachIndexed { index2, text ->
                        if (index2 / 3 == position.second) {
                            Square(
                                modifier = Modifier.size(squareSize),
                                text = if (text == 0) " " else text.toString(),
                                onClickItem = { onClickItem(index1, index2) }
                            )
                        }
                    }
                }
            }
        }
    }
}
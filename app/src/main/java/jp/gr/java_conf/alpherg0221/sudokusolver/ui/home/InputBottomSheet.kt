package jp.gr.java_conf.alpherg0221.sudokusolver.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.gr.java_conf.alpherg0221.compose.material.BottomSheetLayout


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputBottomSheet(
    r: Int,
    c: Int,
    onClick: (Int) -> Unit = {},
    onDelete: () -> Unit = {}
) {
    BottomSheetLayout(
        title = "${r + 1}行目 ${c + 1}列目",
        actions = {
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Rounded.Backspace, contentDescription = null)
            }
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            itemsIndexed(listOf(7, 8, 9, 4, 5, 6, 1, 2, 3)) { _, text ->
                TextButton(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),
                    onClick = { onClick(text) },
                ) {
                    Text(
                        text = text.toString(),
                        fontSize = 36.sp
                    )
                }
            }
        }
    }
}
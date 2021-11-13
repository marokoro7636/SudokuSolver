package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.theme.SudokuSolverTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SudokuSolverApp() {
    SudokuSolverTheme {
        ProvideWindowInsets {
            val scaffoldState = rememberScaffoldState()

            val navController = rememberAnimatedNavController()

           Scaffold(
               scaffoldState = scaffoldState,
               drawerContent = {

               }
           ) {
               SudokuSolverNavGraph(
                   navController = navController
               )
           }
        }
    }
}
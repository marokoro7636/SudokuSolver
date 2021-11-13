package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeViewModel

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SETTING_ROUTE = "setting"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SudokuSolverNavGraph(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    val actions = remember(navController) { MainActions(navController) }

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory()
            )
            HomeScreen(
                homeViewModel = homeViewModel,
                onBack = actions.upPress,
            )
        }
        composable(MainDestinations.SETTING_ROUTE) {

        }
    }
}

class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
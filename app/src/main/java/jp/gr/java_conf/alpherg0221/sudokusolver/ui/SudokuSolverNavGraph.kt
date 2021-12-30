package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import jp.gr.java_conf.alpherg0221.sudokusolver.data.AppContainer
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeViewModel
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.info.InfoScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.info.InfoViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SudokuSolverNavGraph(
    navController: NavHostController = rememberAnimatedNavController(),
    openDrawer: () -> Unit = {},
    navigateToOSS: () -> Unit = {},
    navigateToPrivacyPolicy: () -> Unit = {},
    onBack: () -> Unit = {},
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
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
                openDrawer = openDrawer,
            )
        }
        composable(MainDestinations.SETTINGS_ROUTE) {

        }
        composable(MainDestinations.INFO_ROUTE) {
            val infoViewModel: InfoViewModel = viewModel(
                factory = InfoViewModel.provideFactory()
            )
            InfoScreen(
                infoViewModel = infoViewModel,
                navigateToOSS = navigateToOSS,
                navigateToPrivacyPolicy = navigateToPrivacyPolicy,
                onBack = onBack,
            )
        }
    }
}
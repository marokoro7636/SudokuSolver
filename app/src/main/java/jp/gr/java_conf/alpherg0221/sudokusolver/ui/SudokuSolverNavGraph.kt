package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.gr.java_conf.alpherg0221.sudokusolver.data.AppContainer
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.home.HomeViewModel
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.info.InfoScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.info.InfoViewModel
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting.SettingScreen
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting.SettingViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SudokuSolverNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    navigateToOSS: () -> Unit = {},
    navigateToPrivacyPolicy: () -> Unit = {},
    onBack: () -> Unit = {},
    startDestination: String = NavigationDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory()
            )
            HomeScreen(
                homeViewModel = homeViewModel,
                openDrawer = openDrawer,
            )
        }
        composable(NavigationDestinations.SETTINGS_ROUTE) {
            val settingViewModel: SettingViewModel = viewModel(
                factory = SettingViewModel.provideFactory(appContainer.settingRepository)
            )
            SettingScreen(
                settingViewModel = settingViewModel,
                onBack = onBack,
            )
        }
        composable(NavigationDestinations.INFO_ROUTE) {
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
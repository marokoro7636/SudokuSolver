package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.theme.SudokuSolverTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SudokuSolverApp() {
    SudokuSolverTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkIcons)
            }

            val navController = rememberAnimatedNavController()
            val navigationActions = remember(navController) {
                MainActions(navController)
            }

            val scope = rememberCoroutineScope()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute =
                navBackStackEntry?.destination?.route ?: MainDestinations.HOME_ROUTE

            val drawerState = rememberDrawerState(DrawerValue.Closed)

            BackHandler(
                enabled = drawerState.isOpen,
                onBack = { scope.launch { drawerState.close() } }
            )

            ModalDrawer(
                drawerContent = {
                    AppDrawer(
                        currentRoute = currentRoute,
                        navigateToHome = navigationActions.navigateToHome,
                        navigateToSetting = navigationActions.navigateToSettings,
                        navigateToInfo = navigationActions.navigateToInfo,
                        closeDrawer = { scope.launch { drawerState.close() } }
                    )
                },
                drawerState = drawerState,
                gesturesEnabled = currentRoute == MainDestinations.HOME_ROUTE
            ) {
                SudokuSolverNavGraph(
                    navController = navController,
                    openDrawer = { scope.launch { drawerState.open() } },
                    navigateToOSS = navigationActions.navigateToOSS,
                    navigateToPrivacyPolicy = navigationActions.navigateToPrivacyPolicy,
                    onBack = navigationActions.onBack
                )
            }
        }
    }
}
package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jp.gr.java_conf.alpherg0221.sudokusolver.data.AppContainer
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme
import jp.gr.java_conf.alpherg0221.sudokusolver.ui.theme.SudokuSolverTheme
import kotlinx.coroutines.launch

@Composable
fun SudokuSolverApp(
    appContainer: AppContainer
) {
    val theme by appContainer.settingRepository.observeTheme()
        .collectAsState(initial = AppTheme.Default)

    SudokuSolverTheme(
        darkTheme = when (theme) {
            AppTheme.Dark -> true
            AppTheme.Light -> false
            AppTheme.Default -> isSystemInDarkTheme()
        }
    ) {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkIcons)
        }

        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NavigationActions(navController)
        }

        val scope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NavigationDestinations.HOME_ROUTE

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
            modifier = Modifier.navigationBarsPadding(),
            drawerState = drawerState,
            gesturesEnabled = currentRoute == NavigationDestinations.HOME_ROUTE
        ) {
            SudokuSolverNavGraph(
                appContainer = appContainer,
                navController = navController,
                openDrawer = { scope.launch { drawerState.open() } },
                navigateToOSS = navigationActions.navigateToOSS,
                navigateToPrivacyPolicy = navigationActions.navigateToPrivacyPolicy,
                onBack = navigationActions.onBack
            )
        }
    }
}
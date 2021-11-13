package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import androidx.navigation.NavHostController

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SETTINGS_ROUTE = "setting"
    const val INFO_ROUTE = "info"
}

class MainActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(MainDestinations.HOME_ROUTE)
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(MainDestinations.SETTINGS_ROUTE)
    }
    val navigateToInfo: () -> Unit = {
        navController.navigate(MainDestinations.INFO_ROUTE)
    }
    val onBack: () -> Unit = {
        navController.navigateUp()
    }
}
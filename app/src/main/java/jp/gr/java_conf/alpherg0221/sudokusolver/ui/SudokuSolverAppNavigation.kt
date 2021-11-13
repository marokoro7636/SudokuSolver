package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

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
    val navigateToOSS: () -> Unit = {
        val context = navController.context
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
    }
    val navigateToPrivacyPolicy: () -> Unit = {
        val context = navController.context
        context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/marokoro7636/SudokuSolver")))
    }
    val onBack: () -> Unit = {
        navController.navigateUp()
    }
}
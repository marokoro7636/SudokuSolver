package jp.gr.java_conf.alpherg0221.sudokusolver.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import jp.gr.java_conf.alpherg0221.sudokusolver.SudokuSolverApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as SudokuSolverApplication).container

        setContent {
            SudokuSolverApp(appContainer)
        }
    }
}
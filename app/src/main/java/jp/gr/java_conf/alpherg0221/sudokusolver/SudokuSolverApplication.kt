package jp.gr.java_conf.alpherg0221.sudokusolver

import android.app.Application
import jp.gr.java_conf.alpherg0221.sudokusolver.data.AppContainer
import jp.gr.java_conf.alpherg0221.sudokusolver.data.AppContainerImpl

class SudokuSolverApplication : Application() {

    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}
package jp.gr.java_conf.alpherg0221.sudokusolver.data

import android.content.Context
import jp.gr.java_conf.alpherg0221.sudokusolver.data.setting.SettingRepository

interface AppContainer {
    val settingRepository: SettingRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val settingRepository: SettingRepository by lazy {
        SettingRepository(applicationContext)
    }
}
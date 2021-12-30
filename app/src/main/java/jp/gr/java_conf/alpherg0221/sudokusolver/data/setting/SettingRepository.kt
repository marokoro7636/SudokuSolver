package jp.gr.java_conf.alpherg0221.sudokusolver.data.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingRepository(
    private val applicationContext: Context
) {
    private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")
    private val themeKey = stringPreferencesKey("theme")

    fun observeTheme(): Flow<AppTheme> =
        applicationContext.settingsDataStore.data.map { setting ->
            try {
                setting[themeKey]?.let { AppTheme.valueOf(it) } ?: AppTheme.Default
            } catch (e: Throwable) {
                AppTheme.Default
            }
        }

    suspend fun setTheme(theme: AppTheme) {
        withContext(Dispatchers.IO) {
            applicationContext.settingsDataStore.edit { setting ->
                setting[themeKey] = theme.name
            }
        }
    }
}
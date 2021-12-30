package jp.gr.java_conf.alpherg0221.sudokusolver.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import jp.gr.java_conf.alpherg0221.sudokusolver.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.sudokusolver.model.AppTheme
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SettingUiState(
    val loading: Boolean = false,
)

class SettingViewModel(
    private val settingRepository: SettingRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    val theme = settingRepository.observeTheme().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        AppTheme.Default,
    )

    init {
        refreshAll()
    }

    private fun refreshAll() {
        _uiState.update { it.copy(loading = false) }
    }

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingRepository.setTheme(theme = theme)
        }
    }

    companion object {
        fun provideFactory(
            settingRepository: SettingRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingViewModel(settingRepository = settingRepository) as T
            }
        }
    }
}
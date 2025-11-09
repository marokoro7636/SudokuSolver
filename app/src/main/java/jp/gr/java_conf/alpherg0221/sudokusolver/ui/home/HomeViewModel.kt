package jp.gr.java_conf.alpherg0221.sudokusolver.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import jp.gr.java_conf.alpherg0221.sudokusolver.util.CalcSudoku
import jp.gr.java_conf.alpherg0221.sudokusolver.util.NoAnswerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class HomeUiState(
    val currentRow: Int = 0,
    val currentColumn: Int = 0,
    val errorMessage: Boolean = false,
    val loading: Boolean = false,
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _field = MutableStateFlow(
        mutableListOf(
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        )
    )
    val field: StateFlow<List<List<Int>>> = _field

    fun updateField(r: Int, c: Int, value: Int) {
        _field.update { field ->
            val newField = MutableList(9) { i ->
                field[i].toMutableList()
            }
            newField[r][c] = value
            newField
        }
    }

    fun calculate() {
        _uiState.update { it.copy(loading = true, errorMessage = false) }
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.Default) {
                    CalcSudoku(_field.value).calculate()
                }
                _field.update { result.map { it.toMutableList() }.toMutableList() }
            } catch (e: NoAnswerException) {
                _uiState.update { it.copy(errorMessage = true) }
            } finally {
                _uiState.update { it.copy(loading = false) }
            }
        }
    }

    fun deleteAll() {
        _field.update {
            mutableListOf(
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            )
        }
        println(_field.value)
    }

    fun updateSquare(r: Int, c: Int) {
        _uiState.update {
            it.copy(currentRow = r, currentColumn = c)
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }
}
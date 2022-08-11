package model

import data.Question

sealed class AppState {
    data class Success(val questions: List<Question>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading: AppState()
    data class Reload(val questions: List<Question>) : AppState()
}

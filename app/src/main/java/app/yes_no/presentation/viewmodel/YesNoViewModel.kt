package app.yes_no.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.yes_no.domain.model.YesNoResponse
import app.yes_no.domain.usecase.GetYesNoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YesNoViewModel @Inject constructor(
    private val getYesNoUseCase: GetYesNoUseCase
) : ViewModel() {

    var state by mutableStateOf<YesNoResponse?>(null)
        private set

    var isLoading by mutableStateOf(true)
    var error by mutableStateOf<String?>(null)


    init {
        fetchAnswer()
    }

    fun fetchAnswer() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                state = getYesNoUseCase()
            } catch (e: Exception) {
                error = e.localizedMessage ?: "Error"
            }
            isLoading = false
        }
    }


}
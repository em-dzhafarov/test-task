package com.dzhafarov.testtaskapplication.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.testtaskapplication.data.model.Status
import com.dzhafarov.testtaskapplication.domain.UserRegistrationUseCase
import com.dzhafarov.testtaskapplication.domain.validation.UserProfileValidationUseCase
import com.dzhafarov.testtaskapplication.presentation.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val userProfileValidationUseCase: UserProfileValidationUseCase
) : ViewModel() {

    private val _successLiveData = SingleLiveEvent<Unit>()
    val successLiveData: LiveData<Unit> get() = _successLiveData

    private val _errorLiveData = SingleLiveEvent<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val _progressLiveData = SingleLiveEvent<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    fun register(userName: String?, email: String?) {
        if (userName == null || email == null) return

        if (validateInputData(userName, email)) {
            viewModelScope.launch(Dispatchers.Main) {
                _progressLiveData.value = true
                val status = withContext(Dispatchers.IO) {
                    userRegistrationUseCase.create(userName, email)
                }
                _progressLiveData.value = false

                if (status is Status.Success) {
                    val result = status.data
                    _successLiveData.value = result
                } else if (status is Error) {
                    _errorLiveData.value = "Registration is failed"
                }
            }
        }
    }

    private fun validateInputData(userName: String?, email: String?): Boolean {
        val isValid = userProfileValidationUseCase.validate(userName, email)
        if (!isValid) {
            _errorLiveData.value = "Validation isn't passed!"
        }
        return isValid
    }
}

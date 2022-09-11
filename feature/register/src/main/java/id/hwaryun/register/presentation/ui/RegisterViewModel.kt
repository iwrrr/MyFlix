package id.hwaryun.register.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hwaryun.core.wrapper.ViewResource
import id.hwaryun.register.domain.RegisterUserUseCase
import id.hwaryun.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    val registerResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) {
        viewModelScope.launch {
            registerUserUseCase(
                RegisterUserUseCase.RegisterParam(
                    birthdate = birthdate,
                    email = email,
                    gender = gender,
                    password = password,
                    username = username,
                )
            ).collect {
                registerResult.postValue(it)
            }
        }
    }
}
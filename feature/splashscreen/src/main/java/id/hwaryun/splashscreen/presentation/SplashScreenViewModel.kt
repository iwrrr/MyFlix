package id.hwaryun.splashscreen.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hwaryun.core.wrapper.ViewResource
import id.hwaryun.splashscreen.domain.SyncResult
import id.hwaryun.splashscreen.domain.SyncUserUseCase
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val syncUserUseCase: SyncUserUseCase) : ViewModel() {

    val syncResult: MutableLiveData<ViewResource<SyncResult>> = MutableLiveData()

    fun syncUser() {
        viewModelScope.launch {
            syncUserUseCase().collect {
                syncResult.value = it
            }
        }
    }
}
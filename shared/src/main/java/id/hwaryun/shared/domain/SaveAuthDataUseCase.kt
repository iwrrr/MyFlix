package id.hwaryun.shared.domain

import id.hwaryun.core.base.BaseUseCase
import id.hwaryun.core.wrapper.DataResource
import id.hwaryun.core.wrapper.ViewResource
import id.hwaryun.shared.data.model.response.UserResponse
import id.hwaryun.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SaveAuthDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<SaveAuthDataUseCase.Param, Boolean>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> = flow {
        param?.let {
            val saveLoginStatus = repository.setUserLoginStatus(param.isLoggedIn).first()
            val saveToken = repository.setUserToken(param.authToken).first()
            val saveUser = repository.setCurrentUser(param.user).first()

            if (saveUser is DataResource.Success && saveToken is DataResource.Success && saveLoginStatus is DataResource.Success) {
                emit(ViewResource.Success(true))
            } else {
                emit(ViewResource.Error(IllegalStateException("Failed to save local data")))
            }
        }
    }

    data class Param(val isLoggedIn: Boolean, val authToken: String, val user: UserResponse)
}
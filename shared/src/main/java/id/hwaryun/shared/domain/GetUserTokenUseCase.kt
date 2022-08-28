package id.hwaryun.shared.domain

import id.hwaryun.core.base.BaseUseCase
import id.hwaryun.core.wrapper.ViewResource
import id.hwaryun.shared.data.repository.UserPreferenceRepository
import id.hwaryun.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetUserTokenUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> {
        return flow {
            repository.getUserToken().map {
                it.suspendSubscribe(
                    doOnSuccess = { result ->
                        emit(ViewResource.Success(result.payload.orEmpty()))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }
}
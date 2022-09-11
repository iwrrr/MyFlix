package id.hwaryun.register.domain

import id.hwaryun.core.base.BaseUseCase
import id.hwaryun.core.wrapper.ViewResource
import id.hwaryun.register.data.repository.RegisterRepository
import id.hwaryun.shared.data.model.mapper.UserMapper
import id.hwaryun.shared.data.model.viewparam.UserViewParam
import id.hwaryun.shared.domain.SaveAuthDataUseCase
import id.hwaryun.shared.utils.GenderUtils
import id.hwaryun.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val checkRegisterFieldUseCase: CheckRegisterFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val repository: RegisterRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterUserUseCase.RegisterParam, UserViewParam?>(dispatcher) {

    override suspend fun execute(param: RegisterParam?): Flow<ViewResource<UserViewParam?>> {

        return flow {
            mutateParam(param)?.let { p ->
                emit(ViewResource.Loading())
                checkRegisterFieldUseCase(p).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        repository.registerUser(
                            email = p.email,
                            password = p.password,
                            gender = p.gender,
                            birthdate = p.birthdate,
                            username = p.username
                        ).collect { registerResult ->
                            registerResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = registerResult.payload?.data
                                    val token = result?.token
                                    val user = result?.user
                                    if (!token.isNullOrEmpty() && user != null) {
                                        saveAuthDataUseCase(
                                            SaveAuthDataUseCase.Param(true, token, user)
                                        ).collect {
                                            it.suspendSubscribe(
                                                doOnSuccess = {
                                                    emit(
                                                        ViewResource.Success(
                                                            UserMapper.toViewParam(
                                                                user
                                                            )
                                                        )
                                                    )
                                                },
                                                doOnError = { error ->
                                                    emit(ViewResource.Error(error.exception))
                                                }
                                            )
                                        }
                                    }
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                })
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            } ?: throw IllegalStateException("Param Required")
        }
    }

    private fun mutateParam(param: RegisterParam?) = param?.apply {
        this.gender = GenderUtils.parseGender(this.gender)
    }

    data class RegisterParam(
        val birthdate: String,
        val email: String,
        var gender: String,
        val password: String,
        val username: String
    )
}
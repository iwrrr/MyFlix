package id.hwaryun.register.data.repository

import id.hwaryun.core.wrapper.DataResource
import id.hwaryun.register.data.network.datasource.RegisterDataSource
import id.hwaryun.register.data.network.model.RegisterRequest
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias RegisterDataResource = DataResource<BaseResponse<AuthResponse>>

interface RegisterRepository {
    suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String,
    ): Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(
    private val dataSource: RegisterDataSource
) : RegisterRepository, Repository() {

    override suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource> {
        return flow {
            emit(safeNetworkCall {
                dataSource.registerUser(
                    RegisterRequest(
                        birthdate = birthdate,
                        email = email,
                        gender = gender,
                        password = password,
                        username = username
                    )
                )
            })
        }
    }
}
package id.hwaryun.login.data.repository

import id.hwaryun.core.wrapper.DataResource
import id.hwaryun.login.data.network.datasource.LoginDataSource
import id.hwaryun.login.data.network.request.LoginRequest
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias LoginDataResource = DataResource<BaseResponse<AuthResponse>>

interface LoginRepository {
    suspend fun loginUser(email: String, password: String): Flow<LoginDataResource>
}

class LoginRepositoryImpl(
    private val dataSource: LoginDataSource
) : LoginRepository, Repository() {

    override suspend fun loginUser(email: String, password: String): Flow<LoginDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.loginUser(LoginRequest(email, password)) })
        }
    }
}
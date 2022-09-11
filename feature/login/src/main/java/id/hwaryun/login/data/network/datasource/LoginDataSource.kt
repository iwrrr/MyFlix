package id.hwaryun.login.data.network.datasource

import id.hwaryun.login.data.network.request.LoginRequest
import id.hwaryun.login.data.network.service.LoginFeatureApi
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse

interface LoginDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse>
}

class LoginDataSourceImpl(
    private val api: LoginFeatureApi
) : LoginDataSource {

    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse> {
        return api.loginUser(loginRequest)
    }
}
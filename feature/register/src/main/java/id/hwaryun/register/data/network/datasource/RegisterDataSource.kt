package id.hwaryun.register.data.network.datasource

import id.hwaryun.register.data.network.model.RegisterRequest
import id.hwaryun.register.data.network.service.RegisterFeatureApi
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse

interface RegisterDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}

class RegisterDataSourceImpl(
    private val api: RegisterFeatureApi
) : RegisterDataSource {

    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse> {
        return api.registerUser(registerRequest)
    }
}
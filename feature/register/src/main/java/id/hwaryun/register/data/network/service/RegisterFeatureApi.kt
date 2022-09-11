package id.hwaryun.register.data.network.service

import id.hwaryun.register.data.network.model.RegisterRequest
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterFeatureApi {

    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}
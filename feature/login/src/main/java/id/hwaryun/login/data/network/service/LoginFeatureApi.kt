package id.hwaryun.login.data.network.service

import id.hwaryun.login.data.network.request.LoginRequest
import id.hwaryun.shared.data.model.response.AuthResponse
import id.hwaryun.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginFeatureApi {

    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<AuthResponse>
}
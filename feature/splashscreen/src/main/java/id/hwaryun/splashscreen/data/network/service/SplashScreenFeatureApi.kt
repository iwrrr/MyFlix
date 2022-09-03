package id.hwaryun.splashscreen.data.network.service

import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.splashscreen.data.network.model.response.SyncResponse
import retrofit2.http.GET

interface SplashScreenFeatureApi {

    @GET("/api/v1/sync")
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}
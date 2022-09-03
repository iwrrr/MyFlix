package id.hwaryun.splashscreen.data.network.datasource

import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.splashscreen.data.network.model.response.SyncResponse
import id.hwaryun.splashscreen.data.network.service.SplashScreenFeatureApi

interface SplashScreenDataSource {
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}

class SplashScreenDataSourceImpl(val service: SplashScreenFeatureApi) : SplashScreenDataSource {

    override suspend fun doUserSync(): BaseResponse<SyncResponse> {
        return service.doUserSync()
    }
}
package id.hwaryun.splashscreen.data.repository

import id.hwaryun.core.wrapper.DataResource
import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.shared.data.repository.Repository
import id.hwaryun.splashscreen.data.network.datasource.SplashScreenDataSource
import id.hwaryun.splashscreen.data.network.model.response.SyncResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias SyncDataResponse = DataResource<BaseResponse<SyncResponse>>

interface SplashScreenRepository {
    suspend fun doUserSync(): Flow<SyncDataResponse>
}

class SplashScreenRepositoryImpl(val dataSource: SplashScreenDataSource) : SplashScreenRepository,
    Repository() {

    override suspend fun doUserSync(): Flow<SyncDataResponse> {
        return flow {
            emit(safeNetworkCall { dataSource.doUserSync() })
        }
    }
}
package id.hwaryun.shared.data.remote.datasource

import id.hwaryun.shared.data.model.request.WatchlistRequest
import id.hwaryun.shared.data.model.response.BaseResponse
import id.hwaryun.shared.data.remote.service.SharedFeatureApi

interface SharedFeatureApiDataSource {
    suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any>
    suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any>
}

class SharedFeatureApiDataSourceImpl(private val sharedFeatureApi: SharedFeatureApi) :
    SharedFeatureApiDataSource {
    override suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.addWatchlist(request)
    }

    override suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.removeWatchlist(request)
    }

}
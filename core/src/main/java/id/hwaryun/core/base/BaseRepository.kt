package id.hwaryun.core.base

import id.hwaryun.core.exception.ApiErrorException
import id.hwaryun.core.exception.NoInternetConnectionException
import id.hwaryun.core.exception.UnexpectedErrorException
import id.hwaryun.core.wrapper.DataResource
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    abstract fun <T> getErrorMessageFromApi(response: T): String

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> DataResource.Error(
                    ApiErrorException(
                        getErrorMessageFromApi(throwable.response()?.errorBody()),
                        throwable.code()
                    )
                )
                is IOException -> DataResource.Error(NoInternetConnectionException())
                else -> DataResource.Error(UnexpectedErrorException())
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(coroutine.invoke())
        } catch (e: Exception) {
            DataResource.Error(e)
        }
    }
}
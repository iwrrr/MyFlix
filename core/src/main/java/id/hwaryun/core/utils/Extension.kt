package id.hwaryun.core.utils

import android.content.Context
import id.hwaryun.core.exception.ApiErrorException
import id.hwaryun.core.exception.NoInternetConnectionException
import id.hwaryun.styling.R

fun Context.getErrorMessage(exception: Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(R.string.message_error_unknown)
    }
}
package id.hwaryun.splashscreen.data.network.model.response

import com.google.gson.annotations.SerializedName
import id.hwaryun.shared.data.model.response.UserResponse

data class SyncResponse(
    @SerializedName("user")
    val userResponse: UserResponse? = null
)

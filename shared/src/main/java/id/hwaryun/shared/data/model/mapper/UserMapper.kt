package id.hwaryun.shared.data.model.mapper

import id.hwaryun.shared.data.model.response.UserResponse
import id.hwaryun.shared.data.model.viewparam.UserViewParam
import id.hwaryun.shared.utils.ViewParamMapper

object UserMapper : ViewParamMapper<UserResponse, UserViewParam> {

    override fun toViewParam(dataObject: UserResponse?): UserViewParam = UserViewParam(
        birthdate = dataObject?.birthdate.orEmpty(),
        email = dataObject?.email.orEmpty(),
        gender = dataObject?.gender ?: -1,
        id = dataObject?.id ?: -1,
        username = dataObject?.username.orEmpty()
    )
}
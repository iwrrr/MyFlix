package id.hwaryun.shared.router

import android.content.Context
import android.content.Intent

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ActivityRouter {
    fun loginActivity(context: Context): Intent
    fun registerActivity(context: Context): Intent
    fun homeActivity(context: Context): Intent
}
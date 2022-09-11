package id.hwaryun.myflix.router

import android.content.Context
import android.content.Intent
import id.hwaryun.home.presentation.ui.HomeActivity
import id.hwaryun.login.presentation.ui.LoginActivity
import id.hwaryun.shared.router.ActivityRouter

class ActivityRouterImpl : ActivityRouter {

    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun registerActivity(context: Context): Intent {
        TODO("Not yet implemented")
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}
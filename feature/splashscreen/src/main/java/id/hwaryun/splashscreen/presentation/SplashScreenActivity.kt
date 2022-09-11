package id.hwaryun.splashscreen.presentation

import android.content.Intent
import android.widget.Toast
import id.hwaryun.core.base.BaseActivity
import id.hwaryun.shared.router.ActivityRouter
import id.hwaryun.shared.utils.ext.subscribe
import id.hwaryun.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {

    override val viewModel: SplashScreenViewModel by inject()

    val activityRouter: ActivityRouter by inject()

    override fun initView() {
        viewModel.syncUser()
    }

    override fun observeData() {
        viewModel.syncResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    if (result.payload?.first == true) {
                        navigateToHome()
                    } else {
                        navigateToLogin()
                    }
                },
                doOnError = { error ->
                    Toast.makeText(this, error.exception?.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun navigateToLogin() {
        startActivity(activityRouter.loginActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    private fun navigateToHome() {
        startActivity(activityRouter.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}
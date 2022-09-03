package id.hwaryun.splashscreen.presentation

import android.widget.Toast
import id.hwaryun.core.base.BaseActivity
import id.hwaryun.shared.utils.ext.subscribe
import id.hwaryun.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {

    override val viewModel: SplashScreenViewModel by inject()

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
        TODO("Not yet implemented")
    }

    private fun navigateToHome() {
        TODO("Not yet implemented")
    }
}
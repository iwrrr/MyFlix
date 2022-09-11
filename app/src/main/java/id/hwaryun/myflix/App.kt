package id.hwaryun.myflix

import android.app.Application
import id.hwaryun.login.di.LoginModule
import id.hwaryun.myflix.di.AppModule
import id.hwaryun.register.di.RegisterModule
import id.hwaryun.shared.di.SharedModule
import id.hwaryun.splashscreen.di.SplashScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModule.getModules() +
                        SharedModule.getModules() +
                        SplashScreenModule.getModules() +
                        LoginModule.getModules() +
                        RegisterModule.getModules()
            )
        }
    }
}
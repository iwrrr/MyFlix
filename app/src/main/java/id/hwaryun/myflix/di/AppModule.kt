package id.hwaryun.myflix.di

import id.hwaryun.core.base.BaseModule
import id.hwaryun.myflix.router.ActivityRouterImpl
import id.hwaryun.shared.router.ActivityRouter
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule : BaseModule {

    override fun getModules(): List<Module> = listOf(routers)

    val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
    }
}
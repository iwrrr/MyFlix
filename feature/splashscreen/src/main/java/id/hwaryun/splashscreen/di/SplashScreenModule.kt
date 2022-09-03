package id.hwaryun.splashscreen.di

import id.hwaryun.core.base.FeatureModule
import id.hwaryun.shared.data.remote.NetworkClient
import id.hwaryun.splashscreen.data.network.datasource.SplashScreenDataSource
import id.hwaryun.splashscreen.data.network.datasource.SplashScreenDataSourceImpl
import id.hwaryun.splashscreen.data.network.service.SplashScreenFeatureApi
import id.hwaryun.splashscreen.data.repository.SplashScreenRepository
import id.hwaryun.splashscreen.data.repository.SplashScreenRepositoryImpl
import id.hwaryun.splashscreen.domain.SyncUserUseCase
import id.hwaryun.splashscreen.presentation.SplashScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashScreenModule : FeatureModule {

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )

    override val repositories: Module = module {
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::SplashScreenViewModel)
    }

    override val dataSources: Module = module {
        single<SplashScreenDataSource> { SplashScreenDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { SyncUserUseCase(get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<SplashScreenFeatureApi> { get<NetworkClient>().create() }
    }
}
package id.hwaryun.register.di

import id.hwaryun.core.base.FeatureModule
import id.hwaryun.register.data.network.datasource.RegisterDataSource
import id.hwaryun.register.data.network.datasource.RegisterDataSourceImpl
import id.hwaryun.register.data.network.service.RegisterFeatureApi
import id.hwaryun.register.data.repository.RegisterRepository
import id.hwaryun.register.data.repository.RegisterRepositoryImpl
import id.hwaryun.register.domain.CheckRegisterFieldUseCase
import id.hwaryun.register.domain.RegisterUserUseCase
import id.hwaryun.register.presentation.ui.RegisterViewModel
import id.hwaryun.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object RegisterModule : FeatureModule {

    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }

    override val dataSources: Module = module {
        single<RegisterDataSource> { RegisterDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckRegisterFieldUseCase(Dispatchers.IO) }
        single { RegisterUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<RegisterFeatureApi> { get<NetworkClient>().create() }
    }

}
package id.hwaryun.login.di

import id.hwaryun.core.base.FeatureModule
import id.hwaryun.login.data.network.datasource.LoginDataSource
import id.hwaryun.login.data.network.datasource.LoginDataSourceImpl
import id.hwaryun.login.data.network.service.LoginFeatureApi
import id.hwaryun.login.data.repository.LoginRepository
import id.hwaryun.login.data.repository.LoginRepositoryImpl
import id.hwaryun.login.domain.CheckLoginFieldUseCase
import id.hwaryun.login.domain.LoginUserUseCase
import id.hwaryun.login.presentation.ui.LoginViewModel
import id.hwaryun.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginModule : FeatureModule {

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )

    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }

    override val dataSources: Module = module {
        single<LoginDataSource> { LoginDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckLoginFieldUseCase(Dispatchers.IO) }
        single { LoginUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<LoginFeatureApi> { get<NetworkClient>().create() }
    }
}
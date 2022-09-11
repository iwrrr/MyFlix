package id.hwaryun.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import id.hwaryun.core.base.BaseModule
import id.hwaryun.shared.data.local.datastore.UserPreferenceDataSource
import id.hwaryun.shared.data.local.datastore.UserPreferenceDataSourceImpl
import id.hwaryun.shared.data.local.datastore.UserPreferenceFactory
import id.hwaryun.shared.data.remote.NetworkClient
import id.hwaryun.shared.data.remote.datasource.SharedFeatureApiDataSource
import id.hwaryun.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import id.hwaryun.shared.data.remote.service.SharedFeatureApi
import id.hwaryun.shared.data.repository.SharedApiRepository
import id.hwaryun.shared.data.repository.SharedApiRepositoryImpl
import id.hwaryun.shared.data.repository.UserPreferenceRepository
import id.hwaryun.shared.data.repository.UserPreferenceRepositoryImpl
import id.hwaryun.shared.domain.GetUserTokenUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule : BaseModule {

    override fun getModules(): List<Module> = listOf(
        remote,
        local,
        dataSource,
        repository,
        sharedUseCase,
        common
    )

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
        single<SharedFeatureApi> { get<NetworkClient>().create() }
    }

    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val dataSource = module {
        single<UserPreferenceDataSource> { UserPreferenceDataSourceImpl(get(), get()) }
        single<SharedFeatureApiDataSource> { SharedFeatureApiDataSourceImpl(get()) }
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SharedApiRepository> { SharedApiRepositoryImpl(get()) }
    }

    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }
}
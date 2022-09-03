package id.hwaryun.core.base

import org.koin.core.module.Module

interface BaseModule {
    fun getModules(): List<Module>
}

interface FeatureModule : BaseModule {
    val repositories: Module
    val viewModels: Module
    val dataSources: Module
    val useCases: Module
    val network: Module
}
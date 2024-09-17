package com.geeks.data.di

import org.koin.dsl.module

val dataModules = module {
    includes(databaseModule, repositoryModule)
}
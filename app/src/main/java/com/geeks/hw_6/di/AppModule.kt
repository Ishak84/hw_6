package com.geeks.hw_6.di

import org.koin.dsl.module

val appModule = module {
    includes(viewModelModule)
}
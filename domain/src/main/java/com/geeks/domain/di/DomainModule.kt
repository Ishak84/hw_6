package com.geeks.domain.di

val domainModule = module {
    includes(usesCaseModule)
}
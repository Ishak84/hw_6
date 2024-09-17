package com.geeks.domain.di

val usesCaseModule = module {
    factory { DeleteTaskUseCase(get()) }
    factory { InsertTaskUseCase(get()) }
    factory { FetchTaskUseCase(get()) }
    factory { GetTaskUseCase(get()) }
}
package com.roh.idus.localweather.di

import com.roh.idus.localweather.date.repository.WeatherRepositoryImpl
import com.roh.idus.localweather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}

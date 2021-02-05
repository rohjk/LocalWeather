package com.roh.idus.localweather.di

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.datasource.WeatherServerDataSource
import com.roh.idus.localweather.date.repository.WeatherRepositoryImpl
import com.roh.idus.localweather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModuleBind {

    @Binds
    fun bindWeatherServerDataSource(impl: WeatherServerDataSource): WeatherDataSource

    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}
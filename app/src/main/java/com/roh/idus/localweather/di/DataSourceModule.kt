package com.roh.idus.localweather.di

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.datasource.WeatherServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @Binds
    @ViewModelScoped
    fun bindWeatherServerDataSource(impl: WeatherServerDataSource): WeatherDataSource

}

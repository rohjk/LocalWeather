package com.roh.idus.localweather.di

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.datasource.WeatherServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface DataSourceModule {

    @Binds
    @ActivityScoped
    fun bindWeatherServerDataSource(impl: WeatherServerDataSource): WeatherDataSource

}

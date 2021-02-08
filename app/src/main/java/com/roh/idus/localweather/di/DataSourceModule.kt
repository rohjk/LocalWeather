package com.roh.idus.localweather.di

import com.roh.idus.localweather.data.datasource.WeatherDataSource
import com.roh.idus.localweather.data.datasource.WeatherRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface DataSourceModule {

    @ActivityScoped
    @Binds
    fun bindWeatherServerDataSource(impl: WeatherRemoteDataSource): WeatherDataSource

}

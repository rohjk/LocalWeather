package com.roh.idus.localweather.di

import com.roh.idus.localweather.data.datasource.WeatherDataSource
import com.roh.idus.localweather.data.datasource.WeatherRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindWeatherServerDataSource(impl: WeatherRemoteDataSource): WeatherDataSource

}

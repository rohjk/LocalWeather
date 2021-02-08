package com.roh.idus.localweather.di

import com.roh.idus.localweather.data.repository.WeatherRepositoryImpl
import com.roh.idus.localweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {

    @ActivityScoped
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}

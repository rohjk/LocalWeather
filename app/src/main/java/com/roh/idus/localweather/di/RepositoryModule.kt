package com.roh.idus.localweather.di

import android.app.Activity
import com.roh.idus.localweather.date.repository.WeatherRepositoryImpl
import com.roh.idus.localweather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {

    @Binds
    @ActivityScoped
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}

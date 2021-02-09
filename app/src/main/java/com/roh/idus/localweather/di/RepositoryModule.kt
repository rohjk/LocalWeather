package com.roh.idus.localweather.di

import com.roh.idus.localweather.data.repository.WeatherRepositoryImpl
import com.roh.idus.localweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}

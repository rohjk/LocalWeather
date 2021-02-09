package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetLocationWeatherUseCaseTest {

    lateinit var getLocationWeatherUseCase: GetLocationWeatherUseCase

    @MockK
    lateinit var weatherRepository: WeatherRepository

    @MockK
    lateinit var locationWeather: LocationWeather

    val id = 1234L

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getLocationWeatherUseCase = GetLocationWeatherUseCase(weatherRepository)
    }

    @Test
    operator fun invoke() {
        val expected = locationWeather

        every { weatherRepository.getLocationWeather(id) } returns Observable.just(locationWeather)

        getLocationWeatherUseCase(id).test()
                .assertNoErrors()
                .assertValue { it == expected }

        verify(exactly = 1) {
            weatherRepository.getLocationWeather(id)
        }

    }
}
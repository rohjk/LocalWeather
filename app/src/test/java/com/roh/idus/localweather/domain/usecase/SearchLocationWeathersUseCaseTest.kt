package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class SearchLocationWeathersUseCaseTest {

    lateinit var searchLocationWeathersUseCase: SearchLocationWeathersUseCase

    @MockK
    lateinit var weatherRepository: WeatherRepository

    @MockK
    lateinit var location1: Location

    @MockK
    lateinit var location2: Location

    @MockK
    lateinit var locationWeather1: LocationWeather

    @MockK
    lateinit var locationWeather2: LocationWeather

    val search = "test"

    val locationId1 = 1L
    val locationId2 = 2L

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchLocationWeathersUseCase = SearchLocationWeathersUseCase(weatherRepository)

        every { location1.id } returns locationId1
        every { location2.id } returns locationId2
        every { weatherRepository.getLocationWeather(locationId1) } returns Observable.just(locationWeather1)
        every { weatherRepository.getLocationWeather(locationId2) } returns Observable.just(locationWeather2)
    }

    @Test
    operator fun invoke() {
        val expected = listOf(locationWeather1, locationWeather2)
        every { weatherRepository.getLocations(search) } returns Observable.just(listOf(location1, location2))

        searchLocationWeathersUseCase(search).test()
                .assertNoErrors()
                .assertOf { it == expected }

        verify(ordering = Ordering.SEQUENCE) {
            weatherRepository.getLocations(search)
            weatherRepository.getLocationWeather(locationId1)
            weatherRepository.getLocationWeather(locationId2)
        }
    }
}
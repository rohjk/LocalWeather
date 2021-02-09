package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class SearchLocationsUseCaseTest {

    lateinit var searchLocationsUseCase: SearchLocationsUseCase

    @MockK
    lateinit var weatherRepository: WeatherRepository

    @MockK
    lateinit var location: Location

    val search = "test"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchLocationsUseCase = SearchLocationsUseCase(weatherRepository)
    }

    @Test
    operator fun invoke() {
        val locationList = listOf(location)
        val expected = locationList

        every { weatherRepository.getLocations(search) } returns Observable.just(locationList)

        searchLocationsUseCase(search).test()
                .assertNoErrors()
                .assertValue { it == expected }

        verify(exactly = 1) {
            weatherRepository.getLocations(search)
        }

    }

}
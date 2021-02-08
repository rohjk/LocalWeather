package com.roh.idus.localweather.data.repository

import com.roh.idus.localweather.data.datasource.WeatherRemoteDataSource
import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.data.model.mapper.LocationMapper
import com.roh.idus.localweather.data.model.mapper.LocationWeatherMapper
import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.LocationWeather
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    @MockK
    lateinit var weatherRemoteDataSource: WeatherRemoteDataSource

    @MockK
    lateinit var locationWeatherMapper: LocationWeatherMapper

    @MockK
    lateinit var locationMapper: LocationMapper

    @MockK
    lateinit var location: Location

    @MockK
    lateinit var locationDTO: LocationDTO

    @MockK
    lateinit var locationWeather: LocationWeather

    @MockK
    lateinit var locationWeatherDTO: LocationWeatherDTO

    val search = "testSearch"
    val id = 1234L

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherRemoteDataSource, locationWeatherMapper, locationMapper)
    }

    @Test
    fun testGetLocation_success() {
        every { weatherRemoteDataSource.getLocations(search) } returns Observable.just(listOf(locationDTO))
        every { locationMapper.transform(locationDTO) } returns location

        val expected = listOf(location)

        weatherRepositoryImpl.getLocation(search).test()
                .assertNoErrors()
                .assertValue { it == expected }

        verify(exactly = 1) {
            weatherRemoteDataSource.getLocations(search)
            locationMapper.transform(locationDTO)
        }
    }

    @Test
    fun testGetLocation_fail() {
        val expected = Throwable("Error")

        every { weatherRemoteDataSource.getLocations(search) } returns Observable.error(expected)

        weatherRepositoryImpl.getLocation(search).test()
                .assertError(expected)

        verify(exactly = 1) {
            weatherRemoteDataSource.getLocations(search)
        }
        verify(exactly = 0) {
            locationMapper.transform(any())
        }
    }

    @Test
    fun testGetLocationWeather_success() {
        every { weatherRemoteDataSource.getLocationWeather(id) } returns Observable.just(locationWeatherDTO)
        every { locationWeatherMapper.transform(locationWeatherDTO) } returns locationWeather

        val expected = locationWeather

        weatherRepositoryImpl.getLocationWeather(id).test()
                .assertNoErrors()
                .assertValue { it == expected }

        verify(exactly = 1) {
            weatherRemoteDataSource.getLocationWeather(id)
            locationWeatherMapper.transform(locationWeatherDTO)
        }
    }

    @Test
    fun testGetLocationWeather_fail() {
        val expected = Throwable("Error")

        every { weatherRemoteDataSource.getLocationWeather(id) } returns Observable.error(expected)

        weatherRepositoryImpl.getLocationWeather(id).test()
                .assertError(expected)

        verify(exactly = 1) {
            weatherRemoteDataSource.getLocationWeather(id)
        }
        verify(exactly = 0) {
            locationWeatherMapper.transform(any())
        }
    }
}
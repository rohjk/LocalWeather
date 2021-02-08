package com.roh.idus.localweather.data.datasource

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.data.network.WeatherServiceApi
import com.roh.idus.localweather.error.HttpRequestFailException
import com.roh.idus.localweather.error.NullResponseBodyException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class WeatherRemoteDataSourceTest {

    lateinit var weatherRemoteDataSource: WeatherRemoteDataSource

    @MockK
    lateinit var weatherServiceApi: WeatherServiceApi
    val scheduler = Schedulers.trampoline()

    @MockK
    lateinit var locationDTO: LocationDTO
    @MockK
    lateinit var locationWeatherDTO: LocationWeatherDTO

    private val errorResponseBody = ResponseBody.create(
            MediaType.get("application/json; charset=UTF-8"),
            "[text={\"message\":\"record not found\"}\\n]"
    )

    private val search = "TEST"
    private val id = 1234L

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherRemoteDataSource = WeatherRemoteDataSource(weatherServiceApi, scheduler)
    }

    @Test
    fun testGetLocations_success() {
        val expectedResult = listOf(locationDTO)

        val response = Response.success(expectedResult)
        every { weatherServiceApi.getLocations(search) } returns Observable.just(response)

        weatherRemoteDataSource.getLocations(search).test()
                .assertNoErrors()
                .assertValue { it == expectedResult }

        verify {
            weatherServiceApi.getLocations(search)
        }
    }

    @Test
    fun testGetLocations_fail_response_fail() {
        val response = Response.error<List<LocationDTO>>(
                400,
                errorResponseBody
        )

        every { weatherServiceApi.getLocations(search) } returns Observable.just(response)

        weatherRemoteDataSource.getLocations(search).test()
                .assertError(HttpRequestFailException::class.java)

        verify {
            weatherServiceApi.getLocations(search)
        }
    }

    @Test
    fun testGetLocations_fail_null_response_body() {
        val expectedResult: List<LocationDTO>? = null
        val response = Response.success(expectedResult)
        every { weatherServiceApi.getLocations(search) } returns Observable.just(response)

        weatherRemoteDataSource.getLocations(search).test()
                .assertError(NullResponseBodyException::class.java)

        verify {
            weatherServiceApi.getLocations(search)
        }
    }

    @Test
    fun getLocationWeather_success() {
        val expectedResult = locationWeatherDTO

        val response = Response.success(expectedResult)
        every { weatherServiceApi.getWeather(id) } returns Observable.just(response)

        weatherRemoteDataSource.getLocationWeather(id).test()
                .assertNoErrors()
                .assertValue { it == expectedResult }

        verify {
            weatherServiceApi.getWeather(id)
        }
    }

    @Test
    fun getLocationWeather_fail_response_fail() {
        val response = Response.error<LocationWeatherDTO>(
                400,
                errorResponseBody
        )

        every { weatherServiceApi.getWeather(id) } returns Observable.just(response)

        weatherRemoteDataSource.getLocationWeather(id).test()
                .assertError(HttpRequestFailException::class.java)

        verify {
            weatherServiceApi.getWeather(id)
        }
    }

    @Test
    fun getLocationWeather_fail_null_response_body() {
        val expectedResult: LocationWeatherDTO? = null

        val response = Response.success(expectedResult)

        every { weatherServiceApi.getWeather(id) } returns Observable.just(response)

        weatherRemoteDataSource.getLocationWeather(id).test()
                .assertError(NullResponseBodyException::class.java)

        verify {
            weatherServiceApi.getWeather(id)
        }
    }
}
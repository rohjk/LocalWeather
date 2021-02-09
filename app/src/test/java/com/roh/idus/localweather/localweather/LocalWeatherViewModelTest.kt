package com.roh.idus.localweather.localweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.roh.idus.localweather.R
import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.usecase.GetLocationWeatherUseCase
import com.roh.idus.localweather.domain.usecase.SearchLocationWeathersUseCase
import com.roh.idus.localweather.error.HttpRequestFailException
import com.roh.idus.localweather.error.NullResponseBodyException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LocalWeatherViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val scheduler = Schedulers.trampoline()
    val disposable = CompositeDisposable()

    lateinit var localWeatherViewModel: LocalWeatherViewModel

    @MockK
    lateinit var searchLocationWeatherUseCase: SearchLocationWeathersUseCase

    @MockK
    lateinit var locationWeather: LocationWeather

    val search = "test"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localWeatherViewModel = LocalWeatherViewModel(searchLocationWeatherUseCase, scheduler, disposable)
    }

    @Test
    fun testSearch_success() {
        val expectedWeathers = listOf(locationWeather)
        val expectedDataLoading = false
        val expectedToastTextId = null

        every { searchLocationWeatherUseCase(search) } returns Single.just(listOf(locationWeather))

        localWeatherViewModel.search(search)

        val actualWeathers = localWeatherViewModel.locationWeathers.value
        val actualDataLoading = localWeatherViewModel.dataLoading.value
        val actualToastTextId = localWeatherViewModel.toastTextId.value

        assertEquals(expectedWeathers, actualWeathers)
        assertEquals(expectedDataLoading, actualDataLoading)
        assertEquals(expectedToastTextId, actualToastTextId)
    }

    @Test
    fun testSearch_fail_HttpRequestFailException() {
        val expectedWeathers = emptyList<LocationWeather>()
        val expectedDataLoading = false
        val expectedToastTextId = R.string.http_requst_fail_error_message

        every { searchLocationWeatherUseCase(search) } returns Single.error(HttpRequestFailException("error"))

        localWeatherViewModel.search(search)

        val actualWeathers = localWeatherViewModel.locationWeathers.value
        val actualDataLoading = localWeatherViewModel.dataLoading.value
        val actualToastTextId = localWeatherViewModel.toastTextId.value

        assertEquals(expectedWeathers, actualWeathers)
        assertEquals(expectedDataLoading, actualDataLoading)
        assertEquals(expectedToastTextId, actualToastTextId)
    }

    @Test
    fun testSearch_fail_NullResponseBodyException() {
        val expectedWeathers = emptyList<LocationWeather>()
        val expectedDataLoading = false
        val expectedToastTextId = R.string.null_response_body_error_message

        every { searchLocationWeatherUseCase(search) } returns Single.error(NullResponseBodyException("error"))

        localWeatherViewModel.search(search)

        val actualWeathers = localWeatherViewModel.locationWeathers.value
        val actualDataLoading = localWeatherViewModel.dataLoading.value
        val actualToastTextId = localWeatherViewModel.toastTextId.value

        assertEquals(expectedWeathers, actualWeathers)
        assertEquals(expectedDataLoading, actualDataLoading)
        assertEquals(expectedToastTextId, actualToastTextId)
    }

    @Test
    fun testSearch_fail_others() {
        val expectedWeathers = emptyList<LocationWeather>()
        val expectedDataLoading = false
        val expectedToastTextId = R.string.default_error_message

        every { searchLocationWeatherUseCase(search) } returns Single.error(Throwable("error"))

        localWeatherViewModel.search(search)

        val actualWeathers = localWeatherViewModel.locationWeathers.value
        val actualDataLoading = localWeatherViewModel.dataLoading.value
        val actualToastTextId = localWeatherViewModel.toastTextId.value

        assertEquals(expectedWeathers, actualWeathers)
        assertEquals(expectedDataLoading, actualDataLoading)
        assertEquals(expectedToastTextId, actualToastTextId)
    }
}
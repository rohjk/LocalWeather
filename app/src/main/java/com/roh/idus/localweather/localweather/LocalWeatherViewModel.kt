package com.roh.idus.localweather.localweather

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roh.idus.localweather.R
import com.roh.idus.localweather.di.MainScheduler
import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.usecase.SearchWeatherInfosUseCase
import com.roh.idus.localweather.error.HttpRequestFailException
import com.roh.idus.localweather.error.NullResponseBodyException
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherViewModel @ViewModelInject constructor(
        private val searchWeatherInfosUseCase: SearchWeatherInfosUseCase,
        @MainScheduler private val scheduler: Scheduler,
        private val disposable: CompositeDisposable
) : ViewModel() {

    companion object {
        private val TAG = LocalWeatherViewModel::class.java.simpleName
    }

    private val _search = MutableLiveData<String>()

    private val _locationWeathers = MutableLiveData<List<LocationWeather>>(emptyList())
    val locationWeathers: LiveData<List<LocationWeather>>
        get() = _locationWeathers

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<Int>()
    val toastText: LiveData<Int>
        get() = _toastText


    fun search(search: String) {
        if (_dataLoading.value == true || search == _search.value) {
            return
        }
        _search.value = search
        getLocationWeathers()
    }

    fun refresh() {
        if (_dataLoading.value == true) {
            return
        }
        getLocationWeathers()
    }

    private fun getLocationWeathers() {
        _search.value?.let { search ->
            _dataLoading.value = true
            disposable.add(
                    searchWeatherInfosUseCase(search).observeOn(scheduler).doFinally {
                        _dataLoading.value = false
                    }.subscribe({
                        _locationWeathers.value = it
                    }, { error ->
                        Log.e(TAG, "Failure to get Weather : ${error}")
                        var errorMessage = R.string.default_error_message
                        when(error) {
                            is HttpRequestFailException ->
                                errorMessage = R.string.http_requst_fail_error_message
                            is NullResponseBodyException ->
                                errorMessage = R.string.null_response_body_error_message
                        }
                        _toastText.value = errorMessage
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
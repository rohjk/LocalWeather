package com.roh.idus.localweather.localweather

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roh.idus.localweather.di.MainScheduler
import com.roh.idus.localweather.domain.model.WeatherInfo
import com.roh.idus.localweather.domain.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherViewModel @ViewModelInject constructor(
        private val weatherRepository: WeatherRepository,
        @MainScheduler private val scheduler: Scheduler,
        private val disposable: CompositeDisposable
) : ViewModel() {

    private val _search = MutableLiveData<String>()

    private val _weatherInfos = MutableLiveData<List<WeatherInfo>>(emptyList())
    val weatherInfos: LiveData<List<WeatherInfo>>
        get() = _weatherInfos

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText


    fun search(search: String) {
        if (_dataLoading.value == true || search == _search.value) {
            return
        }
        _search.value = search
        getWeathers()
    }

    fun refresh() {
        if (_dataLoading.value == true) {
            return
        }
        getWeathers()
    }

    private fun getWeathers() {
        _search.value?.let { search ->
            _dataLoading.value = true
            disposable.add(
                weatherRepository.getWeather(search).observeOn(scheduler).doFinally {
                    _dataLoading.value = false
                }.subscribe({
                    _weatherInfos.value = it
                }, { error ->
                    Log.e("Jake", "Failure to get Weather : ${error.message.toString()}")
                    _toastText.value = error.message
                })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
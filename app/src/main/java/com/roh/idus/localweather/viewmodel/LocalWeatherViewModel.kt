package com.roh.idus.localweather.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roh.idus.localweather.di.MainScheduler
import com.roh.idus.localweather.domain.WeatherInfo
import com.roh.idus.localweather.domain.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherViewModel @ViewModelInject constructor(
        private val weatherRepository: WeatherRepository,
        @MainScheduler private val scheduler: Scheduler,
        private val disposable: CompositeDisposable
) : ViewModel() {

    private val _search = MutableLiveData<String>()

    private val _weatherInfos = MutableLiveData<List<WeatherInfo>>()
    val weatherInfos: LiveData<List<WeatherInfo>>
        get() = _weatherInfos

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String>
        get() = _snackbarText


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
        _search.value?.let {
            _dataLoading.value = true
            disposable.add(
                weatherRepository.getWeathersBySearch("se").observeOn(scheduler).doFinally {
                    _dataLoading.value = false
                }.subscribe({
                    _weatherInfos.value = it
                }, {
                    Log.e("Jake", it.message.toString())
                })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
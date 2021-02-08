package com.roh.idus.localweather.localweather

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roh.idus.localweather.di.MainScheduler
import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.usecase.SearchWeatherInfosUseCase
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
                    searchWeatherInfosUseCase(search).observeOn(scheduler).doFinally {
                        _dataLoading.value = false
                    }.subscribe({
                        _locationWeathers.value = it
                    }, { error ->
                        Log.e(TAG, "Failure to get Weather : ${error.message.toString()}")
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
package com.roh.idus.localweather.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.roh.idus.localweather.di.MainScheduler
import com.roh.idus.localweather.domain.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherViewModel @ViewModelInject constructor(
        private val weatherRepository: WeatherRepository,
        @MainScheduler private val scheduler: Scheduler,
        private val disposable: CompositeDisposable
) : ViewModel() {

    fun refresh() {
        getWeathers()
    }

    private fun getWeathers() {
        disposable.add(
                weatherRepository.getWeathersBySearch("se").observeOn(scheduler).subscribe({
                    Log.d("Jake", it.size.toString())
                }, {
                    Log.e("Jake", it.message.toString())
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
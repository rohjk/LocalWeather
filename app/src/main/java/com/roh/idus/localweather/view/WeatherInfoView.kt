package com.roh.idus.localweather.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.roh.idus.localweather.databinding.ViewWeatherInfoBinding
import com.roh.idus.localweather.domain.Weather

class WeatherInfoView constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private val binding: ViewWeatherInfoBinding

    init {
        val inflater = LayoutInflater.from(context)
        this.binding = ViewWeatherInfoBinding.inflate(inflater, this, true)
    }

    fun setWeather(weather: Weather) {
        binding.weather = weather
    }

}
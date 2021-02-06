package com.roh.idus.localweather.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.roh.idus.localweather.databinding.ViewWeatherInfoBinding
import com.roh.idus.localweather.domain.Weather

class WeatherInfoView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val binding: ViewWeatherInfoBinding

    init {
        this.binding = ViewWeatherInfoBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    fun setWeather(weather: Weather) {
        binding.weather = weather
    }

}
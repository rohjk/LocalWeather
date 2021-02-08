package com.roh.idus.localweather.utils

import com.roh.idus.localweather.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IconUrlGenerator @Inject constructor() {
    fun getPngIconUrl(iconType: String): String = BuildConfig.BASE_HOST + "static/img/weather/png/${iconType}.png"
}
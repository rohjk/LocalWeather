package com.roh.idus.localweather.date.network

import com.roh.idus.localweather.date.model.Location
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherServiceApi {

    @GET("location/search")
    fun getLocations(@Query("query") search: String): Observable<Response<List<Location>>>

    @GET("location/{id}")
    fun getWeather(@Path("id") id: Long): Observable<Response<WeatherResponse>>
}
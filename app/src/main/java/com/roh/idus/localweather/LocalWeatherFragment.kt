package com.roh.idus.localweather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.roh.idus.localweather.databinding.FragmentLocalWeatherBinding
import com.roh.idus.localweather.domain.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@AndroidEntryPoint
class LocalWeatherFragment : Fragment() {

    private lateinit var binding: FragmentLocalWeatherBinding

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentLocalWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        weatherRepository.getWeathersBySearch("se").observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d("Jake", it.size.toString())
        },{
            Log.e("Jake", it.message.toString())
        })
    }

}
package com.roh.idus.localweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.roh.idus.localweather.databinding.FragmentLocalWeatherBinding

class LocalWeatherFragment : Fragment() {

    private lateinit var binding: FragmentLocalWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentLocalWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

}
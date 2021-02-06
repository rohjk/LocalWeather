package com.roh.idus.localweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.roh.idus.localweather.databinding.FragmentLocalWeatherBinding
import com.roh.idus.localweather.viewmodel.LocalWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalWeatherFragment : Fragment() {

    private val viewModel: LocalWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLocalWeatherBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@LocalWeatherFragment
            viewModel = this@LocalWeatherFragment.viewModel
        }
        viewModel.search("se")
        return binding.root
    }

}
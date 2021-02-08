package com.roh.idus.localweather.localweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.roh.idus.localweather.databinding.FragmentLocalWeatherBinding
import com.roh.idus.localweather.localweather.adapter.WeatherInfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalWeatherFragment : Fragment() {

    private val viewModel: LocalWeatherViewModel by viewModels()
    private val adapter = WeatherInfoAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLocalWeatherBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@LocalWeatherFragment
            viewModel = this@LocalWeatherFragment.viewModel
            adapter = this@LocalWeatherFragment.adapter
        }

        subscribeUI()

        viewModel.search("se")
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.toastText.observe(viewLifecycleOwner) { stringId ->
            Toast.makeText(activity, getString(stringId), Toast.LENGTH_SHORT).show()
        }
    }

}
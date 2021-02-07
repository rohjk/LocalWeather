package com.roh.idus.localweather.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.roh.idus.localweather.domain.model.WeatherInfo
import com.roh.idus.localweather.localweather.adapter.WeatherInfoAdapter

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("adapterWeatherInfoList")
fun bindAdapterPokemonList(view: RecyclerView, list: List<WeatherInfo>?) {
    list?.let { itemList ->
        view.adapter?.apply {
            (this as WeatherInfoAdapter).addHeaderAndSumbitList(itemList)
        }
    }
}
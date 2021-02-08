package com.roh.idus.localweather.localweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.idus.localweather.databinding.HeaderWeatherListBinding
import com.roh.idus.localweather.databinding.ItemWeatherListBinding
import com.roh.idus.localweather.domain.model.WeatherInfo

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class WeatherInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<DataItem> = emptyList()

    fun addHeaderAndSumbitList(list: List<WeatherInfo>) {
        val itemList: MutableList<DataItem> = mutableListOf()

        if (!list.isEmpty()) {
            itemList.add(DataItem.Header())
            for (i in list) {
                itemList.add(DataItem.weatherInfoItem(i))
            }
        }
        this.items = itemList
        notifyDataSetChanged()
    }

    class WeatherInfoViewHolder constructor(val binding: ItemWeatherListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherInfo) {
            binding.apply {
                viewModel = WeatherInfoViewModel(item)
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): WeatherInfoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherListBinding.inflate(layoutInflater, parent, false)
                return WeatherInfoViewHolder(binding)
            }
        }
    }

    class HeaderViewHolder constructor(val binding: HeaderWeatherListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                        HeaderWeatherListBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> WeatherInfoViewHolder.from(parent)
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.weatherInfoItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherInfoViewHolder -> {
                val weatherInfoItem = items[position] as DataItem.weatherInfoItem
                holder.bind(weatherInfoItem.weatherInfo)
            }
        }
    }

    sealed class DataItem {
        data class weatherInfoItem(val weatherInfo: WeatherInfo) : DataItem()
        data class Header(val id: Long = System.currentTimeMillis()) : DataItem()
    }

}
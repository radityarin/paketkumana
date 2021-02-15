package com.radityarin.paketkumana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radityarin.paketkumana.R
import com.radityarin.paketkumana.data.source.remote.response.cekresi.History
import com.radityarin.paketkumana.databinding.ItemProgressBinding
import com.radityarin.paketkumana.domain.model.Courier
import java.util.ArrayList

class StatusAdapter : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    private var listData = ArrayList<History>()

    fun setData(newListData: List<History>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProgressBinding.bind(itemView)

        fun bind(data: History) {
            with(binding) {
                tvDateStatus.text = data.date
                tvDescriptionStatus.text = data.desc
            }
        }

    }
}
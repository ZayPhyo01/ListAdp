package com.ui.listadp

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SampleAdapter(
    val onClick: (String) -> Unit
) : ListAdapter<SampleModel , SampleViewHolder>(
    SampleDiffUtils()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_sample, parent, false)
        ) {
            onClick(it)
        }
    }



    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(getItem(position))
        Log.d("onBindViewHolder", position.toString())
    }

}


class SampleViewHolder(view: View, val onClick: (String) -> Unit) : ViewHolder(view) {
    val id: TextView = view.findViewById(R.id.tvId)
    val name: TextView = view.findViewById(R.id.tvName)

    fun bind(model: SampleModel) {
        id.text = model.id
        name.text = model.name
        itemView.setOnClickListener {
            onClick.invoke(model.id)
        }
    }
}

class SampleDiffUtils : DiffUtil.ItemCallback<SampleModel>() {
    override fun areItemsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
        return oldItem == newItem
    }

}

data class SampleModel(
    val id: String,
    val name: String
)
package com.example.studyapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.databinding.ItemRowBinding

class RVAdapter(private val activity: Activity, private val items: ArrayList<Data>):
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = items[position].title
            tvDetails.text = items[position].description
            cvCard.setOnClickListener { CustomAlertDialog(activity, items[position].title, items[position].details) }
        }
    }

    override fun getItemCount() = items.size
}
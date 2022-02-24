package com.example.samplediffutil.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplediffutil.common.model.SampleUser
import com.example.samplediffutil.databinding.ItemSampleBinding
import com.example.samplediffutil.viewmodel.MainViewModel

class SampleAdapter(private val viewModel: MainViewModel) : ListAdapter<SampleUser, SampleAdapter.ViewHolder>(SampleDataDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSampleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SampleUser) {
            binding.viewModel = viewModel
            binding.sampleData = item
        }
    }

    class SampleDataDiff : DiffUtil.ItemCallback<SampleUser>() {
        override fun areItemsTheSame(oldItem: SampleUser, newItem: SampleUser) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: SampleUser, newItem: SampleUser) =
            oldItem == newItem
    }
}
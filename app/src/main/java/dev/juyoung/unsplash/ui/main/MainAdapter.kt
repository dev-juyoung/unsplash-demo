package dev.juyoung.unsplash.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.juyoung.unsplash.data.entities.PhotoSummary

class MainAdapter(private val viewModel: MainViewModel) :
    ListAdapter<PhotoSummary, MainViewHolder>(PhotoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }
}

class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoSummary>() {
    override fun areItemsTheSame(oldItem: PhotoSummary, newItem: PhotoSummary): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoSummary, newItem: PhotoSummary): Boolean {
        return oldItem == newItem
    }
}

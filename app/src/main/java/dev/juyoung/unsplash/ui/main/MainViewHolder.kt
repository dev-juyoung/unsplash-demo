package dev.juyoung.unsplash.ui.main

import android.view.ViewGroup
import dev.juyoung.unsplash.R
import dev.juyoung.unsplash.data.entities.PhotoSummary
import dev.juyoung.unsplash.databinding.PhotoItemBinding
import dev.juyoung.unsplash.ui.base.ViewBindingHolder

class MainViewHolder(parent: ViewGroup) :
    ViewBindingHolder<PhotoItemBinding, MainViewModel, PhotoSummary>(parent, R.layout.item_photo) {
    override fun bind(viewModel: MainViewModel, data: PhotoSummary) {
        binding?.let {
            it.item = data
            it.viewModel = viewModel
            it.executePendingBindings()
        }
    }
}

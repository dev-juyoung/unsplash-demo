package dev.juyoung.unsplash.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.juyoung.unsplash.data.entities.PhotoSummary
import dev.juyoung.unsplash.ui.main.MainAdapter

@BindingAdapter("app:photos")
fun setPhotos(view: RecyclerView, photos: List<PhotoSummary>?) {
    photos?.let {
        (view.adapter as MainAdapter).submitList(it)
    }
}

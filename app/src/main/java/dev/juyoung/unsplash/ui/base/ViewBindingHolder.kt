package dev.juyoung.unsplash.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class ViewBindingHolder<B : ViewDataBinding, VM : BaseViewModel, T>(
    parent: ViewGroup,
    @LayoutRes resourceId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
) {
    protected val binding = DataBindingUtil.bind<B>(itemView)

    abstract fun bind(viewModel: VM, data: T)
}

package dev.juyoung.unsplash.ui.detail

import dev.juyoung.unsplash.R
import dev.juyoung.unsplash.databinding.ExifBottomSheetViewBinding
import dev.juyoung.unsplash.ui.base.ViewBindingBottomSheet
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ExifBottomSheet : ViewBindingBottomSheet<ExifBottomSheetViewBinding, DetailViewModel>(
    R.layout.bottom_sheet_exif
) {
    override val viewModel: DetailViewModel by sharedViewModel()

    companion object {
        fun create(): ExifBottomSheet = ExifBottomSheet().apply {

        }
    }
}

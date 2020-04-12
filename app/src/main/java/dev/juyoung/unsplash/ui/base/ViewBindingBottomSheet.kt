package dev.juyoung.unsplash.ui.base

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.juyoung.unsplash.BR

abstract class ViewBindingBottomSheet<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val contentLayoutId: Int
) : BottomSheetDialogFragment() {
    protected abstract val viewModel: VM
    protected lateinit var viewBinding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DataBindingUtil.inflate<B>(
            inflater,
            contentLayoutId,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.setVariable(BR.viewModel, viewModel)
            viewBinding = it
        }

        return viewBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // new BottomSheetDialog(this.getContext(), this.getTheme());
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet: View =
                    findViewById(com.google.android.material.R.id.design_bottom_sheet)
                val bottomSheetBehavior: BottomSheetBehavior<View> =
                    BottomSheetBehavior.from(bottomSheet)

                bottomSheet.setBackgroundColor(Color.TRANSPARENT)
                with(bottomSheetBehavior) {
                    state = BottomSheetBehavior.STATE_EXPANDED
                    setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                        override fun onSlide(p0: View, p1: Float) {
                            /* no-op */
                        }

                        override fun onStateChanged(view: View, newState: Int) {
                            if (newState == BottomSheetBehavior.STATE_COLLAPSED) dismiss()
                        }
                    })
                }
            }
        }
    }
}

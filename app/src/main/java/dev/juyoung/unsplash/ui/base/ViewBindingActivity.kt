package dev.juyoung.unsplash.ui.base

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dev.juyoung.unsplash.BR
import dev.juyoung.unsplash.EventObserver
import timber.log.Timber

abstract class ViewBindingActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val contentLayoutId: Int
) : AppCompatActivity() {
    protected abstract val viewModel: VM
    protected lateinit var viewBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<B>(this, contentLayoutId).also {
            it.lifecycleOwner = this
            it.setVariable(BR.viewModel, viewModel)
            viewBinding = it
        }

        setupView()
        observe()
    }

    protected fun setupActionBar(
        toolbar: Toolbar,
        titleColor: Int = Color.WHITE,
        hasArrowBack: Boolean = true,
        onClick: () -> Unit = {}
    ) {
        toolbar.setTitleTextColor(titleColor)
        setSupportActionBar(toolbar)
        if (hasArrowBack) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onClick.invoke()
            }
        }
    }

    open fun setupView() {
        /* no-op */
    }

    open fun observe() {
        with(viewModel) {
            initialized()
            dataLoading.observe(this@ViewBindingActivity, EventObserver {
                Timber.i("dataLoading::$it")
            })
            error.observe(this@ViewBindingActivity, EventObserver { error ->
                throw error
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.unbind()
    }
}

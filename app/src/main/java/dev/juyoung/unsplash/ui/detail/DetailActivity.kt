package dev.juyoung.unsplash.ui.detail

import dev.juyoung.unsplash.EventObserver
import dev.juyoung.unsplash.R
import dev.juyoung.unsplash.databinding.DetailViewBinding
import dev.juyoung.unsplash.ui.base.ViewBindingActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity :
    ViewBindingActivity<DetailViewBinding, DetailViewModel>(R.layout.activity_detail) {

    private val id: String by lazy { intent.getStringExtra(EXTRA_KEY) }

    override val viewModel: DetailViewModel by viewModel { parametersOf(id) }

    override fun setupView() {
        super.setupView()
        setupActionBar(viewBinding.toolbar, onClick = { finish() })
    }

    override fun observe() {
        super.observe()

        with(viewModel) {
            infoButtonClicked.observe(this@DetailActivity, EventObserver {
                ExifBottomSheet.create().show(supportFragmentManager, "ExifBottomSheet")
            })
        }
    }

    companion object {
        const val EXTRA_KEY = "DATA_KEY"
    }
}

package dev.juyoung.unsplash.ui.main

import android.content.Intent
import dev.juyoung.unsplash.EventObserver
import dev.juyoung.unsplash.R
import dev.juyoung.unsplash.databinding.MainViewBinding
import dev.juyoung.unsplash.ui.base.ViewBindingActivity
import dev.juyoung.unsplash.ui.detail.DetailActivity
import dev.juyoung.unsplash.utils.EndlessScrollListener
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ViewBindingActivity<MainViewBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()
    private val adapter by lazy { MainAdapter(viewModel) }

    override fun setupView() {
        super.setupView()
        setupActionBar(viewBinding.toolbar, hasArrowBack = false)

        viewBinding.photoList.run {
            adapter = this@MainActivity.adapter
            addOnScrollListener(object : EndlessScrollListener(layoutManager!!) {
                override fun onNext(nextPage: Int) {
                    viewModel.getPhotos(nextPage)
                }
            })
        }
    }

    override fun observe() {
        super.observe()

        with(viewModel) {
            itemClicked.observe(this@MainActivity, EventObserver {
                Intent(this@MainActivity, DetailActivity::class.java).run {
                    putExtra(DetailActivity.EXTRA_KEY, it.id)
                    startActivity(this)
                }
            })
        }
    }
}

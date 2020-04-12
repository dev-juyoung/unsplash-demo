package dev.juyoung.unsplash.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.juyoung.unsplash.Event
import dev.juyoung.unsplash.data.entities.PhotoSummary
import dev.juyoung.unsplash.data.source.UnsplashDataSource
import dev.juyoung.unsplash.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: UnsplashDataSource) : BaseViewModel() {
    private val _photos = MutableLiveData<List<PhotoSummary>>()
    val photos: LiveData<List<PhotoSummary>> = _photos

    private val _itemClicked = MutableLiveData<Event<PhotoSummary>>()
    val itemClicked: LiveData<Event<PhotoSummary>> = _itemClicked

    override fun initialized() {
        super.initialized()
        getPhotos(1)
    }

    fun getPhotos(page: Int) {
        repository.getPhotos(page, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _dataLoading.value = Event(true)
            }
            .doFinally {
                _dataLoading.value = Event(false)
            }
            .subscribe(
                { results ->
                    val previousValue = _photos.value ?: emptyList()
                    _photos.value = previousValue + results
                },
                { error ->
                    _error.value = Event(error)
                }
            )
            .addTo(compositeDisposable)
    }

    fun onItemClicked(item: PhotoSummary) {
        _itemClicked.value = Event(item)
    }

    companion object {
        const val PER_PAGE = 30
    }
}

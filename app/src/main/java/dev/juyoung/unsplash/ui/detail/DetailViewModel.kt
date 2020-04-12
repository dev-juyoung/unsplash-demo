package dev.juyoung.unsplash.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.juyoung.unsplash.Event
import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.source.UnsplashDataSource
import dev.juyoung.unsplash.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class DetailViewModel(
    private val id: String,
    private val repository: UnsplashDataSource
) : BaseViewModel() {
    private val _photo = MutableLiveData<PhotoDetail>()
    val photo: LiveData<PhotoDetail> = _photo

    private val _infoButtonClicked = MutableLiveData<Event<Unit>>()
    val infoButtonClicked: LiveData<Event<Unit>> = _infoButtonClicked

    override fun initialized() {
        super.initialized()

        repository.getPhoto(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _dataLoading.value = Event(true)
            }
            .doFinally {
                _dataLoading.value = Event(false)
            }
            .subscribe(
                { result -> _photo.value = result },
                { error -> _error.value = Event(error) }
            )
            .addTo(compositeDisposable)
    }

    fun onInfoButtonClicked() {
        _infoButtonClicked.value = Event(Unit)
    }
}

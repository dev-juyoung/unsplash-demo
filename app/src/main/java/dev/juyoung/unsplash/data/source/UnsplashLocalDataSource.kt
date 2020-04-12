package dev.juyoung.unsplash.data.source

import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.entities.PhotoSummary
import io.reactivex.Single

class UnsplashLocalDataSource : UnsplashDataSource {
    override fun getPhotos(page: Int, perPage: Int): Single<List<PhotoSummary>> = Single.never()

    override fun getPhoto(id: String): Single<PhotoDetail> = Single.never()
}
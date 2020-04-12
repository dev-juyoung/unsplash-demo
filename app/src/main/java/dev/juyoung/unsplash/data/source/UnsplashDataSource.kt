package dev.juyoung.unsplash.data.source

import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.entities.PhotoSummary
import io.reactivex.Single

interface UnsplashDataSource {
    fun getPhotos(page: Int, perPage: Int): Single<List<PhotoSummary>>

    fun getPhoto(id: String): Single<PhotoDetail>
}

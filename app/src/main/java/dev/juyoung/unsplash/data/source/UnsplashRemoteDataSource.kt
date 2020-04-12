package dev.juyoung.unsplash.data.source

import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.entities.PhotoSummary
import dev.juyoung.unsplash.data.network.services.UnsplashService
import io.reactivex.Single

class UnsplashRemoteDataSource(private val service: UnsplashService) : UnsplashDataSource {
    override fun getPhotos(page: Int, perPage: Int): Single<List<PhotoSummary>> =
        service.getPhotos(page, perPage)

    override fun getPhoto(id: String): Single<PhotoDetail> = service.getPhoto(id)
}

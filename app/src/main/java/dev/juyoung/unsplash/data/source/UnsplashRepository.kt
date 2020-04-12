package dev.juyoung.unsplash.data.source

import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.entities.PhotoSummary
import io.reactivex.Single

class UnsplashRepository(
    private val localDataSource: UnsplashDataSource,
    private val remoteDataSource: UnsplashDataSource
) : UnsplashDataSource {
    override fun getPhotos(page: Int, perPage: Int): Single<List<PhotoSummary>> =
        remoteDataSource.getPhotos(page, perPage)

    override fun getPhoto(id: String): Single<PhotoDetail> = remoteDataSource.getPhoto(id)
}

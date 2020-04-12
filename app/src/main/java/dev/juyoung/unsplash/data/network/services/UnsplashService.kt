package dev.juyoung.unsplash.data.network.services

import dev.juyoung.unsplash.data.entities.PhotoDetail
import dev.juyoung.unsplash.data.entities.PhotoSummary
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {
    @GET("/photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<PhotoSummary>>

    @GET("/photos/{id}")
    fun getPhoto(@Path("id") id: String): Single<PhotoDetail>
}

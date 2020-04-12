package dev.juyoung.unsplash.data.entities

import com.google.gson.annotations.SerializedName
import dev.juyoung.unsplash.extensions.toDate
import java.util.*

data class PhotoDetail(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("color")
    val color: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("exif")
    val exif: Exif,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int
) {
    val published: Date?
        get() = createdAt.toDate
}

data class Exif(
    @SerializedName("make")
    val make: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("exposure_time")
    val exposureTime: String?,
    @SerializedName("aperture")
    val aperture: String?,
    @SerializedName("focal_length")
    val focalLength: String?,
    @SerializedName("iso")
    val iso: Int?
)

data class Location(
    @SerializedName("title")
    val title: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("position")
    val position: Position
)

data class Position(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
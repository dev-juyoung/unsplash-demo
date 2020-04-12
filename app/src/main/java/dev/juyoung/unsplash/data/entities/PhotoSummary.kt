package dev.juyoung.unsplash.data.entities

import com.google.gson.annotations.SerializedName

data class PhotoSummary(
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
    @SerializedName("likes")
    val likes: Int
)
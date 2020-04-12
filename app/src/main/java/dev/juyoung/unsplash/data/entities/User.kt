package dev.juyoung.unsplash.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImage
)

data class ProfileImage(
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("large")
    val large: String
)
package com.example.podcast.network.response
import com.google.gson.annotations.SerializedName


data class GetCastDetailResponse(
    @SerializedName("collection")
    val collection: Collection = Collection()
)

data class Collection(
    @SerializedName("artistId")
    val artistId: Int = 0,
    @SerializedName("artistName")
    val artistName: String = "",
    @SerializedName("artworkUrl100")
    val artworkUrl100: String = "",
    @SerializedName("artworkUrl600")
    val artworkUrl600: String = "",
    @SerializedName("collectionId")
    val collectionId: Int = 0,
    @SerializedName("collectionName")
    val collectionName: String = "",
    @SerializedName("contentFeed")
    val contentFeed: List<ContentFeed> = listOf(),
    @SerializedName("country")
    val country: String = "",
    @SerializedName("genreIds")
    val genreIds: String = "",
    @SerializedName("genres")
    val genres: String = "",
    @SerializedName("releaseDate")
    val releaseDate: String = ""
)

data class ContentFeed(
    @SerializedName("contentUrl")
    val contentUrl: String = "",
    @SerializedName("desc")
    val desc: String = "",
    @SerializedName("publishedDate")
    val publishedDate: String = "",
    @SerializedName("title")
    val title: String = ""
)
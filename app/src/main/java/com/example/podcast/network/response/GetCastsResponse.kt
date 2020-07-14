package com.example.podcast.network.response
import com.google.gson.annotations.SerializedName


data class GetCastsResponse(
    @SerializedName("podcast")
    val podcast: List<Podcast> = listOf()
)

data class Podcast(
    @SerializedName("artistName")
    val artistName: String = "",
    @SerializedName("artworkUrl100")
    val artworkUrl100: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)
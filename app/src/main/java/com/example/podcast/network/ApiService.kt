package com.jcl.youngsquare.config

import com.example.podcast.network.NetworkConfig
import com.example.podcast.network.response.BaseResponse
import com.example.podcast.network.response.GetCastDetailResponse
import com.example.podcast.network.response.GetCastsResponse
import retrofit2.http.*

interface ApiService {

    @GET(NetworkConfig.API_GET_CASTS)
    suspend fun getCastsResponse(): BaseResponse<GetCastsResponse>

    @GET(NetworkConfig.API_GET_CAST_DETAIL)
    suspend fun getCastDetailResponse(): BaseResponse<GetCastDetailResponse>
}
package com.example.podcast.network.response

data class BaseResponse<out T>(val data: T?)

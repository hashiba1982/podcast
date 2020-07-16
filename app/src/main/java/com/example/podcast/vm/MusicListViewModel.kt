package com.example.podcast.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.podcast.network.response.Collection
import com.example.podcast.network.response.GetCastDetailResponse
import com.example.podcast.network.response.GetCastsResponse
import com.example.podcast.network.response.Podcast
import com.example.podcast.tools.debug
import com.jcl.youngsquare.config.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MusicListViewModel() : BaseViewModel() {

    var collection = MutableLiveData<Collection>()

    fun getCastsDetailAPI(){
        launchAPI({
            var castsDetailResponse = RetrofitClient().create().getCastDetailResponse()
            verifyResponse(castsDetailResponse){
                collection.value = castsDetailResponse.data?.collection
            }
        },{

        })
    }
}
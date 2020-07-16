package com.example.podcast.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.podcast.network.response.Collection
import com.example.podcast.network.response.Podcast
import com.example.podcast.tools.debug
import com.jcl.youngsquare.config.RetrofitClient

class HomeViewModel() : BaseViewModel() {

    var castDataSet = MutableLiveData<List<Podcast>>().apply {
        ArrayList<Collection>()
    }

    fun getCastsAPI(){
        launchAPI({
            var castsResponse = RetrofitClient().create().getCastsResponse()
            verifyResponse(castsResponse){
                castDataSet.value = castsResponse.data?.podcast
            }
        },{

        })
    }
}
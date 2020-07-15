package com.example.podcast.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.podcast.network.response.Collection
import com.example.podcast.network.response.GetCastsResponse
import com.example.podcast.network.response.Podcast
import com.example.podcast.tools.debug
import com.jcl.youngsquare.config.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class HomeViewModel(context:Context) : BaseViewModel() {

/*    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/
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
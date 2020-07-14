package com.secretdiary.studio.config.network

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import com.secretdiary.studio.R
import com.secretdiary.studio.main.MainApplication
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

object ExceptionHandle {

    fun handleException(e: Throwable): ResponseThrowable {
        val ex: ResponseThrowable
        if (e is HttpException) {
            ex = ResponseThrowable(1000, e.message!!)
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException || e is MalformedJsonException
        ) {
            ex = ResponseThrowable(2000, e.message!!)
        } else if (e is ConnectException) {
            ex = ResponseThrowable(3000, e.message!!)
        } else if (e is SSLException) {
            ex = ResponseThrowable(4000, e.message!!)
        } else if (e is SocketTimeoutException) {
            ex = ResponseThrowable(5000, e.message!!)
        } else if (e is UnknownHostException) {
            ex = ResponseThrowable(6000, MainApplication.applicationContext().getString(R.string.sdtxt_169))
        } else if (e is ApiException) {
            ex = ResponseThrowable(e.code, e.message!!)
        } else {
            ex = if (!e.message.isNullOrEmpty()) ResponseThrowable(7000, e.message!!, e)
            else ResponseThrowable(8000, e.message!!)
        }
        return ex
    }

}
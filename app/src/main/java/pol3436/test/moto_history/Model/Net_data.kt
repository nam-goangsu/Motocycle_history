package pol3436.test.moto_history.Model

import android.app.Application
import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.D
import okhttp3.OkHttpClient
import okhttp3.*
import pol3436.test.moto_history.Model.Uri.Uri_data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import pol3436.test.moto_history.Retrofit.Get_ApiCall
import retrofit2.create

class Net_data : Application() {
/*
    fun get_Uri(Uri1: String) {//:String
        val client = OkHttpClient()
        var request = Request.Builder().url(Uri1).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Tag", "why ")
            }

            override fun onResponse(call: Call, response: Response) {
                var str = response.body()?.string()
                Log.d("Tag", "get string : " + str)

                //oil을 jsonarray 받아서
            }
        })

        //return resultlist
    }*/

    companion object {
        var networkService: Get_ApiCall

        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(Uri_data.MainUri)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init {
            networkService = retrofit.create(Get_ApiCall::class.java)
        }
    }
}
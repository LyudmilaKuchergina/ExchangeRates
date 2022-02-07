package com.example.exchangerates
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class RateViewModel : ViewModel() {
    val liveData = MutableLiveData<List<Valute>>()

    init{refresh()}

    fun getValutes() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val valutesApi = retrofit.create(ValutesApi::class.java)

        valutesApi.getValutes()?.enqueue(object : Callback<Valutes?> {
            override fun onResponse(call: Call<Valutes?>?, response: Response<Valutes?>) {
                Log.d("TAG","response " + response.body())
                val json = response.body()
                val gbp = json?.valutes?.get("GBP")?.name
                Log.d("TAG", "$gbp")
                json?.valutes?.run {
                    liveData.postValue(this.values.toList())
                }
            }

            override fun onFailure(call: Call<Valutes?>, t: Throwable) {
                Log.d("TAG","onFailure " )
                t.printStackTrace()
            }
        })
    }
    fun refresh(){
        val timer = Timer()
        val period: Long = 10000
        timer.scheduleAtFixedRate(period, period) {
            getValutes()
        }
    }
}
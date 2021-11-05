package com.emptycoder.ja1.repository

import androidx.lifecycle.MutableLiveData
import com.emptycoder.ja1.api.ApiInterface
import com.emptycoder.ja1.model.Article
import com.emptycoder.ja1.model.New
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    fun apiCall(query: String, apikey: String, news: MutableLiveData<List<Article?>>){
        val call = apiInterface.getEverthing(query,apikey)
        call.enqueue(object : Callback<New>{
            override fun onResponse(call: Call<New>, response: Response<New>) {
                news.postValue(response.body()!!.articles)
            }

            override fun onFailure(call: Call<New>, t: Throwable) {
                news.postValue(null)
            }

        })
    }
}
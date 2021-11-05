package com.emptycoder.ja1.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emptycoder.ja1.model.Article
import com.emptycoder.ja1.model.New
import com.emptycoder.ja1.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.security.interfaces.RSAMultiPrimePrivateCrtKey
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    var news : MutableLiveData<List<Article?>> = MutableLiveData()
    val apiKey = "4fdff476ace14b0e85237e01246f0ff1"


    fun getData() : MutableLiveData<List<Article?>>{
        return news
    }

    fun loadNews(){
        repository.apiCall("Everything",apiKey,news)
    }
}
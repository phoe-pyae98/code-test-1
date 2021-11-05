package com.emptycoder.ja1.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emptycoder.ja1.adapter.NewAdapter
import com.emptycoder.ja1.databinding.ActivityMainBinding
import com.emptycoder.ja1.model.Article
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var newlist: MutableList<String> = ArrayList()

    lateinit var rcAdapter : NewAdapter

    lateinit var layoutManager: LinearLayoutManager


    var page = 1
    var isLoading = false
    var limit = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViewModel()


    }

    //call viewmodel and get data from that's function
    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.loadNews()

        viewModel.getData().observe(this, {
            if (it != null) {
                rcAdapter = NewAdapter()
                layoutManager = LinearLayoutManager(this)
                  binding.newRc.layoutManager = layoutManager
                  getPage(it as ArrayList<Article?>)
                  binding.newRc.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                       override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                               val visibleData = layoutManager.itemCount
                               val pastVisibleData = layoutManager.findFirstCompletelyVisibleItemPosition()
                               val total = rcAdapter.itemCount

//                               if(!isLoading){
//                                   if((visibleData+pastVisibleData) >= total){
//                                       page++
//                                       getPage(it as ArrayList<Article?>)
//                                   }
//                               }

                           super.onScrolled(recyclerView, dx, dy)
                       }
                   })

            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
            }
        })

        binding.newRc.layoutManager = LinearLayoutManager(this)


    }



    //pagination data with limit 10
    fun getPage(list: ArrayList<Article?>){

        isLoading = true
        binding.loadingPb.visibility = View.VISIBLE

        var start = (page-1) * limit
        var end = (page)* limit

        var currentList : ArrayList<Article?> = ArrayList()


            for(i in start..end-1){
                currentList.add(list[i])
            }

        Handler().postDelayed({
            if(::rcAdapter.isInitialized){
                rcAdapter.notifyDataSetChanged()
                rcAdapter = NewAdapter()
                rcAdapter.addNews(currentList)
                binding.newRc.adapter = rcAdapter
            }else{
                rcAdapter = NewAdapter()
                rcAdapter.addNews(currentList)
                binding.newRc.adapter = rcAdapter
            }
        },5000)


        Log.d("cdata",currentList[1].toString())
        isLoading = false
        binding.loadingPb.visibility = View.GONE


    }


}
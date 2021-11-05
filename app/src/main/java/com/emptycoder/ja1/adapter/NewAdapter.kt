package com.emptycoder.ja1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emptycoder.ja1.R
import com.emptycoder.ja1.databinding.NewViewBinding
import com.emptycoder.ja1.model.Article
import com.squareup.picasso.Picasso

class NewAdapter : RecyclerView.Adapter<NewAdapter.NewViewHolder>() {

    lateinit var newlist: ArrayList<Article?>

    fun addNews(data: ArrayList<Article?>){
        this.newlist = data
    }

    class NewViewHolder(val binding: NewViewBinding): RecyclerView.ViewHolder(binding.root){
      fun bind(new: Article?){
          Picasso.get().load(new!!.urlToImage).placeholder(R.drawable.ic_launcher_background).into(binding.newImg)
          binding.newTitle.setText(new.title)
          binding.newDesciption.setText(new.description)
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        return NewViewHolder(NewViewBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
          holder.bind(newlist[position]!!)
    }

    override fun getItemCount(): Int  = newlist.size
}
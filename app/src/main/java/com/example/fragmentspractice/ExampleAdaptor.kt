package com.example.fragmentspractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExampleAdaptor(private val exampleList: List<ListItem>) : RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_layout, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        Glide.with(holder.imageView.context).load(currentItem.imageSource.large).into(holder.imageView)
//        holder.imageView.setImage
        holder.textView.text = currentItem.text
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)

    }
}
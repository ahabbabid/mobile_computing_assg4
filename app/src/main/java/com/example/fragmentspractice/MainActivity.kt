package com.example.fragmentspractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentspractice.api.ApiRequests
import com.example.fragmentspractice.api.users
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.randomuser.me/api/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = findViewById<TextView>(R.id.text_view)
        val imageView = findViewById<ImageView>(R.id.image_view)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        setContentView(R.layout.activity_main)
        getUsers(this)
    }
    private fun getUsers(context: Context){
         val TAG = ""
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val gson = GsonBuilder().setLenient().create()
        val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiRequests::class.java)
        val call = api.getUsers()
        call.enqueue( object : Callback<users>{
            override fun onResponse(call: Call<users>, response: Response<users>) {
               if(response.code() == 200){
                   val list = ArrayList<ListItem>()
                   for(item in response.body()!!.results){
                       list += ListItem(item.email, item.picture)
                   }
                   recyclerView.adapter =  ExampleAdaptor(exampleList = list)
                   recyclerView.layoutManager= LinearLayoutManager(context)
//                   recyclerView.setHasFixedSize(true)


                   Log.d(TAG,"ahabb is awesome")
               }
            }

            override fun onFailure(call: Call<users>, t: Throwable) {
               Log.e(TAG,t.message?: "ahabb")
            }

        })
    }



}
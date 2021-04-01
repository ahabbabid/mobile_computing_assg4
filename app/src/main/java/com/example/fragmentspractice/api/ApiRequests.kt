package com.example.fragmentspractice.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("?results=500")
    fun getUsers():Call<users>
}
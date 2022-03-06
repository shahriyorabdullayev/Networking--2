package com.example.modul6networking.network.retrofit.services

import com.example.modul6networking.model.Poster
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    private val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server() :String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val posterService: PosterService = retrofit.create(PosterService::class.java)
}
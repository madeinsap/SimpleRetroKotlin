package com.project.solomode.testapp.retrokotlin

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val BASE_URL = "http://insapol.esy.es/skripsi/"
    private var retrofit: Retrofit? = null

    val getClient: Retrofit
    get() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
package com.project.solomode.testapp.retrokotlin

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface ApiInterface {

    @GET("data/getData.php")
    fun getAllData(@Query("lat") lat : String, @Query("lng") lng : String): Observable<List<Instansi>>

}
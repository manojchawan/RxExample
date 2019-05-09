package com.manchaw.rxexample.network

import com.manchaw.rxexample.PostResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getPosts(): Observable<List<PostResponse>>
}

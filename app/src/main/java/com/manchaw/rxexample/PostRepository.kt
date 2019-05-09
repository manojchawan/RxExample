package com.manchaw.rxexample

import com.manchaw.rxexample.network.ApiInterface
import com.manchaw.rxexample.network.RetrofitClient
import io.reactivex.Observable

class PostRepository {


    fun getPostList(): Observable<List<PostResponse>> {
        return RetrofitClient.getClient().create(ApiInterface::class.java).getPosts()
    }

    companion object {
        private val TAG = PostRepository::class.java.simpleName
    }

}

package com.manchaw.rxexample

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostViewModel {
    final public val TAG = PostViewModel::class.java.simpleName

    data class MyViewState(val list: List<PostResponse>)

    private val relay = BehaviorRelay.create<MyViewState>()
    private val repository: PostRepository = PostRepository()
    val disposable: CompositeDisposable = CompositeDisposable()


    //WRONG WAY
    fun getPosts(): Observable<List<PostResponse>> {
        return repository.getPostList()
    }

    fun getPostData() {
        val disp = repository.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "onSubscribe")
//                displayPosts(it)
                relay.accept(MyViewState(it)) //updates the viewstate class
            }, { t: Throwable ->
                Log.d(TAG, t.localizedMessage)
            })

        disposable.add(disp)
    }

    fun showPosts() = relay.hide()


//    init {
//        repository = PostRepository()
//    }
}

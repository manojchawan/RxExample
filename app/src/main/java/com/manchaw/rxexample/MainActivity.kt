package com.manchaw.rxexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manchaw.rxexample.network.ApiInterface
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final public val TAG = MainActivity::class.java.simpleName
    lateinit var mviewModel: PostViewModel


    lateinit var api: ApiInterface
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPost.layoutManager = LinearLayoutManager(baseContext)

        fetchPosts()
    }

    private fun fetchPosts() {
        mviewModel = ViewModelProviders.of(this@MainActivity).get(PostViewModel::class.java)

        val disp = mviewModel.showPosts().subscribe {
            displayPosts(it)
        }

        mviewModel.getPostData()

        compositeDisposable.add(disp)
    }

    private fun displayPosts(it: PostViewModel.MyViewState) {

        val postAdapter = PostAdapter(baseContext, it.list)
        rvPost.adapter = postAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

        if (!compositeDisposable.isDisposed) {
            Log.d(TAG, "Disposed")

            compositeDisposable.clear()
        }

    }

}

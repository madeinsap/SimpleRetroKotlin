package com.project.solomode.testapp.retrokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.ArrayList
import org.reactivestreams.Subscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    lateinit var jsonApi: ApiInterface
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var mAdapter: MyAdapter

    var compositeDisposable: CompositeDisposable? = null
    var instansiList: ArrayList<Instansi>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.my_recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fletchData()
    }

    private fun fletchData() {
        progressBar.visibility = View.VISIBLE
        val apiService = ApiClient.getClient
        jsonApi = apiService.create(ApiInterface::class.java)

        compositeDisposable?.add(jsonApi.getAllData("lat", "lng")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(instansi: List<Instansi>) {
        progressBar.visibility = View.GONE
        instansiList = ArrayList(instansi)
        mAdapter = MyAdapter(this, instansiList!!)
        recyclerView.adapter = mAdapter

    }

    private fun handleError(error: Throwable) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        compositeDisposable?.clear()
        super.onDestroy()
    }
}

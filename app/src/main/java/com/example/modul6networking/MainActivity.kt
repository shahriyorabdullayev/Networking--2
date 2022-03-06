package com.example.modul6networking

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_advanced_kotlin.activity.network.volley.VolleyHttp
import com.example.modul6networking.adapter.MainAdapter
import com.example.modul6networking.model.Poster
import com.example.modul6networking.model.PosterResp
import com.example.modul6networking.network.retrofit.services.RetrofitHttp
import com.example.modul6networking.network.volley.VolleyHandler
import com.example.modul6networking.utils.Logger
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var btnFloating: FloatingActionButton
    private lateinit var posters: ArrayList<Poster>
    private lateinit var poster: Poster
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rvMain = findViewById(R.id.rv_main)
        btnFloating = findViewById(R.id.btn_floating)
        rvMain.layoutManager = GridLayoutManager(this, 1)




//        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("VolleyHttp", response!!)
//                tvResult.text = response
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("VolleyHttp", error)
//            }
//        })

        poster = Poster(1, 1, "PDP", "Online")

//        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), object :VolleyHandler {
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@", response!!)
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@", error!!)
//            }
//
//        })
//
//
//        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object :VolleyHandler {
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@", response!!)
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@", error!!)
//            }
//
//        })

//        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object :VolleyHandler {
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@", response!!)
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@", error!!)
//            }
//
//        })

        //workWithRetrofit()
        progress = findViewById(R.id.progress)
        posters = ArrayList()
        apiPosterList()

    }

    fun refreshAdapter(items: ArrayList<Poster>){
        val adapter = MainAdapter(this, items)
        rvMain.adapter = adapter
    }

    fun apiPosterList() {
        progress.visibility = View.VISIBLE
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                val postArray = Gson().fromJson(response, Array<Poster>::class.java)
                posters.clear()
                posters.addAll(postArray)
                progress.visibility = View.GONE
                refreshAdapter(posters)
                Logger.d("@@@", posters.size.toString())
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }

        })
    }

    fun dialogPoster(poster:Poster) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster")
            .setPositiveButton(android.R.string.yes){
                dialog, which -> apiPosterDelete(poster!!)
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun apiPosterDelete(poster: Poster) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object :VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("@@@", response!!)
                apiPosterList()
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }
        })
    }

    fun workWithRetrofit() {

//        RetrofitHttp.posterService.listPost().enqueue(object : retrofit2.Callback<ArrayList<PosterResp>> {
//            override fun onResponse(
//                call: Call<ArrayList<PosterResp>>,
//                response: Response<ArrayList<PosterResp>>,
//            ) {
//                Logger.d("@@@", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
//                Logger.d("@@@", t.message.toString())
//            }
//
//        })

        val poster = Poster(1, 1, "PDP", "Online")

        RetrofitHttp.posterService.createPost(poster).enqueue(object: retrofit2.Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("@@@", t.message.toString())
            }

        })

//        RetrofitHttp.posterService.updatePost(poster.id, poster).enqueue(object : retrofit2.Callback<PosterResp>{
//            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
//                Logger.d("@@@", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
//                Logger.d("@@@", t.message.toString())
//            }
//
//        })

//        RetrofitHttp.posterService.singlePost(poster.id).enqueue(object : retrofit2.Callback<PosterResp> {
//            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
//                Logger.d("@@@", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
//                Logger.d("@@@", t.message.toString())
//            }
//
//        })

//        RetrofitHttp.posterService.deletePost(poster.userId).enqueue(object : retrofit2.Callback<PosterResp> {
//            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
//                Logger.d("@@@", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
//                Logger.d("@@@", t.message.toString())
//            }
//
//        })



    }


}
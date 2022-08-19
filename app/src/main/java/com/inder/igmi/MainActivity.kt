package com.inder.igmi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.inder.igmi.Adapter.DataAdapter
import com.inder.igmi.Model.Listed
import com.inder.igmi.Model.SearchModel
import com.inder.igmi.ViewModel.MainViewModel
import com.inder.igmi.utils.Keys
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() , Observer{

    var mainViewModel : MainViewModel? = null
    var searchList = mutableListOf<Listed>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        mainViewModel!!.addObserver(this)

        var map = HashMap<String,String>()
        map.put(Keys.PARAM_LATITUDE,"53.798407")
        map.put(Keys.PARAM_LONGITUDE,"-1.548248")
        map.put(Keys.PARAM_DATE,"2021-04-16")
        map.put(Keys.PARAM_TIME,"10.30")
        map.put(Keys.PARAM_PERSON,"2")

        if(isNetworkAvailable(this@MainActivity)){
            mainViewModel!!.searchData(this@MainActivity,map, Keys.SEARCH_DATA_RES,rl_progress)
        }else{
            Toast.makeText(this@MainActivity, "Internet is not available", Toast.LENGTH_LONG).show()
        }


    }

    fun setDataOnScreen(searchModel: SearchModel){
        searchList?.clear()
        searchList?.addAll(searchModel.listed)

        //set adapter
        var abc = DataAdapter (this, searchList!!)
        recyclerView.adapter = abc

    }

    override fun update(p0: Observable?, p1: Any?) {
        when (mainViewModel!!.resultCode) {
            Keys.SEARCH_DATA_RES -> {
                if (mainViewModel!!.searchModel != null) {
                   //set the data here
                    setDataOnScreen(mainViewModel?.searchModel!!)
                }
            }
        }
    }


    //Check internet connection
    fun isNetworkAvailable(context: Context): Boolean
    {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true
                    }
                }
            } else {
                try {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        Log.i("update_status", "Network is available")
                        return true
                    }
                } catch (e: java.lang.Exception) {
                    Log.i("update_status", "" + e.message)
                }
            }
        }
        Log.i("update_status", "Network is not available")
        return false
    }


}
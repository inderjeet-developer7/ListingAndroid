package com.inder.igmi.ViewModel

import android.content.Context
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.inder.igmi.Model.SearchModel
import com.inder.igmi.Webservice.RetrofitService
import retrofit2.Call
import retrofit2.Response
import java.util.*

class MainViewModel : Observable() {
    var searchModel : SearchModel? = null
    var resultCode =""

    fun searchData(context: Context,map: HashMap<String,String>,requestCode:String,progress: LottieAnimationView){
        progress.visibility= View.VISIBLE
        var apiServices= RetrofitService.create()
        var response=apiServices.searchTableApi(map)
        response.enqueue(object : retrofit2.Callback<SearchModel>{
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                progress.visibility= View.GONE

                if(response.body()!=null){
                    searchModel=response.body()
                    resultCode=requestCode
                    setChanged()
                    notifyObservers()
                }

            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                progress.visibility= View.GONE

                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()

            }
        })
    }

}
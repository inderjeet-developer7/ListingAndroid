package com.inder.igmi.Webservice

import com.inder.igmi.Model.SearchModel
import com.inder.igmi.utils.URLHelper
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @FormUrlEncoded
    @POST(URLHelper.SEARCH_TABLE_API)
    fun searchTableApi(@FieldMap map: HashMap<String, String>): Call<SearchModel>


}

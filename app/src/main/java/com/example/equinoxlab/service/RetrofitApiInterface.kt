package com.example.retrofitdemo.service

import com.example.equinoxlab.model.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitApiInterface {

    @GET("betaDailyUpdateApi/Service1.svc/getManager")
    fun getData() : Observable<ResponseData>

}

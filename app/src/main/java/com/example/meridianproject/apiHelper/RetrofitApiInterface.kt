package com.example.meridianproject.apiHelper



import com.example.meridianproject.common.userdetails.model.UserMain
import com.google.gson.JsonObject



import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitApiInterface {


  @GET("users")
  fun getUserDetails(): Observable<UserMain>



}

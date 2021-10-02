package com.example.meridianproject.repository



import com.example.meridianproject.apiHelper.RetrofitApiService
import com.example.meridianproject.common.userdetails.model.UserMain
import io.reactivex.Observable


class MainRepository() {

    fun getUserDetails():Observable<UserMain>{
        return RetrofitApiService.retrofitApiService?.getUserDetails()!!
    }
}
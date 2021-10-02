package com.example.meridianproject.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.NavController
import com.example.meridianproject.R
import com.example.meridianproject.variabiles.Constants
import com.example.meridianproject.variabiles.Keys
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

object Utils {

    fun loadImage(img: ImageView, url: String) {
        Picasso.get()
                .load(url)
                // .placeholder(R.drawable.ic_spinner)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_CACHE)
                .into(img)

    }


    fun isNetworkConnected(
            context: Context?,
            currentPage: String,
            navController: NavController
    ): Boolean {
        if (context != null) {
            val connMgr = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return if (networkInfo != null && networkInfo.isConnected) {
                true
            } else {
                Keys.requestErrorCode = 9
                val bundle = Bundle()
                bundle.putString(Constants.pageName, currentPage)
               // navController.navigate(R.id.errorPage, bundle)
                false
            }
        } else {
            Keys.requestErrorCode = 9
            //navController.popBackStack(R.id.errorPage, true )

            val bundle = Bundle()
            bundle.putString(Constants.pageName, currentPage)
            //navController.navigate(R.id.errorPage, bundle)
            return false
        }
    }





}
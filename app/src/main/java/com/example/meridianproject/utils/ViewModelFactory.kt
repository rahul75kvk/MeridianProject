package com.example.meridianproject.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meridianproject.common.login.viewModel.LoginViewModel
import com.example.meridianproject.common.signup.viewModel.SignupViewModel
import com.example.meridianproject.common.userdetails.viewModel.UserdetailsViewModel
import com.example.meridianproject.repository.MainRepository


class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        }else if (modelClass.isAssignableFrom(SignupViewModel::class.java)){
            return SignupViewModel() as T
        }else if (modelClass.isAssignableFrom(UserdetailsViewModel::class.java)) {
            return UserdetailsViewModel(MainRepository()) as T


             // throw IllegalArgumentException("Unknown class name")

        }
        throw IllegalArgumentException("Unknown class name")


    }
}
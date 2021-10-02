package com.example.meridianproject.common.signup.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.meridianproject.R
import com.example.meridianproject.utils.Validator


class SignupViewModel:ViewModel() {


    fun ValidateSignup(
            context: Context,
            userName:String,
            userEmail:String,
            confirmPassword:String,
            passWord:String

    ): String {
        var Valu: String = ""
        if  (userName.isNullOrEmpty()) {
            Valu = "Please enter your Name !"
        }else if (userEmail.isNullOrEmpty()) {
                Valu = "Please Enter Email !"
        }else if (confirmPassword.isNullOrEmpty()) {
            Valu = "Please Enter ConfirmPassword !"
        }else if (passWord.isNullOrEmpty()) {
            Valu = "Please Enter Password !"
        }else if (passWord !=confirmPassword) {
            Valu = "Please Confirm Password!"
        }else if (!Validator.isValidPasswordFormat(passWord)) {
            Valu = context.getString(R.string.password_warning)
        }
        return Valu
    }
}
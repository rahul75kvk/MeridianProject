package com.example.meridianproject.common.login.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.meridianproject.R
import com.example.meridianproject.utils.Validator

class LoginViewModel :ViewModel () {


    fun ValidateLogin(
            context: Context,
            passWord:String,
            userEmail:String

    ): String {
        var Valu: String = ""
        if (userEmail?.length == 0) {
            Valu = "Please enter registered email"
        } else if (passWord?.length == 0) {
            Valu = "Please enter a password"
        }else if (!Validator.isValidPasswordFormat(passWord)) {
            Valu = context.getString(R.string.password_warning)
        }
        return Valu
    }
}
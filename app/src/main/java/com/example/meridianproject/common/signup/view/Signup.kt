package com.example.meridianproject.common.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.meridianproject.R
import com.example.meridianproject.base.BaseActivity
import com.example.meridianproject.base.BaseFragment
import com.example.meridianproject.common.signup.viewModel.SignupViewModel
import com.example.meridianproject.utils.PrefManager
import com.example.meridianproject.utils.ViewModelFactory
import kotlinx.android.synthetic.main.signup.view.*


class Signup:BaseFragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var rootView: View

    private lateinit var signupViewModel:SignupViewModel

    lateinit var userName: EditText
    lateinit var userEmail: EditText
    lateinit var passWord: EditText
    lateinit var confirmPassword: EditText
    lateinit var prefmanager: PrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

    }

    private fun setupViewModel() {

        signupViewModel = ViewModelProvider(this, ViewModelFactory()).get(SignupViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.signup, null)


        setUpUI(rootView)

        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.frame) as NavHostFragment
        navController = navHostFragment.navController



        return rootView
    }

    private fun setUpUI(rootView: View) {
        rootView.tv_login.setOnClickListener(this)
        rootView.btn_Signup.setOnClickListener(this)
        userName = rootView.tv_Name
        userEmail=rootView.tv_Email
        passWord = rootView.tv_Password
        confirmPassword = rootView.tv_ConfirmPassword

        prefmanager = PrefManager(requireContext())
    }

    override fun onClick(v: View?) {

        val id = v?.id
        when (id) {

            R.id.tv_login -> {
                Navigation.findNavController(rootView).navigate(R.id.action_signup_to_login)

            }

            R.id.btn_Signup -> {

                val response: String = signupViewModel.ValidateSignup(
                        requireActivity() as BaseActivity,
                         userEmail.text.toString().trim(),
                                 passWord.text.toString().trim()
                )

                if (response.equals("")) {
                    prefmanager.checkName =userEmail.toString()

                    if (confirmPassword.equals(passWord)) {

                        prefmanager.checkEmail = userEmail.toString()

                    }
                    else{
                        showToast(" please confirm Your password")
                    }

                    Navigation.findNavController(rootView).navigate(R.id.action_signup_to_login)

                } else {
                    showToast(response)
                }

            }


        }
    }
}


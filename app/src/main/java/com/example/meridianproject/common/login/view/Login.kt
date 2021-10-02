package com.example.meridianproject.common.login.view

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
import com.example.meridianproject.common.login.viewModel.LoginViewModel
import com.example.meridianproject.utils.PrefManager
import com.example.meridianproject.utils.ViewModelFactory
import kotlinx.android.synthetic.main.frag_login.view.*

class Login : BaseFragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var rootView: View
    lateinit var viewModel: LoginViewModel

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var prefmanager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.frag_login, null)
        setUpUI(rootView)

        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.frame) as NavHostFragment
        navController = navHostFragment.navController

        return rootView
    }

    private fun setUpUI(rootView:View) {

        rootView.tv_Signup.setOnClickListener(this)
        rootView.btn_Login.setOnClickListener(this)
        username = rootView.tv_Email
        password = rootView.tv_Password
        prefmanager = PrefManager(requireContext())

    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

    }
    override fun onClick(v: View?) {

        val id = v?.id
        when (id) {

            R.id.tv_Signup -> {
                Navigation.findNavController(rootView).navigate(R.id.action_login_to_Signup)

            }

            R.id.btn_Login ->{

                val response: String = viewModel.ValidateLogin(
                        requireActivity() as BaseActivity,
                        username.text.toString().trim(),
                        password.text.toString().trim()
                )

                if (response.equals("")) {

                    if (username.text.toString().equals(prefmanager.checkEmail)||password.text.toString().equals(prefmanager.checkPassword)) {

                        Navigation.findNavController(rootView)
                            .navigate(R.id.action_login_to_UserDtails)
                    } else{

                        showToast("Please Signup")
                        Navigation.findNavController(rootView).navigate(R.id.action_login_to_Signup)
                    }
                } else {
                    showToast(response)
                }


            }
        }

    }

}
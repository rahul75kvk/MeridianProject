package com.example.meridianproject.common.userdetails.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meridianproject.R
import com.example.meridianproject.base.BaseFragment
import com.example.meridianproject.common.signup.viewModel.SignupViewModel
import com.example.meridianproject.common.userdetails.adapter.UserListAdapter
import com.example.meridianproject.common.userdetails.model.UserDetailslist
import com.example.meridianproject.common.userdetails.model.UserMain
import com.example.meridianproject.common.userdetails.viewModel.UserdetailsViewModel
import com.example.meridianproject.utils.PrefManager
import com.example.meridianproject.utils.Status
import com.example.meridianproject.utils.Utils
import com.example.meridianproject.utils.ViewModelFactory
import com.example.meridianproject.variabiles.Constants
import kotlinx.android.synthetic.main.user_details.view.*

class UserDetails :BaseFragment() {

    lateinit var navController: NavController
    lateinit var rootView: View

    lateinit var prefmanager: PrefManager
    lateinit var userViewModel: UserdetailsViewModel
    lateinit var adapterProduct: UserListAdapter
    lateinit var layoutManager: LinearLayoutManager

    lateinit var userlistArray: ArrayList<UserDetailslist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {

        userViewModel = ViewModelProvider(this, ViewModelFactory()).get(UserdetailsViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.user_details, null)

        setUpUI(rootView)
        setupObserver()
        userlistArray = ArrayList<UserDetailslist>()

        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.frame) as NavHostFragment
        navController = navHostFragment.navController

        if (Utils.isNetworkConnected(requireActivity(), Constants.userdetails, navController)) {
            userViewModel.fetchuserData()
        }

        return rootView
    }

    private fun setUpUI(rootView: View) {

        prefmanager = PrefManager(requireContext())
        layoutManager = LinearLayoutManager(context)

        rootView.rv_User!!.layoutManager =
                LinearLayoutManager(
                        requireActivity(),

                        LinearLayoutManager.VERTICAL,
                        false
                )
        rootView.rv_User!!.isNestedScrollingEnabled = false
        rootView.rv_User!!.setHasFixedSize(true)

        adapterProduct = UserListAdapter(
                arrayListOf()
        )
        rootView.rv_User!!.adapter = adapterProduct
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupObserver() {
        userViewModel.userData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { userData -> renderData(userData) }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(data: UserMain) {

        if (data.success) {

            if (data.data!!.userDetails != null) {
                userlistArray = data.data!!.userDetails!!
               adapterProduct.addList(userlistArray)


            }
        } else {
            showToast(data.message)
        }
    }
}
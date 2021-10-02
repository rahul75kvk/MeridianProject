package com.example.meridianproject.common.userdetails.viewModel

import androidx.lifecycle.ViewModel
import com.example.meridianproject.common.userdetails.model.UserMain
import com.example.meridianproject.repository.MainRepository
import com.example.meridianproject.utils.Resource
import com.example.meridianproject.utils.SingleLiveEvent
import com.example.meridianproject.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserdetailsViewModel (private val mainRepository: MainRepository):ViewModel() {


    private val userData = SingleLiveEvent<Resource<UserMain>>()

    private val compositeDisposable = CompositeDisposable()


    fun fetchuserData() {

        userData.postValue(Resource.loading(null))
        compositeDisposable.add(mainRepository.getUserDetails().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userDatas -> userData.postValue(Resource.success(userDatas)) },
                        { throwable ->
                            userData.postValue(Resource.error("Something went to wrong", null))

                        }
                )
        )
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun userData(): SingleLiveEvent<Resource<UserMain>> {
        return userData

    }

}
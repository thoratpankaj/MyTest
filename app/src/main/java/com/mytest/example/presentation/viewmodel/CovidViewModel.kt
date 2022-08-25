package com.mytest.example.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytest.example.data.model.APIResponse
import com.mytest.example.data.util.Resource
import com.mytest.example.domain.usecase.repository.GetCovidDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CovidViewModel(
    private val app: Application,
    private val getCovidDataUseCase: GetCovidDataUseCase
) : AndroidViewModel(app) {

    val covidData: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getCovidData() = viewModelScope.launch(Dispatchers.IO) {

        covidData.postValue(Resource.Loading())

        try {
            if (isNetworkAvailble(app)) {
                val apiResult = getCovidDataUseCase.execute()
                covidData.postValue(apiResult)
            } else {
                covidData.postValue(Resource.Error("Internet is not available"))
            }
        }catch (ex:Exception){
            covidData.postValue(Resource.Error(ex.message.toString()))

        }
    }

    fun isNetworkAvailble(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}
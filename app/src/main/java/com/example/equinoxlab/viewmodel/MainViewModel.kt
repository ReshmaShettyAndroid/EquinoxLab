package com.example.equinoxlab.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.equinoxlab.R
import com.example.equinoxlab.model.Data
import com.example.equinoxlab.model.ResponseData
import com.example.retrofitdemo.service.RetrofitApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.example.equinoxlab.database.AppDatabase;
import com.example.equinoxlab.database.entity.ManagerEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context;
    var apiInterface: RetrofitApiInterface;
    private var myCompositeDisposable: CompositeDisposable? = null;
    private var responseData: MutableLiveData<ResponseData>? = null
    private var roomDb: AppDatabase? = null;

    init {
        context = application.applicationContext;
        apiInterface =
            ApiClient.getClient(application.applicationContext.getString(R.string.base_url))!!
                .create(RetrofitApiInterface::class.java)
        myCompositeDisposable = CompositeDisposable()
        roomDb = AppDatabase.getInstance(context);
    }

    fun getMutableData(): LiveData<ResponseData?>? {
        if (responseData == null) {
            responseData = MutableLiveData<ResponseData>()
        }
        return responseData
    }

    fun requestGetApi() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            apiInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    private fun onFailure(t: Throwable) {
        Log.d("Testing", "Testing Response:-error occured" + t.message);
    }

    private fun onResponse(response: ResponseData) {
        Log.d("Testing", "Testing Response:-" + response.data);
        responseData?.setValue(response)
    }


    fun insertdataToDb(managerList: List<ManagerEntity>) {
            GlobalScope.launch {
                var managerEntity: ManagerEntity? = roomDb?.ManagerDao()?.getValuExist();

                if(managerEntity == null) {
                if (managerList != null && managerList.size > 0) {
                    for (managerData: ManagerEntity in managerList) {
                        try {
                            val id = roomDb?.ManagerDao()?.insertAll(managerData)
                        } catch (e: Exception) {
                            Log.d("Testing", "Error occured in insertdataToDb:-" + e.message)

                        }
                    }

                }
            }
        }
    }

    fun getAlldataFromDb(){
        var list :List<ManagerEntity>;
        var data:ResponseData;
        GlobalScope.launch {
            try {
                var managerList: List<ManagerEntity> = roomDb?.ManagerDao()?.getAll()!!
                 data = ResponseData(200, managerList, "successful insertion")

                responseData?.postValue(data)

            } catch (e: Exception) {
                Log.d("Testing", "Error occured in getAlldataFromDb:-" + e.message)
            }
        }
    }

}

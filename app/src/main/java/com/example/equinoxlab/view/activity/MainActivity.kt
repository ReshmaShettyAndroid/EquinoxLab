package com.example.equinoxlab.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.equinoxlab.R
import com.example.equinoxlab.database.entity.ManagerEntity
import com.example.equinoxlab.model.Data
import com.example.equinoxlab.model.ResponseData
import com.example.equinoxlab.utils.commonUtils
import com.example.equinoxlab.view.adapter.ManagerDataRecyclerAdapter
import com.example.equinoxlab.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var commonUtils: commonUtils;
    private lateinit var mainViewModel: MainViewModel;
    private lateinit var recylerVwManager : RecyclerView;
    private lateinit var searchView : SearchView;
    private lateinit var managerDataRecyclerAdapter : ManagerDataRecyclerAdapter;
    private lateinit var managerList:List<ManagerEntity>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        objectInitialization();
        viewInitialization();
        liveData();
        Apicall();

    }
    fun objectInitialization(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        commonUtils = commonUtils(this)
    }
    fun viewInitialization(){
        recylerVwManager = findViewById(R.id.recylerVwManager);
        searchView =  findViewById(R.id.searchView);

        recyclerViewSetting();
        searchData();
    }

    fun recyclerViewSetting(){
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recylerVwManager.setLayoutManager(linearLayoutManager)
    }

    fun searchData(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                managerDataRecyclerAdapter.filter(managerList,newText)
                return false
            }
        })

    }
    fun liveData() {
        mainViewModel.getMutableData()!!.observe(this, Observer<ResponseData?> { response ->
                commonUtils.dismissProgressDialog()
            if(response?.code == 200) {
                managerList = response.data;
                setAdapter(managerList)
                //store it in datbase
                mainViewModel.insertdataToDb(managerList)
            }
            else{
                Toast.makeText(this,getString(R.string.smtng_went_wrng_txt),Toast.LENGTH_SHORT).show()
            }
            })
    }

    fun setAdapter(managerlist: List<ManagerEntity>){
        managerDataRecyclerAdapter  =
            ManagerDataRecyclerAdapter(this, managerlist)
        recylerVwManager.setAdapter(managerDataRecyclerAdapter)
    }

    fun Apicall(){
        if(commonUtils.isNetworkConnected){
            commonUtils.showProgressDialog()
            mainViewModel.requestGetApi();
        }
        else{
            //get data from room database
            commonUtils.showProgressDialog()
            mainViewModel.getAlldataFromDb();
        }
    }
}
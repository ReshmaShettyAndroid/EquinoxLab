package com.example.equinoxlab.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.equinoxlab.R
import com.example.equinoxlab.database.entity.ManagerEntity
import com.example.equinoxlab.view.adapter.ManagerDataRecyclerAdapter.ManagerDataHolder
import java.util.*

class ManagerDataRecyclerAdapter(private val context: Context, private var managerList: List<ManagerEntity>) :RecyclerView.Adapter<ManagerDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagerDataHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_managerdata, parent, false)
        return ManagerDataHolder(v)
    }

    override fun onBindViewHolder(holder: ManagerDataHolder, position: Int) {
        var data :ManagerEntity = managerList[position]
        holder.txtName.text = data.name
        holder.txtDept.text = data.dept_name
    }

    override fun getItemCount(): Int {
        return managerList.size
    }

    fun filter(dataList: List<ManagerEntity>,query: String): List<ManagerEntity> {
        var query = query.toLowerCase()
        val filteredDataList: MutableList<ManagerEntity> = ArrayList()
        for (data in dataList) {
            val name = data.name.toLowerCase()
            val dptName = data.dept_name.toLowerCase()
            if (name.contains(query) || dptName.contains(query)) {
                filteredDataList.add(data)
            }
        }
        managerList = filteredDataList
        notifyDataSetChanged();
     return filteredDataList
    }

    inner class ManagerDataHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtName: TextView
        var txtDept: TextView
        init {
            txtName = itemView.findViewById(R.id.txtName)
            txtDept = itemView.findViewById(R.id.txtDept)
        }
    }

}
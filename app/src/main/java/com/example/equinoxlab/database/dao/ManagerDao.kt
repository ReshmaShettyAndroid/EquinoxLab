package com.example.equinoxlab.database.dao

import androidx.room.*
import com.example.equinoxlab.database.entity.ManagerEntity
import com.example.equinoxlab.model.Data
@Dao
interface ManagerDao {
    @Query("SELECT * FROM manager")
    fun getAll(): List<ManagerEntity>

    @Insert
    fun insertAll(vararg managerData: ManagerEntity)

    @Query("SELECT * FROM manager WHERE id =1")
    fun getValuExist(): ManagerEntity
}


package com.ahmetroid.worldclocks.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmetroid.worldclocks.data.model.Clock

@Dao
interface ClocksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Clock)

    @Delete
    suspend fun delete(movies: Clock)

    @Query("SELECT * FROM Clock")
    fun getAll(): LiveData<List<Clock>>

}
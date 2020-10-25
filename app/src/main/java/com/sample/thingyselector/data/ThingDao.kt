package com.sample.thingyselector.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sample.thingyselector.models.Thing

@Dao
interface ThingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertThing(thing: Thing)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(things: List<Thing>)

    @Query("SELECT * FROM things ORDER BY date ASC")
    fun getAll(): LiveData<List<Thing>>

    @Query("SELECT * FROM things where selected=1 ORDER BY date ASC")
    fun getSelectedThings(): LiveData<List<Thing>>

    @Update
    fun updateThing(vararg thing: Thing)

    @Query("DELETE FROM things")
    fun deleteAll():Int

    @Query("SELECT * FROM things WHERE id = :id")
    fun getThingById(id: Int): Thing?

    @Query("SELECT COUNT(*) from things")
    fun getCount(): Int
}
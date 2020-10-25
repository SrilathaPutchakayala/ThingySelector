package com.sample.thingyselector.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sample.thingyselector.data.AppDatabase
import com.sample.thingyselector.data.SampleDataProvider
import com.sample.thingyselector.models.Thing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThingViewModel(app: Application) : AndroidViewModel(app) {


    private val database = AppDatabase.getInstance(app)
    val thingsList = database?.thingDao()?.getAll()
    val selectedThingsList = database?.thingDao()?.getSelectedThings()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleThings = SampleDataProvider.getThings()
                database?.thingDao()?.deleteAll()
                database?.thingDao()?.insertAll(sampleThings)
            }
        }
    }

    fun updateThing(thing: Thing){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.thingDao()?.updateThing(thing)
            }
        }
    }
}
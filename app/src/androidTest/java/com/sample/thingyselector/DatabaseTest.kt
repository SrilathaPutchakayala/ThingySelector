package com.sample.thingyselector

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.thingyselector.data.AppDatabase
import com.sample.thingyselector.data.ThingDao
import com.sample.thingyselector.models.Thing
import com.sample.thingyselector.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: ThingDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.thingDao()!!
    }

    @Test
    fun createThings() {
        dao.insertAll(SampleDataProvider.getThings())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getThings().size)
    }

    @Test
    fun insertThing() {
        val thing = Thing()
        thing.text = "some text"
        dao.insertThing(thing)
        val savedThing = dao.getThingById(1)
        assertEquals(savedThing?.id ?: 0, 1)
    }

    @After
    fun closeDb() {
        database.close()
    }
}
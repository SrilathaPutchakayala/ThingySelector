package com.sample.thingyselector.data

import com.sample.thingyselector.models.Thing
import java.util.*

class SampleDataProvider {

    companion object {

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getThings() = arrayListOf(
            Thing(getDate(0),"Thing 1",false),
            Thing(getDate(0),"Thing 2",false),
            Thing(getDate(0),"Thing 3",false),
            Thing(getDate(0),"Thing 4",false),
            Thing(getDate(0),"Thing 5",false)
        )
    }
}
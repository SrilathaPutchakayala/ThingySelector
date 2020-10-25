package com.sample.thingyselector.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.thingyselector.NEW_THING_ID
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "things")
data class Thing(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var text: String,
    var selected: Boolean
) : Parcelable {
    constructor() : this(NEW_THING_ID, Date(), "",false)
    constructor(date: Date, text: String, selected: Boolean) : this(NEW_THING_ID, date, text,selected)
}
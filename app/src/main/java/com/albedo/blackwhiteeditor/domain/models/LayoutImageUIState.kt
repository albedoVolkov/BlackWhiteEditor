package com.albedo.blackwhiteeditor.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albedo.blackwhiteeditor.data.utils.ConstantsData
import com.google.gson.Gson
import java.util.Date


@Entity(tableName = ConstantsData.LAYOUTS_ROOM_TABLE_NAME)
data class LayoutImageUIState (
    @PrimaryKey
    @ColumnInfo("id")
    val id : String,
    @ColumnInfo("name")
    val name : String,
    @ColumnInfo("date")
    val date : Date,
    @ColumnInfo("data")
    val data : ImageUIState,
){
    override fun toString(): String {
        return Gson().toJson(this,LayoutImageUIState::class.java)
    }
}

fun fromStringToLayoutImageUIStateItem(string: String): LayoutImageUIState? {
    return try {
        Gson().fromJson(string, LayoutImageUIState::class.java)
    }catch(e : Exception){
        null
    }
}
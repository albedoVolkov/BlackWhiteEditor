package com.albedo.blackwhiteeditor.data.utils

import androidx.room.TypeConverter
import com.albedo.blackwhiteeditor.domain.models.ImageUIState
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import com.albedo.blackwhiteeditor.domain.models.fromStringToImageUIStateItem
import com.albedo.blackwhiteeditor.domain.models.fromStringToLayoutImageUIStateItem
import com.google.gson.Gson
import java.util.Date
import javax.inject.Inject

class ListTypeConverter @Inject constructor(){
    @TypeConverter
    fun fromStringListToString(value: List<String>): String {
        return value.joinToString(separator = "||,||")
    }
    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {

        if (value == "" || value == "[]" || value == "null"){
            return listOf()
        }
        return value.split("||,||").map { it }
    }



    @TypeConverter
    fun fromStringToIntList(value: String): List<Int> {
        if (value == ""){
            return listOf()
        }
        return value.split("||,||").map { it.toInt() }
    }
    @TypeConverter
    fun fromIntListToString(value: List<Int>): String {
        return value.joinToString(separator = "||,||")
    }



    @TypeConverter
    fun fromDateToString(value: Date): String { return Gson().toJson(value,Date::class.java) }

    @TypeConverter
    fun fromStringToDate(value: String): Date { return Gson().fromJson(value,Date::class.java) }




    @TypeConverter
    fun fromLayoutImageUIStateListToString(value: List<LayoutImageUIState>): String {
        return value.joinToString(separator = "||,||")
    }
    @TypeConverter
    fun fromStringToLayoutImageUIStateList(value: String): List<LayoutImageUIState> {
        if (value == "" || value == "[]" || value == "null"){
            return listOf()
        }
        return value.split("||,||").map { fromStringToLayoutImageUIStateItem(it)!! }
    }

    @TypeConverter
    fun fromLayoutImageUIStateToString(value: LayoutImageUIState): String { return value.toString() }

    @TypeConverter
    fun fromStringToLayoutImageUIState(value: String): LayoutImageUIState { return fromStringToLayoutImageUIStateItem(value)!! }


    @TypeConverter
    fun fromImageUIStateToString(value: ImageUIState): String { return value.toString() }

    @TypeConverter
    fun fromStringToImageUIState(value: String): ImageUIState { return fromStringToImageUIStateItem(value)!! }

}
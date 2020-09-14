package com.charished.absa.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListTypeConverter {

    // Convert a list of Strings to a Json String
    @TypeConverter
    fun fromString(stat: ArrayList<String>): String {
        return Gson().toJson(stat)
    }

    // Convert a Json String to a list of Strings
    @TypeConverter
    fun fromArrayList(jsonString: String): ArrayList<String> {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson<ArrayList<String>>(jsonString, type)
    }


}
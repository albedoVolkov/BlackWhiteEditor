package com.albedo.blackwhiteeditor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.albedo.blackwhiteeditor.data.local.LayoutsDao
import com.albedo.blackwhiteeditor.data.utils.ListTypeConverter
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import dagger.hilt.android.qualifiers.ApplicationContext


@Database(entities = [LayoutImageUIState::class, ], version = 1, exportSchema = true)
@TypeConverters(ListTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {


    abstract fun layoutsDao(): LayoutsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(@ApplicationContext context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "application_data_base"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}
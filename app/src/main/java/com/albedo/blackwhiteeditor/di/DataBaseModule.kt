package com.albedo.blackwhiteeditor.di

import android.content.Context
import com.albedo.blackwhiteeditor.AppDataBase
import com.albedo.blackwhiteeditor.data.local.LayoutsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getDataBase(context)
    }

    @Provides
    fun provideLayoutsDao(appDatabase: AppDataBase): LayoutsDao {
        return appDatabase.layoutsDao()
    }

}
package com.albedo.blackwhiteeditor.di


import com.albedo.blackwhiteeditor.data.repositories.LayoutsRepository
import com.albedo.blackwhiteeditor.domain.interfaces.LayoutsRepoInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // or whatever graph fits your need the best
interface RepositoryModules {
    @Binds
    fun provideLayoutsRepositoryImpl(repository: LayoutsRepository): LayoutsRepoInterface
}
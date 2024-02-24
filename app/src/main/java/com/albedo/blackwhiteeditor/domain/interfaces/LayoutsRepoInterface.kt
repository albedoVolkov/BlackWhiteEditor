package com.albedo.blackwhiteeditor.domain.interfaces

import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import kotlinx.coroutines.flow.Flow


interface LayoutsRepoInterface {

    suspend fun insert(data: LayoutImageUIState): Result<Boolean?>
    suspend fun delete(id: String): Result<Boolean?>

    suspend fun getList(): Result<List<LayoutImageUIState>?>
    fun getListFlow(): Flow<List<LayoutImageUIState>?>

    suspend fun getById(id : String) : Result<LayoutImageUIState?>
}
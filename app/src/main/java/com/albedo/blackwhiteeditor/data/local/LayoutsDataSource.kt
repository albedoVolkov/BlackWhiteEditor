package com.albedo.blackwhiteeditor.data.local

import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import kotlinx.coroutines.flow.Flow

interface LayoutsDataSource {

    suspend fun getListItems(): Result<List<LayoutImageUIState>?>
    fun getListItemsFlow(): Flow<List<LayoutImageUIState>?>

    suspend fun getItemById(id : String) : Result<LayoutImageUIState?>

    suspend fun addItem(item : LayoutImageUIState) : Result<Boolean?>

    suspend fun deleteItem(id : String) : Result<Boolean?>

    suspend fun deleteAllItems(): Result<Boolean?>

}
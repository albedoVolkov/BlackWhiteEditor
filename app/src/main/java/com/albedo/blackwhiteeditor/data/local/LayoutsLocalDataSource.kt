package com.albedo.blackwhiteeditor.data.local

import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LayoutsLocalDataSource @Inject internal constructor(
    private val dao: LayoutsDao,
) : LayoutsDataSource {

    //private val TAG = "LayoutsLocalDataSource"

    override suspend fun getListItems(): Result<List<LayoutImageUIState>?> = withContext(Dispatchers.IO) {
        try {
            val list = dao.getAllItems()
            return@withContext Result.success(list)
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }

    override fun getListItemsFlow(): Flow<List<LayoutImageUIState>?> = dao.getAllItemsFlow()

    override suspend fun getItemById(id: String): Result<LayoutImageUIState?> = withContext(Dispatchers.IO) {
        try {
            val item = dao.getItemById(id)
            return@withContext Result.success(item)
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }

    override suspend fun addItem(item: LayoutImageUIState): Result<Boolean?> = withContext(Dispatchers.IO) {
        try {
            dao.insertItem(item)
            return@withContext Result.success(true)
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }

    override suspend fun deleteItem(id: String): Result<Boolean?> = withContext(Dispatchers.IO) {
        try {
            dao.deleteItemById(id)
            return@withContext Result.success(true)
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }

    override suspend fun deleteAllItems(): Result<Boolean?> = withContext(Dispatchers.IO) {
        try {
            dao.clear()
            return@withContext Result.success(true)
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }


}
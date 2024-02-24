package com.albedo.blackwhiteeditor.data.repositories

import android.util.Log
import com.albedo.blackwhiteeditor.data.local.LayoutsLocalDataSource
import com.albedo.blackwhiteeditor.domain.interfaces.LayoutsRepoInterface
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LayoutsRepository @Inject constructor(
    private val localSource: LayoutsLocalDataSource
) : LayoutsRepoInterface {

    private val TAG = "LayoutsRepository"


    override suspend fun insert(data: LayoutImageUIState): Result<Boolean?> {
        val res = localSource.addItem(data)
        Log.d(TAG, "addItemLocalSource : data = $data : result = ${res.getOrNull()}")
        return res
    }


    override suspend fun delete(id: String): Result<Boolean?> {
        if (id != "" && id != "-1") {
            val oldItem = getById(id)

            if (oldItem.isSuccess && oldItem.getOrNull() != null) {
                val res = localSource.deleteItem(id)
                Log.d(TAG, "deleteUserLocalSource : id = $id : result = ${res.getOrNull()}")
                return res

            }else{ return Result.success(false) }

        }else{ return Result.success(false) }
    }


    override suspend fun getList(): Result<List<LayoutImageUIState>?> {
        val list = localSource.getListItems()
        Log.d(TAG, "getItemsList : isSuccess = ${list.isSuccess} : result = ${list.getOrNull()}")
        return list
    }


    override fun getListFlow(): Flow<List<LayoutImageUIState>?> = localSource.getListItemsFlow()


    override suspend fun getById(id : String) : Result<LayoutImageUIState?>   {
        val item = localSource.getItemById(id)
        Log.d(TAG, "getItemsList : isSuccess = ${item.isSuccess} : result = ${item.getOrNull()}")
        return item
    }

}
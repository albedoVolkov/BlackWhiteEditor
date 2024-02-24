package com.albedo.blackwhiteeditor.data.local

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import com.albedo.blackwhiteeditor.data.utils.ConstantsData
    import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface LayoutsDao  {
        @Insert(LayoutImageUIState::class,onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertItem(item: LayoutImageUIState)

        @Query("DELETE FROM ${ConstantsData.LAYOUTS_ROOM_TABLE_NAME} where id =:id")
        suspend fun deleteItemById(id: String)

        @Query("SELECT * FROM ${ConstantsData.LAYOUTS_ROOM_TABLE_NAME}")
        suspend fun getAllItems(): List<LayoutImageUIState>

        @Query("SELECT * FROM ${ConstantsData.LAYOUTS_ROOM_TABLE_NAME}")
        fun getAllItemsFlow(): Flow<List<LayoutImageUIState>?>

        @Query("SELECT * FROM ${ConstantsData.LAYOUTS_ROOM_TABLE_NAME} WHERE id =:idMain LIMIT 1")
        suspend fun getItemById(idMain : String): LayoutImageUIState?

        @Query("delete from ${ConstantsData.LAYOUTS_ROOM_TABLE_NAME}")
        suspend fun clear()

    }
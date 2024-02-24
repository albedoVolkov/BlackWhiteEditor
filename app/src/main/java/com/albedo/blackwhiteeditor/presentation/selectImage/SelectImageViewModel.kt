package com.albedo.blackwhiteeditor.presentation.selectImage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import com.albedo.blackwhiteeditor.domain.usecases.DeleteLayoutFromListByIdUseCase
import com.albedo.blackwhiteeditor.domain.usecases.GetListLayoutsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectImageViewModel @Inject constructor(
    getListLayoutsFlow : GetListLayoutsFlowUseCase,
    private val deleteLayoutFromListByIdUseCase : DeleteLayoutFromListByIdUseCase,
): ViewModel() {

    private val TAG = "SelectImageViewModel"

    private var _showList : List<LayoutImageUIState> = listOf()
    val showList: List<LayoutImageUIState> get() = _showList

    private var _mainList : List<LayoutImageUIState> = listOf()
    //val mainList: List<LayoutImageUIState> get() = _mainList// this list isn't for showing and not sorted

    val data = getListLayoutsFlow.execute().asLiveDataFlow()
    private fun <T> Flow<T>.asLiveDataFlow() = shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)


    fun filterItems(filterType: String) {
        _showList = when (filterType) {
            "None" -> emptyList()
            "All" -> _mainList
            "Reversed" -> _mainList.reversed()
            else -> emptyList()
        }
    }

    fun setListInViewModel(list : List<LayoutImageUIState>) {
        Log.d(TAG, "setListDishesInViewModel : list - $list")
        _mainList = list
    }

    fun deleteLayout(id : String) {
        Log.d(TAG, "deleteLayout : id - $id")
        viewModelScope.launch {
            deleteLayoutFromListByIdUseCase.execute(id)
        }
    }

}
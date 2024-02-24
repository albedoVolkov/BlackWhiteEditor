package com.albedo.blackwhiteeditor.domain.usecases

import com.albedo.blackwhiteeditor.domain.interfaces.LayoutsRepoInterface
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListLayoutsFlowUseCase @Inject constructor(private val repo : LayoutsRepoInterface) {
    fun execute() : Flow<List<LayoutImageUIState>?> = repo.getListFlow()
}
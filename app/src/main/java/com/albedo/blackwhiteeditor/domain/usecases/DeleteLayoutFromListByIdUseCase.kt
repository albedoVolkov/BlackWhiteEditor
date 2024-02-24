package com.albedo.blackwhiteeditor.domain.usecases

import com.albedo.blackwhiteeditor.domain.interfaces.LayoutsRepoInterface
import javax.inject.Inject

class DeleteLayoutFromListByIdUseCase @Inject constructor(private val repo : LayoutsRepoInterface) {
    suspend fun execute(id : String){
        repo.delete(id)
    }
}
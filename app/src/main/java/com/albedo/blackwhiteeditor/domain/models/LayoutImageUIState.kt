package com.albedo.blackwhiteeditor.domain.models

import java.util.Date

class LayoutImageUIState (
    val id : String,
    val name : String,
    val date : Date,
    val data : ImageUIState,
    val isSystem : Boolean,
)
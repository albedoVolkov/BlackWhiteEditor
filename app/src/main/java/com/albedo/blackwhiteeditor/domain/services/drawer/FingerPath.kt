package com.albedo.blackwhiteeditor.domain.services.drawer

import android.graphics.Path

class FingerPath(
    var color: Int,
    var emboss: Boolean,
    var blur: Boolean,
    var strokeWidth: Int,
    var path: Path,
)
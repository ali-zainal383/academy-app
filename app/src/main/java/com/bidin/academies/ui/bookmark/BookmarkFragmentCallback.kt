package com.bidin.academies.ui.bookmark

import com.bidin.academies.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

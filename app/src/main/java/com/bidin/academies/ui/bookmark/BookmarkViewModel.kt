package com.bidin.academies.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bidin.academies.data.CourseEntity
import com.bidin.academies.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks() : List<CourseEntity> = DataDummy.generateDummyCourses()
}
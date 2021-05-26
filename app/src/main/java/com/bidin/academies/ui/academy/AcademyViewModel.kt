package com.bidin.academies.ui.academy

import androidx.lifecycle.ViewModel
import com.bidin.academies.data.CourseEntity
import com.bidin.academies.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}
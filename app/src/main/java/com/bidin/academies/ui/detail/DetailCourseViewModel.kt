package com.bidin.academies.ui.detail

import androidx.lifecycle.ViewModel
import com.bidin.academies.data.CourseEntity
import com.bidin.academies.data.ModuleEntity
import com.bidin.academies.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        lateinit var course: CourseEntity
        val coursesEntities = DataDummy.generateDummyCourses()
        for(courseEntity in coursesEntities) {
            if (courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }
        return course
    }

    fun getModules() : List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}
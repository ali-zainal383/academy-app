package com.bidin.academies.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}
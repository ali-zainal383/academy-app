package com.bidin.academies.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bidin.academies.R
import com.bidin.academies.data.CourseEntity
import com.bidin.academies.databinding.ActivityDetailCourseBinding
import com.bidin.academies.databinding.ContentDetailCourseBinding
import com.bidin.academies.ui.reader.CourseReaderActivity
import com.bidin.academies.utils.DataDummy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailContentBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBiding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBiding.detailContent

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]

        setContentView(activityDetailCourseBiding.root)

        setSupportActionBar(activityDetailCourseBiding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val extras = intent.extras
        if (extras != null){
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                val modules = viewModel.getModules()
                adapter.setModules(modules)
                populateCourse(viewModel.getCourse() as CourseEntity)

                with(detailContentBinding.rvModule) {
                    isNestedScrollingEnabled = false
                    layoutManager = LinearLayoutManager(this@DetailCourseActivity)
                    setHasFixedSize(true)
                    this.adapter = adapter
                    val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                    addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.description
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
                .load(courseEntity.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)

        detailContentBinding.btStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}
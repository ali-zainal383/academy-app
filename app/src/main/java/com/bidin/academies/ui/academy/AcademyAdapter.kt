package com.bidin.academies.ui.academy

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bidin.academies.R
import com.bidin.academies.data.CourseEntity
import com.bidin.academies.databinding.ItemsAcademyBinding
import com.bidin.academies.ui.detail.DetailCourseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.CourseViewHolder>() {

    private var listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>?) {
        if (courses == null) return
        this.listCourses.clear()
        this.listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsAcademyBinding = ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(viewHolder: CourseViewHolder, position: Int) {
        val course = listCourses[position]
        viewHolder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size

    class CourseViewHolder(private val binding: ItemsAcademyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(course.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}
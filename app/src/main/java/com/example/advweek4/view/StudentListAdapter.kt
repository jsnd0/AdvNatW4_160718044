package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.databinding.StudentListItemBinding
import com.example.advweek4.model.Student
import kotlinx.android.synthetic.main.student_list_item.view.*
import util.loadImage

class StudentListAdapter (val studentList:ArrayList<Student>): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailCLickListener {
    class StudentViewHolder(val view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateStudentList(newStudentList: List<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val v = inflater.inflate(R.layout.student_list_item, parent, false)
        val v = DataBindingUtil.inflate<StudentListItemBinding>(
            inflater,
            R.layout.student_list_item,
            parent,
            false
        )
        return StudentViewHolder(v)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener=this
//        holder.view.txtId.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name
//        holder.view.imageView.loadImage(studentList[position].photoUrl, holder.view.progressBar)
//
//
//        holder.view.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun onButtonDetailCLick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}
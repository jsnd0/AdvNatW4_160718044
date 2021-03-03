package com.example.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advweek4.R
import com.example.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*


class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(
            viewLifecycleOwner,
            Observer { studentListAdapter.updateStudentList(it) })
        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if (it) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
            viewModel.loadingDoneLD.observe(viewLifecycleOwner, Observer {
                if (it) {
                    progressLoad.visibility = View.GONE
                    recView.visibility = View.VISIBLE
                } else {
                    progressLoad.visibility = View.VISIBLE
                    recView.visibility = View.GONE
                }
            })
        })
    }


}
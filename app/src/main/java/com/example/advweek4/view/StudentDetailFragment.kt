package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.R
import com.example.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import util.loadImage
import java.util.*
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(
            viewLifecycleOwner,
            Observer {
                txtId.setText(viewModel.studentLD.value?.id.toString())
                txtName.setText(viewModel.studentLD.value?.name.toString())
                txtDob.setText(viewModel.studentLD.value?.dob.toString())
                txtPhone.setText(viewModel.studentLD.value?.phone.toString())
                imageView2.loadImage(viewModel.studentLD.value?.photoUrl, progressBarD)

                var student = it
                btnNotif.setOnClickListener {
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe() {
                            MainActivity.showNotification(
                                student.name.toString(),
                                "A new notification created",
                                R.drawable.ic_baseline_person_24
                            )
                        }
                }
            })
    }
}

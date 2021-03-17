package com.example.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    var TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(studentId: String) {
        val student1 = Student(
            "16055",
            "Nonie",
            "1998/03/28",
            "5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff"
        )
        studentLD.value = student1


        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"
        Log.d("showvolleyd", url.toString())
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Gson().fromJson<Student>(response, Student::class.java)
                studentLD.value = result
                Log.d("showvolleyd", response.toString())
            }, {
                Log.d("showvolleyd", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
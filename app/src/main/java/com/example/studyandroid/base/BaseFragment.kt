package com.example.studyandroid.base

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun<T> startA(clazz:Class<T>,title:String){
        val intent = Intent(requireContext(), clazz)
        intent.putExtra("title",title)
        startActivity(intent)
    }

    fun <T> showToast(value:T){
        Toast.makeText(requireContext(),"$value", Toast.LENGTH_LONG).show()
    }


}
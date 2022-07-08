package com.example.studyandroid.study.coordinator_layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.R
import com.example.studyandroid.study.databinding_and_rv.DataBindingAndRvAdapter

class CoordinatorLayoutFragment02 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coordinator_layout02, container, false)
        val rv: RecyclerView = view.findViewById(R.id.coordinator_fragment02_list)
        val toolbar: Toolbar = view.findViewById(R.id.coordinator_fragment02_toolbar)
        toolbar.title = "江江江江江江"
        val dataList = ArrayList<String>()
        repeat(100) {
            dataList.add("Item $it")
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = DataBindingAndRvAdapter(dataList)
        }



        return view
    }
}
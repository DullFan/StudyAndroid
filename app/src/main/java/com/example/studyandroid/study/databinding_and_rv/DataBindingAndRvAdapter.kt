package com.example.studyandroid.study.databinding_and_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.R
import com.example.studyandroid.databinding.ItemDataBindingAndRvBinding

class DataBindingAndRvAdapter(var dataList: List<String>) :
    RecyclerView.Adapter<DataBindingAndRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_data_binding_and_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataBindingAndRvAdapter.ViewHolder, position: Int) {
        holder.dataBinding.data = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val dataBinding: ItemDataBindingAndRvBinding) :
        RecyclerView.ViewHolder(dataBinding.root)
}


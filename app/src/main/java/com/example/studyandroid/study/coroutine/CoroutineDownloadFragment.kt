package com.example.studyandroid.study.coroutine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyandroid.databinding.FragmentCoroutineDownloadBinding


class CoroutineDownloadFragment : Fragment() {
    val viewDataBinding by lazy {
        FragmentCoroutineDownloadBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return viewDataBinding.root
    }
}
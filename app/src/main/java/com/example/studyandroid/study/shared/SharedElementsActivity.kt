package com.example.studyandroid.study.shared

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivitySharedElementsBinding

class SharedElementsActivity : BaseActivity() {
    lateinit var viewBinding: ActivitySharedElementsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySharedElementsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val mutableListOf = mutableListOf<String>()
        for (i in 0..100) {
            mutableListOf.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试")
        }
        viewBinding.sharedRv.layoutManager = LinearLayoutManager(this)
        viewBinding.sharedRv.adapter = MyAdapter(mutableListOf){imageView,textView ->
            val intent = Intent(this,ShareDetailsActivity::class.java)
            intent.putExtra("title","详情信息")
            //实现单个View动画
            //val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            //  this,imageView,"image"
            //  )
            val pair = Pair<View,String>(imageView,"image")
            val pairText = Pair<View,String>(textView,"text")

            val optionsCompat2 = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, pair,pairText
            )




            startActivity(intent,optionsCompat2.toBundle())
        }

    }
}


class MyAdapter(val dataList: List<String>,val onClick:(image:ImageView,textView:TextView) -> Unit) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.shared_item_text)
        val imageView = itemView.findViewById<ImageView>(R.id.shared_item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shared_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.textView.text = dataList[position]
        holder.itemView.setOnClickListener {
            onClick(holder.imageView,holder.textView)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
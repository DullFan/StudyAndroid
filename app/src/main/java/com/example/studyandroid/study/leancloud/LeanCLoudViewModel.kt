package com.example.studyandroid.study.leancloud

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cn.leancloud.LCException
import cn.leancloud.LCObject
import cn.leancloud.LCQuery
import cn.leancloud.LCUser
import cn.leancloud.livequery.LCLiveQuery
import cn.leancloud.livequery.LCLiveQueryEventHandler
import cn.leancloud.livequery.LCLiveQuerySubscribeCallback
import com.example.studyandroid.utils.showLog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LeanCLoudViewModel(application: Application) : AndroidViewModel(application) {
    var dataListLive = MutableLiveData<List<LCObject>>()

    init {
        val query = LCQuery<LCObject>("Word")
        query.whereEqualTo("user", LCUser.getCurrentUser())
        query.findInBackground().subscribe(object : Observer<List<LCObject>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<LCObject>) {
                showLog(t)
                dataListLive.value = t
            }

            override fun onError(e: Throwable) {
                Toast.makeText(application, "没有查到东西哦", Toast.LENGTH_LONG).show()
            }

            override fun onComplete() {
            }
        })

        val liveQuery = LCLiveQuery.initWithQuery(query)
        liveQuery.subscribeInBackground(object : LCLiveQuerySubscribeCallback() {
            override fun done(e: LCException?) {

            }
        })
        liveQuery.setEventHandler(object : LCLiveQueryEventHandler() {
            //创建时回调
            override fun onObjectCreated(LCObject: LCObject?) {
                super.onObjectCreated(LCObject)
                val t = dataListLive.value?.toMutableList()
                if (LCObject != null) {
                    t?.add(LCObject)
                    dataListLive.value = t
                }
            }

            //删除时回调
            override fun onObjectDeleted(objectId: String?) {
                super.onObjectDeleted(objectId)
                val t = dataListLive.value?.toMutableList()
                val ob = t?.find {
                    it.get("objectId") == objectId
                }
                t?.remove(ob)
                dataListLive.value = t
            }

            //更新时回调
            override fun onObjectUpdated(LCObject: LCObject?, updateKeyList: MutableList<String>?) {
                super.onObjectUpdated(LCObject, updateKeyList)
                val ob = dataListLive.value?.find {
                    it.get("objectId") == LCObject?.get("objectId")
                }
                updateKeyList?.forEach {
                    ob?.put(it, LCObject?.get(it))
                }
                dataListLive.value = dataListLive.value
            }
        })
    }


    fun addWord(newWord: String) {
        LCObject("Word").apply {
            put("word", newWord)
            put("user", LCUser.getCurrentUser())
            saveInBackground().subscribe(object : Observer<LCObject> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: LCObject) {
                    Toast.makeText(getApplication(), "添加成功", Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(getApplication(), "添加失败", Toast.LENGTH_LONG).show()
                }

                override fun onComplete() {

                }
            })
        }
    }
}
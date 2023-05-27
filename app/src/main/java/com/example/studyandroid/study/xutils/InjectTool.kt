package com.example.studyandroid.study.xutils

import com.example.studyandroid.study.xutils.annation.BindView
import com.example.studyandroid.study.xutils.annation.ContentView
import com.example.studyandroid.utils.showLog
import org.jetbrains.annotations.Contract

class InjectTool {

    companion object {
        @Contract(pure = true)
        fun inject(any: Any) {
            injectSetContentView(any)
            injectBindView(any)
        }

        /**
         * 把控件注入到Activity中去
         * @param any Activity中的this
         */
        private fun injectBindView(any: Any) {
            val activityClass = any.javaClass

            //获取 Activity 里所有的字段
            val fields = activityClass.declaredFields

            fields.forEach { field ->
                //让 JVM 不要去处理private修饰的
                field.isAccessible = true
                val bindView = field.getAnnotation(BindView::class.java)
                if (bindView == null) {
                    showLog("BindView is null")
                    return
                }
                val viewId = bindView.value

                try {
                    // 反射 1 findViewById(viewID == R.id.bt_t2)
                    val findViewByIdMethod = activityClass.getMethod("findViewById", Int::class.java)
                    val resultView = findViewByIdMethod.invoke(any, viewId)
                    // 2 获取反射的值 赋值给 button1  button2  button3
                    field.set(any, resultView)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        /**
         * 将布局绑定至 Activity
         * @param any Activity中的this
         */
        @Contract(pure = true)
        private fun injectSetContentView(any: Any) {
            val mActivityClass = any.javaClass

            // 获取@ContentView 注解
            val mContentView = mActivityClass.getAnnotation(ContentView::class.java)

            if (mContentView == null) {
                showLog("ContentView is null")
                return
            }

            // 获取布局文件
            val layoutId = mContentView.value
            try {
                //寻找setContentView()函数，并且寻找传递的参数类型为Int类型
                val setContentViewMethod =
                    mActivityClass.getMethod("setContentView", Int::class.java)
                //执行setContentView()函数
                //第一个参数为:反射的示例.第二个参数为:这个函数需要的值
                setContentViewMethod.invoke(any, layoutId)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
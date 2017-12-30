package com.lvqingyang.frame

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.ev_empty.view.*
import kotlinx.android.synthetic.main.ev_fail.view.*
import kotlinx.android.synthetic.main.ev_layout.view.*
import kotlinx.android.synthetic.main.ev_loading.view.*
import kotlinx.android.synthetic.main.ev_no_connection.view.*
import java.util.*

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2017/12/29
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

public class EmptyView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var mViewShow: View?=null

    /**
     * 扩展TypedArray的方法
     */
    public fun TypedArray.initImage(iv: ImageView, index: Int){
        val drawable=this.getDrawable(index)
        if (drawable != null) {
            iv.setImageDrawable(drawable)
        }
    }

    public fun TypedArray.initText(tv: TextView, index: Int){
        val text=this.getString(index)
        if (text != null) {
            tv.text=text
        }
    }

    init {
        val inflater=LayoutInflater.from(context)
        inflater.inflate(R.layout.ev_layout, this)

        if (context!=null && attrs != null) {
             val arr=context.theme.obtainStyledAttributes(
                    attrs, R.styleable.EmptyView, 0, 0)
            try {
                //show view
                val layoutId=arr.getResourceId(R.styleable.EmptyView_view_show, -1)
                if (layoutId!=-1) {
                    mViewShow=inflater.inflate(layoutId, null)
                    container.addView(mViewShow, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                }

                arr.initImage(iv_empty, R.styleable.EmptyView_empty_icon)
                arr.initImage(iv_fail, R.styleable.EmptyView_fail_icon)
                arr.initImage(iv_no_connection, R.styleable.EmptyView_no_connection_icon)

                arr.initText(tv_empty, R.styleable.EmptyView_empty_text)
                arr.initText(tv_empty_info, R.styleable.EmptyView_empty_text_info)
                arr.initText(tv_fail, R.styleable.EmptyView_fail_text)
                arr.initText(tv_fail_info, R.styleable.EmptyView_fail_text_info)

                setIndicator()
            }finally {
                arr.recycle()
            }
        }
    }

    public fun loading(){
        changeState(layout_loading)
    }

    public fun failed(){
        changeState(layout_failed)
    }

    public fun noConnection(){
        changeState(layout_no_connection)
    }

    public fun empty(){
        changeState(layout_empty)
    }

    public fun success(){
        changeState(mViewShow)
    }

    public fun setOnRetryListener(listener: OnClickListener){
        layout_no_connection.setOnClickListener{
            loading()
            listener.onClick(it)
        }
    }

    private fun changeState(viewShow: View?){
        for (i in 0 until container.childCount){
            container.getChildAt(i).visibility= View.GONE
        }
        viewShow?.visibility= View.VISIBLE
    }

    private fun setIndicator() {
        val arrayOfString = resources.getStringArray(R.array.arr_indicator)
        val i = Random().nextInt(arrayOfString.size)
        av_loading.setIndicator(arrayOfString[i])
    }

}
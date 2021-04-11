package com.app.movieguidemvvm.ui.listeners

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.movieguidemvvm.model.Search

open class OnMovieItemClick(context: Context, private val listener: onMovieSelected) :
        RecyclerView.OnItemTouchListener  {

    private var mGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })

    interface onMovieSelected {
        fun onMovieClick(view: View)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val childView = rv.findChildViewUnder(e.x, e.y)

        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            listener.onMovieClick(childView)
        }

        return false

    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {


    }
}
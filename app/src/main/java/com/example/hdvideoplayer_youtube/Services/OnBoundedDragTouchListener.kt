package com.example.hdvideoplayer_youtube.Services

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class OnBoundedDragTouchListener(var mView: View, var mParent: View = mView.parent as View) : View.OnTouchListener {
    private var isDragging = false
    private var isInitialized = false

    private var xWhenAttached = 0f
    private var yWhenAttached = 0f

    private var maxLeft = 0f
    private var maxRight = 0f
    private var maxTop = 0f
    private var maxBottom = 0f

    private var width = 0
    private var height = 0

    private var dX = 0f
    private var dY = 0f

    private lateinit var mOnDragActionListener: OnDragActionListener

    fun setOnDragActionListener(mOnDragActionListener: OnDragActionListener) {
        this.mOnDragActionListener = mOnDragActionListener
    }

    interface OnDragActionListener {
        fun onDragStart(view: View)
        fun onDragEnd(view: View)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (isDragging) {
            val bounds = FloatArray(4)
            // LEFT
            bounds[0] = event.rawX + dX
            if (bounds[0] < maxLeft) {
                bounds[0] = maxLeft
            }
            // RIGHT
            bounds[2] = bounds[0] + width
            if (bounds[2] > maxRight) {
                bounds[2] = maxRight
                bounds[0] = bounds[2] - width
            }
            // TOP
            bounds[1] = event.rawY + dY
            if (bounds[1] < maxTop) {
                bounds[1] = maxTop
            }
            // BOTTOM
            bounds[3] = bounds[1] + height
            if (bounds[3] > maxBottom) {
                bounds[3] = maxBottom
                bounds[1] = bounds[3] - height
            }

            when (event.action) {

                MotionEvent.ACTION_MOVE -> {
                    mView.animate().x(bounds[0]).y(bounds[1]).setDuration(0).start()
                    mOnDragActionListener.onDragStart(mView)
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> onDragFinish()

            }
        } else {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isDragging = true
                    if (!isInitialized) {
                        updateBounds()
                    }
                    dX = v.x - event.rawX
                    dY = v.y - event.rawY
                    mOnDragActionListener.onDragStart(mView)
                }
            }
        }

        return true
    }

    private fun onDragFinish() {
        mOnDragActionListener.onDragEnd(mView)
        dX = 0f
        dY = 0f
        isDragging = false
    }

    private fun updateBounds() {
        updateViewBounds()
        updateParentBounds()
        isInitialized = true
    }

    private fun updateViewBounds() {
        width = mView.width
        xWhenAttached = mView.x
        dX = 0f
        height = mView.height
        yWhenAttached = mView.y
        dY = 0f
    }

    private fun updateParentBounds() {
        maxLeft = -mView.width * 1f / 2
        maxTop = -mView.height * 1f / 2
        maxRight = mParent.width.toFloat() + mView.width * 1f / 2
        maxBottom = mParent.height.toFloat() + mView.height * 1f / 2

//        maxLeft = 0f                          //old one
//        maxTop = 0f                           //old one
//        maxRight = maxLeft + mParent.width    //old one
//        maxBottom = maxTop + mParent.height   //old one
    }
}

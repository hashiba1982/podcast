package com.example.podcast.tools

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class SquareLayout : RelativeLayout {
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(context: Context?) : super(context) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // For simple implementation, or internal size is always 0.
        // We depend on the container to specify the layout size of
        // our view. We can't really know what it is since we will be
        // adding and removing different arbitrary views and do not
        // want the layout to change as this happens.
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec
        setMeasuredDimension(
            View.getDefaultSize(0, widthMeasureSpec),
            View.getDefaultSize(0, heightMeasureSpec)
        )

        // Children are just made to fill our space.
        val childWidthSize = measuredWidth
        val childHeightSize = measuredHeight
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY)
        heightMeasureSpec = widthMeasureSpec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
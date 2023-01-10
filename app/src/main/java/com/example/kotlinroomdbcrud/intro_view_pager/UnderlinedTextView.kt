package com.example.kotlinroomdbcrud.intro_view_pager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.IntDef
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.intro_view_pager.UnderlinedTextView.UnderLinePosition.Companion.POSITION_BASELINE
import com.example.kotlinroomdbcrud.intro_view_pager.UnderlinedTextView.UnderLinePosition.Companion.POSITION_BELOW

class UnderlinedTextView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(POSITION_BASELINE, POSITION_BELOW)
    annotation class UnderLinePosition {
        companion object {
            const val POSITION_BASELINE = 0
            const val POSITION_BELOW = 1
        }
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    var lineColor: Int
        get() = linePaint.color
        set(value) {
            if (linePaint.color != value) {
                linePaint.color = value
                invalidate()
            }
        }

    var lineHeight: Float
        get() = linePaint.strokeWidth
        set(value) {
            if (linePaint.strokeWidth != value) {
                linePaint.strokeWidth = value
                updateSpacing()
            }
        }

    var lineTopOffset = 0F
        set(value) {
            if (field != value) {
                field = value
                updateSpacing()
            }
        }

    @UnderLinePosition
    var linePosition = POSITION_BASELINE

    private val rect = Rect()

    private var internalAdd: Float = lineSpacingExtra

    private inline val extraSpace
        get() = lineTopOffset + lineHeight

    init {
        val density = context.resources.displayMetrics.density

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderlinedTextView, defStyleAttr, 0)
        lineColor = typedArray.getColor(R.styleable.UnderlinedTextView_underlineColor, currentTextColor)
        lineTopOffset = typedArray.getDimension(R.styleable.UnderlinedTextView_underlineOffset, 0f)
        lineHeight = typedArray.getDimension(R.styleable.UnderlinedTextView_underlineHeight, density * 1)
        linePosition = typedArray.getInt(R.styleable.UnderlinedTextView_underLinePosition, POSITION_BASELINE)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight + (extraSpace + 0.5f).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.takeIf { !text.isNullOrEmpty() }?.let {
            val count = lineCount
            val layout = layout
            var xStart: Float
            var xStop: Float
            var yStart: Float
            var firstCharInLine: Int
            var lastCharInLine: Int
            var lastLine: Boolean
            var offset: Int
            val lineSpacing = lineSpacingExtra * lineSpacingMultiplier
            for (i in 0 until count) {
                val baseline = getLineBounds(i, rect)
                lastLine = i == count - 1
                offset = if (lastLine) 0 else 1
                firstCharInLine = layout.getLineStart(i)
                lastCharInLine = layout.getLineEnd(i)
                xStart = layout.getPrimaryHorizontal(firstCharInLine)
                xStop = layout.getPrimaryHorizontal(lastCharInLine - offset)
                yStart = when (linePosition) {
                    POSITION_BASELINE -> baseline + lineTopOffset
                    POSITION_BELOW -> (rect.bottom + lineTopOffset) - if (lastLine) 0F else lineSpacing
                    else -> throw NotImplementedError("")
                }
                canvas.drawRect(xStart, yStart, xStop, yStart + lineHeight, linePaint)
            }
        }
        super.onDraw(canvas)
    }

    private fun updateSpacing() {
        setLineSpacing(internalAdd, 1f)
    }

    override fun setLineSpacing(add: Float, mult: Float) {
        internalAdd = add
        super.setLineSpacing(add + extraSpace, 1f)
    }
}
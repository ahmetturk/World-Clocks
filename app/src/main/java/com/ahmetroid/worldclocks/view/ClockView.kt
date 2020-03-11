package com.ahmetroid.worldclocks.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import com.ahmetroid.worldclocks.R
import java.util.*

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val clockPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private var timeDifference = 0

    private val rect = RectF()
    private val hourSignRect = RectF()
    private val minuteHandPath = Path().apply {
        fillType = Path.FillType.WINDING
    }
    private val hourHandPath = Path().apply {
        fillType = Path.FillType.WINDING
    }

    private val calendar = Calendar.getInstance()
    private val hour
        get() = (calendar.get(Calendar.HOUR) + timeDifference) * 30 + calendar.get(Calendar.MINUTE) * 0.5f + 180
    private val minute
        get() = calendar.get(Calendar.MINUTE) * 6f + 180

    init {
        context.withStyledAttributes(attrs, R.styleable.ClockView) {
            backgroundPaint.color = getColor(R.styleable.ClockView_backgroundColor, Color.BLACK)
            clockPaint.color = getColor(R.styleable.ClockView_clockColor, Color.WHITE)
            timeDifference = getInt(R.styleable.ClockView_timeDifference, 0)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rect.set(0f, 0f, w.toFloat(), h.toFloat())
        hourSignRect.set(-w / 80f, 0f, w / 80f, w / 10f)

        val handHeight = w / 40f
        val handWidth = w / 40f
        val minuteHandWidth = w / 80f
        val hourHandWidth = w / 60f
        val minuteHandHeight = w / 2.5f
        val hourHandHeight = w / 4f

        minuteHandPath.moveTo(-handWidth, -handHeight)
        minuteHandPath.lineTo(handWidth, -handHeight)
        minuteHandPath.lineTo(minuteHandWidth, minuteHandHeight)
        minuteHandPath.lineTo(-minuteHandWidth, minuteHandHeight)
        minuteHandPath.lineTo(-handWidth, -handHeight)
        minuteHandPath.close()

        hourHandPath.moveTo(-handWidth, -handHeight)
        hourHandPath.lineTo(handWidth, -handHeight)
        hourHandPath.lineTo(hourHandWidth, hourHandHeight)
        hourHandPath.lineTo(-hourHandWidth, hourHandHeight)
        hourHandPath.lineTo(-handWidth, -handHeight)
        hourHandPath.close()
    }

    override fun onDraw(canvas: Canvas) {
        // First, draw background
        canvas.drawOval(rect, backgroundPaint)

        // Second, draw hour signs
        val hourSignMargin = hourSignRect.height() / 2
        canvas.withTranslation(rect.centerX(), hourSignMargin) {
            for (degree in 0 until 360 step 30) {
                canvas.withRotation(degree.toFloat(), 0f, rect.centerY() - hourSignMargin) {
                    drawRect(hourSignRect, clockPaint)
                }
            }
        }

        // Third, draw hands
        canvas.withTranslation(rect.centerX(), rect.centerY()) {
            canvas.withRotation(hour) {
                drawPath(hourHandPath, clockPaint)
            }
            canvas.withRotation(minute) {
                drawPath(minuteHandPath, clockPaint)
            }
        }

        // Fourth, draw center dot
        canvas.drawCircle(rect.centerX(), rect.centerY(), hourSignRect.width() / 2, backgroundPaint)
    }

    override fun setBackgroundColor(color: Int) {
        backgroundPaint.color = color
        invalidate()
    }

    fun setClockColor(color: Int) {
        clockPaint.color = color
        invalidate()
    }

    fun setTimeDifference(time: Int) {
        timeDifference = time
        invalidate()
    }
}
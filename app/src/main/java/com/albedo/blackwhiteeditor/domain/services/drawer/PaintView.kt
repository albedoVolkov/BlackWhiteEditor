package com.albedo.blackwhiteeditor.domain.services.drawer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.EmbossMaskFilter
import android.graphics.MaskFilter
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.albedo.blackwhiteeditor.presentation.main.MainActivity.Companion.TAG
import kotlin.math.abs


class PaintView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    private var mX = 0f
    private var mY = 0f
    private var mPath: Path? = null
    private val mPaint: Paint = Paint()
    private val paths = ArrayList<FingerPath>()
    private var currentColor = 0
    private var backgroundColor = DEFAULT_BG_COLOR
    private var strokeWidth = 0
    private var emboss = false
    private var blur = false
    private val mEmboss: MaskFilter
    private val mBlur: MaskFilter
    private var mBitmap: Bitmap? = null
    private var mCanvas: Canvas? = null
    private val mBitmapPaint = Paint(Paint.DITHER_FLAG)

    init {
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = DEFAULT_COLOR
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.xfermode = null
        mPaint.alpha = 0xff
        mEmboss = EmbossMaskFilter(floatArrayOf(1f, 1f, 1f), 0.4f, 6f, 3.5f)
        mBlur = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
    }

    companion object {
        var BRUSH_SIZE = 20
        const val DEFAULT_COLOR = Color.BLACK
        const val DEFAULT_BG_COLOR = Color.WHITE
        private const val TOUCH_TOLERANCE = 4f
    }

    fun init(height : Int,width : Int ) {
        Log.d(TAG,"init is activated")
        Log.d(TAG,"height = $height")
        Log.d(TAG,"width = $width")

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap!!)

        currentColor = DEFAULT_COLOR
        strokeWidth = BRUSH_SIZE
    }

    fun normal() {
        Log.d(TAG,"normal is activated")
        emboss = false
        blur = false
    }

    fun emboss() {
        Log.d(TAG,"emboss is activated")
        emboss = true
        blur = false
    }

    fun blur() {
        Log.d(TAG,"blur is activated")
        emboss = false
        blur = true
    }

    fun clear() {
        Log.d(TAG,"clear is activated")
        backgroundColor = DEFAULT_BG_COLOR
        paths.clear()
        normal()
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        Log.d(TAG,"onDraw is activated")

        canvas.save()
        mCanvas!!.drawColor(backgroundColor)

        for(fp : FingerPath in paths) {
            mPaint.color = fp.color
            mPaint.strokeWidth = fp.strokeWidth.toFloat()
            mPaint.maskFilter = null

            if (fp.emboss)
                mPaint.maskFilter = mEmboss
            else if (fp.blur)
                mPaint.maskFilter = mBlur

            mCanvas?.drawPath(fp.path, mPaint)

        }

        canvas.drawBitmap(mBitmap!!, 0.0f, 0.0f, mBitmapPaint)
        canvas.restore()
    }


    private fun touchStart(x: Float, y: Float) {
        mPath = Path()
        val fp = FingerPath(currentColor, emboss, blur, strokeWidth, mPath!!)
        paths.add(fp)
        mPath!!.reset()
        mPath!!.moveTo(x, y)
        mX = x
        mY = y
    }


    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath!!.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }


    private fun touchUp() {
        mPath!!.lineTo(mX, mY)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }
}
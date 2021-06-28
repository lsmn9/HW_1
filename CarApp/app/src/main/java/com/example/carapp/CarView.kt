package com.example.carapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CarView : View {
    private val WHEEL_WIDTH = 10
    private val WHEEL_LENGTH = 30

    private var carColor = 0
    private var lightsColor = 0
    private var wheelsColor = 0

    private val light1 = Rect()
    private val light2 = Rect()
    private val wheel1 = Rect()
    private val wheel2 = Rect()
    private val wheel3 = Rect()
    private val wheel4 = Rect()
    private val carBody = Rect()

    private var carPaint: Paint? = null
    private var wheelsPaint: Paint? = null
    private var lightsPaint: Paint? = null



    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(context, attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttr(context, attrs)
        init()
    }


    private fun initAttr(context: Context, attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CarView, 0, 0)
        carColor = typedArray.getColor(R.styleable.CarView_car_body_color, Color.RED)
        lightsColor = typedArray.getColor(R.styleable.CarView_car_lights_color, Color.YELLOW)
        wheelsColor = typedArray.getColor(R.styleable.CarView_car_wheels_color, Color.BLACK)
        typedArray.recycle()
    }

    private fun init() {
        carPaint = Paint()
        carPaint!!.color = carColor
        carPaint!!.style = Paint.Style.FILL

        lightsPaint = Paint()
        lightsPaint!!.color = lightsColor
        lightsPaint!!.style = Paint.Style.FILL

        wheelsPaint = Paint()
        wheelsPaint!!.color = wheelsColor
        wheelsPaint!!.style = Paint.Style.FILL
    }

    fun readyToStart() {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        carBody[0, 0, w] = h
        light1[w, h-120, w-30] = h-100
        light2[w,120,w-30] = 100
        wheel1[w, h, w-WHEEL_LENGTH] = h-WHEEL_WIDTH
        wheel2[w,0,w-WHEEL_LENGTH] = WHEEL_WIDTH
        wheel3[0, h, WHEEL_LENGTH] = h-WHEEL_WIDTH
        wheel4[0,0, WHEEL_LENGTH] = WHEEL_WIDTH
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(carBody, carPaint!! )
        canvas.drawRect(light1, lightsPaint!!)
        canvas.drawRect(light2, lightsPaint!!)
        canvas.drawRect(wheel1, wheelsPaint!!)
        canvas.drawRect(wheel2, wheelsPaint!!)
        canvas.drawRect(wheel3, wheelsPaint!!)
        canvas.drawRect(wheel4, wheelsPaint!!)

    }
}
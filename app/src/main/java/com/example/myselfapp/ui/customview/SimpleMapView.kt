package com.example.myselfapp.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myselfapp.data.model.LocationPoint

class SimpleMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var locationName: String = ""

    private val mapPaint = Paint().apply {
        color = Color.parseColor("#ADD8E6")
        style = Paint.Style.FILL
    }

    private val landPaint = Paint().apply {
        color = Color.parseColor("#90EE90")
        style = Paint.Style.FILL
    }

    private val markerPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 28f
        textAlign = Paint.Align.CENTER
    }

    fun setLocation(location: LocationPoint, name: String) {
        this.latitude = location.latitude
        this.longitude = location.longitude
        this.locationName = name
        invalidate() // Paksa View untuk menggambar ulang
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        canvas.drawRect(0f, 0f, width, height, mapPaint)
        canvas.drawRect(width * 0.1f, height * 0.1f, width * 0.9f, height * 0.9f, landPaint)

        val normalizedLat = (latitude + 90) / 180 // Skala dari 0 ke 1
        val normalizedLng = (longitude + 180) / 360 // Skala dari 0 ke 1

        val markerX = normalizedLng * width
        val markerY = (1 - normalizedLat) * height // Invers Y karena latitude meningkat ke atas, piksel ke bawah


        canvas.drawCircle(markerX.toFloat(), markerY.toFloat(), 20f, markerPaint) // Lingkaran merah sebagai marker

        if (locationName.isNotBlank()) {
            canvas.drawText(locationName, markerX.toFloat(), (markerY + 40f).toFloat(), textPaint)
        }
    }
}
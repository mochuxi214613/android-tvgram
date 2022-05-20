package com.keygenqt.tvgram.extensions

import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * Adaptive resize image
 */
fun Bitmap.resizeAdaptive(size: Int): Bitmap {
    return try {
        if (height >= width) {
            val aspectRatio = width.toDouble() / height.toDouble()
            val targetWidth = (size * aspectRatio).toInt()
            Bitmap.createScaledBitmap(this, targetWidth, size, false)
        } else {
            val aspectRatio = height.toDouble() / width.toDouble()
            val targetHeight = (size * aspectRatio).toInt()
            Bitmap.createScaledBitmap(this, size, targetHeight, false)
        }
    } catch (e: Exception) {
        this
    }
}

/**
 * Merge bitmap with background
 */
fun Bitmap.bitmapMerge(down: Bitmap): Bitmap {

    val maxW = if (this.width > down.width) this.width else down.width
    val maxH = if (this.height > down.height) this.height else down.height

    val overlay = Bitmap.createBitmap(maxW, maxH, down.config)
    val canvas = Canvas(overlay)

    val downPosW = if (down.width < this.width) {
        (this.width - down.width) / 2
    } else {
        0
    }

    val downPosH = if (down.height < this.height) {
        (this.height - down.height) / 2
    } else {
        0
    }

    val upPosW = if (this.width < down.width) {
        (down.width - this.width) / 2
    } else {
        0
    }

    val upPosH = if (this.height < down.height) {
        (down.height - this.height) / 2
    } else {
        0
    }

    canvas.drawBitmap(down, downPosW.toFloat(), downPosH.toFloat(), null)
    canvas.drawBitmap(this, upPosW.toFloat(), upPosH.toFloat(), null)

    return overlay
}
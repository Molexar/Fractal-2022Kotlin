package ru.smak.video

import ru.smak.graphics.FractalPainter
import ru.smak.graphics.Plane
import ru.smak.graphics.testFunc
import ru.smak.math.Mandelbrot
import java.awt.image.BufferedImage

class Shot(plane: Plane) {

    val plane: Plane;
    val image: BufferedImage;

    init {
        this.plane = plane;
        image = getImageFromPlane();
    }

    private fun getImageFromPlane(): BufferedImage {

        val fp = FractalPainter(Mandelbrot()::isInSet, ::testFunc, plane)

        val img = BufferedImage(
           100,
           100,
            BufferedImage.TYPE_INT_RGB
        )
        fp.paint(img.graphics)
        return img
    }


}
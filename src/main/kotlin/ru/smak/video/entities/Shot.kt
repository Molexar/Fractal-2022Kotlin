package ru.smak.video.entities

import ru.smak.graphics.FractalPainter
import ru.smak.graphics.Plane
import ru.smak.graphics.testFunc
import ru.smak.gui.MainWindow
import ru.smak.math.Mandelbrot
import ru.smak.video.objects.VideoSettings
import java.awt.image.BufferedImage

class Shot(plane: Plane) {

    val plane: Plane;
    val image: BufferedImage;
    val thumbnailImage: BufferedImage;

    init {
        this.plane = plane;
        image = getImageFromPlane();
        thumbnailImage = getImageFromPlane(100,100);
    }

    // todo: async!
    private fun getImageFromPlane(width: Int = VideoSettings.width, height: Int = VideoSettings.height): BufferedImage {

        val fp = FractalPainter(Mandelbrot()::isInSet, MainWindow.colorScheme, plane)

        val img = BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_RGB
        )
        fp.paint(img.graphics)
        return img
    }

}
package ru.smak.video

import ru.smak.graphics.Plane
import java.awt.image.BufferedImage

class Shot(plane: Plane) {

    val plane: Plane;
    private var _image: BufferedImage;

    init {
        this.plane = plane;
        _image = getImageFromPlane();
    }

    private fun getImageFromPlane(): BufferedImage {
        // todo(для сани): реализовать создания битмапа по плоскости

        val img = BufferedImage(1, 1, 1); // temp

        return img;
    }

    fun getImage() = _image;

}
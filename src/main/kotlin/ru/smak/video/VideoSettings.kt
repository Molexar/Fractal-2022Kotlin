package ru.smak.video

import java.awt.image.BufferedImage

object VideoSettings {

    private val _keyShots = mutableListOf<BufferedImage>();

    fun GetKeyShotsCount() = _keyShots.size;
    fun GetKeyShots() = _keyShots.toMutableList();

    fun AddKeyShot(shot: BufferedImage) = _keyShots.add(shot);
    fun DeleteKeyShot(shot: BufferedImage) = _keyShots.remove(shot);

}
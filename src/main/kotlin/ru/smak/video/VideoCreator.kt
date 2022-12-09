package ru.smak.video

import org.jcodec.api.SequenceEncoder
import org.jcodec.common.io.NIOUtils
import org.jcodec.common.model.ColorSpace
import org.jcodec.common.model.Picture
import org.jcodec.common.model.Rational
import org.jcodec.scale.AWTUtil
import java.awt.image.BufferedImage
import java.io.File


object VideoCreator { // класс создания видео

    @JvmName("createVideo1") // todo: understand how to overload fun with java and kotlin params
    fun createVideo(filename: String, images: List<BufferedImage>) {
        val output = File(filename);

        val enc = SequenceEncoder.createWithFps(NIOUtils.writableChannel(output),
            Rational(VideoSettings.fps, 1));


        //TODO: непонятная проблема с колор спейсами: вылазит исключение,
        // если высота или ширина не четное число, может найти другую либу?

        for (img in images)
            enc.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(img));

        enc.finish()
    }

    fun createVideo(filename: String, shots: List<Shot>) {
        val z = listOf<BufferedImage>();
     createVideo(filename, shots.map { it.image }) ;
    }

}
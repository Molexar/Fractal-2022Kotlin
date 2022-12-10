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

    @JvmName("createVideoByImages")
    fun createVideo(filename: String, images: List<BufferedImage>) {
        val output = File(filename);

        val enc = SequenceEncoder.createWithFps(NIOUtils.writableChannel(output),
            Rational(VideoSettings.fps, 1));

        for (img in images)
            enc.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(img));

        enc.finish()
    }

    @JvmName("createVideoByShots")
    fun createVideo(filename: String, shots: List<Shot>) {
     createVideo(filename, shots.map { it.image }) ;
    }

}
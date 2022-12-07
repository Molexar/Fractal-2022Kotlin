package ru.smak

import ru.smak.gui.MainWindow
import ru.smak.video.VideoCreator
import java.awt.image.BufferedImage

fun main(args: Array<String>) {
    val w = MainWindow().apply { isVisible = true };
    //MainWindow().isVisible = true


    val screen = w.getScreenShot();

    VideoCreator.createVideo(
        "C:\\Users\\danil\\Documents\\video.mp4",
        listOf(
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
            screen,
        )
    );

}


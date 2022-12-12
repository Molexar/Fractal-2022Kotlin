package ru.smak.video.dto

/*
* DTO окна создания видео.
* */
data class CreateVideoDTO
(
    // Высота выходного видео в пикселях.
    val height: Int,

    // Ширина выходного видео в пикселях.
    val width: Int,

    // Длина видео в секундах.
    val duration: Int,

    // Количество FPS.
    val fps: Int
)

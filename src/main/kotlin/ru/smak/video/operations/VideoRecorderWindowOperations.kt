package ru.smak.video.operations

import ru.smak.graphics.Plane
import ru.smak.video.Shot
import ru.smak.video.VideoSettings
import ru.smak.video.dto.CreateVideoDTO
import ru.smak.video.services.VideoRecorderWindowService
import ru.smak.video.ui.windows.ShotsListWindow
import ru.smak.video.ui.windows.VideoWindow
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class VideoRecorderWindowOperations(
    private val _mainWindow: VideoWindow,
    private val _shotsListWindow: ShotsListWindow,
    private val _service: VideoRecorderWindowService
) : MouseAdapter()
{
    override fun mouseClicked(e: MouseEvent?) {
        if (e == null)
        {
            return
        }

        when (e.component.name)
        {
            "AddShotButton" -> onAddShotButtonClick()
            "ClearShotsButton" -> onClearShotsButtonClick()
            "ShowShotsButton" -> onShowShotsButtonClick()
            "CreateVideoButton" -> onCreateVideoButtonClick()
            else -> {
                throw IllegalArgumentException("Cannot recognize button name")
            }
        }
        refreshShotsCount()
    }

    private fun onShowShotsButtonClick() {
        _shotsListWindow.isVisible = true
    }

    private fun onClearShotsButtonClick() {
        VideoSettings.dispose();
        _shotsListWindow.apply {
            dispose()
            repaint()
            isVisible = true
        }
    }

    private fun onAddShotButtonClick() {
        val plane = _mainWindow.plane
        val newShot = Shot(
            Plane(plane.xMin, plane.xMax, plane.yMin, plane.yMax)
                .apply {
                    width = 100
                    height = 100
                }
        )

        VideoSettings.addShot(newShot)
        _shotsListWindow.apply {
            updateThumbnails(_service.shotsList)
            repaint()
            isVisible = true
        }
    }

    private fun refreshShotsCount(count: Int = -1) {
        if (count != -1) _mainWindow.shotsCountLabel.text = "Shots count: ${count}";
        else _mainWindow.shotsCountLabel.text = "Shots count: ${VideoSettings.getKeyShotsCount()}";
    }

    private fun onCreateVideoButtonClick()
    {
        val videoHeight = _mainWindow.heightSpinner.value as Int
        val videoWidth = _mainWindow.widthSpinner.value as Int
        val videoFramerate = _mainWindow.fpsSpinner.value as Int
        val videoDuration = _mainWindow.durationSpinner.value as Int

        val dto = CreateVideoDTO(
            videoHeight,
            videoWidth,
            videoFramerate,
            videoDuration
        )

        _service.Execute(dto)
    }
}
package ru.smak.video

import kotlinx.coroutines.DisposableHandle
import ru.smak.graphics.Plane
import java.awt.image.BufferedImage

// класс параметров для создания видео
object VideoSettings : DisposableHandle {

    private val _statesList = mutableListOf<Plane>(); // список ключевых состояний (добавляются при нажатии на Add Shot кнопку)

    fun GetKeyShotsCount() = _statesList.size; // todo: maybe rename to GetKeyStatesCount?
    fun GetKeyShots() = _statesList.toMutableList(); // same

    fun AddState(state: Plane) = _statesList.add(state);
    fun DeleteState(state: Plane) = _statesList.remove(state);

    override fun dispose() {
        _statesList.clear();

    }

    // для последующего отображения на панельке списка ключевых кадров
    // по нажатии на кнопку Show Shots
    // todo: fun GetKeyFrames(_statesList) => list<BufferedImages>

    // todo (для сани):  fun GetImagesByKeyStates(_statesList) => list<BufferedImages>

}
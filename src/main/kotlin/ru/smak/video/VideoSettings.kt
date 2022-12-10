package ru.smak.video

import kotlinx.coroutines.DisposableHandle

// класс параметров для создания видео
object VideoSettings : DisposableHandle {

    private val _shotsList = mutableListOf<Shot>(); // список ключевых снимков (добавляются при нажатии на Add Shot кнопку)

    var fps = 25;
    var width = 800;
    var height = 600;
    var duration = 5;
    fun getKeyShotsCount() = _shotsList.size;
    fun getKeyShots() = _shotsList.toMutableList();

    fun addShot(shot: Shot) = _shotsList.add(shot);
    fun deleteShot(shot: Shot) = _shotsList.remove(shot);

    override fun dispose() {
        _shotsList.clear();
    }

    fun calculateAllShots(): List<Shot>
    {
        TODO("Сане; создать список всех кадров, для создания видео по ним")
    }



}
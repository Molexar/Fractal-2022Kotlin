package ru.smak.video.windows

import kotlinx.coroutines.DisposableHandle
import ru.smak.graphics.Plane
import ru.smak.main
import ru.smak.video.Shot
import ru.smak.video.ShotThumbnail
import ru.smak.video.VideoSettings
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*


class ShotsListWindow : JFrame(), DisposableHandle {


    private val _minSize = Dimension(355, 420);
    private var _thumbnails = mutableListOf<ShotThumbnail>();

    private val _mainPanel = JPanel().apply { background = Color.RED; };
    private val _thumbsPanel = JPanel().apply { background = Color.GREEN; };
    private val _controlPanel = JPanel().apply { background = Color.BLUE };

    private val _deleteBtn = JButton("Delete");

    companion object {
        val SHRINK = GroupLayout.PREFERRED_SIZE;
        val GROW = GroupLayout.DEFAULT_SIZE;
    }


    init {
        size = _minSize;
        isResizable = false;

        setupLayout();
        setupScrollbar();
        setupEventListeners();

        setLocationRelativeTo(null); //todo: relative to VideoWindow
    }

    private fun setupEventListeners() {


        _deleteBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        clearThumbs();
                        for (selected in _thumbnails.filter { it.isSelected }) {
                            VideoSettings.deleteShot(selected.shot);
                            //_thumbsPanel.remove(selected);
                            _thumbnails.remove(selected);
                        }
                        setupComponents();
                        // TODO

                        // setThumbnails(_thumbnails.takeWhile { !it.isSelected }.toMutableList())
                        // todo: repaint videoWindow
                        _thumbsPanel.repaint();
                    }
                }
            }
        });
    }

    private fun setupScrollbar() { // todo


        val jsp = JScrollPane(_thumbsPanel);

        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        _mainPanel.add(jsp);
        add(_mainPanel);
    }

    private fun setupLayout() {

        _mainPanel.layout = GroupLayout(_mainPanel).apply {
            setVerticalGroup(
                createSequentialGroup()
                    .addComponent(_thumbsPanel, 340, 340, 340)
                    .addComponent(_controlPanel, SHRINK, SHRINK, SHRINK)
            )

            setHorizontalGroup(
                createParallelGroup()
                    .addComponent(_thumbsPanel, 355, 355, 355)
                    .addComponent(_controlPanel, GROW, GROW, GROW)
            )
        }

        _thumbsPanel.layout = FlowLayout(0, 10, 10);
        _controlPanel.apply {
            layout = FlowLayout(0);
            add(_deleteBtn)
        };
    }

    @JvmName("setThumbnails1")
    fun setThumbnails(shots: MutableList<Shot>) {
        clearThumbs();
        _thumbnails = shots.map { ShotThumbnail(it) }.toMutableList();
        setupComponents();
    }

    @JvmName("setThumbnails2")
    fun setThumbnails(thumbs: MutableList<ShotThumbnail>) {
        clearThumbs();
        _thumbnails = thumbs;
        setupComponents();
    }

    private fun setupComponents() {
        for (thumb in _thumbnails)
            _thumbsPanel.add(thumb);
    }

    private fun clearThumbs() {
        for (thumb in _thumbnails)
            _thumbsPanel.remove(thumb);
    }

    override fun dispose() { // todo:
        clearThumbs();
        _thumbnails.clear();
    }

}
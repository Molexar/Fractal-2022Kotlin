package ru.smak.video.windows

import ru.smak.graphics.Plane
import ru.smak.video.Shot
import ru.smak.video.VideoCreator
import ru.smak.video.VideoSettings
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.*


class VideoWindow() : JFrame() {

    private val _minSize = Dimension(400, 300);
    private val _mainPanel: JPanel = JPanel();

    var plane = Plane();

    private var _shotsListWindow = ShotsListWindow().apply { isVisible = false; };
    private val _videoSettings = VideoSettings;

    // labels
    private val _shotsCountLabel = JLabel("Shots count: ${VideoSettings.getKeyShotsCount()}")

    // spinners
    private val _widthLabel = JLabel("Width");
    private val _widthSpinner = JSpinner();

    private val _heightLabel = JLabel("Height");
    private val _heightSpinner = JSpinner();

    private val _fpsLabel = JLabel("FPS");
    private val _fpsSpinner = JSpinner();

    private val _durationLabel = JLabel("Duration");
    private val _durationSpinner = JSpinner();

    // buttons
    private val _addShotBtn = JButton("Add Shot");
    private val _clearBtn = JButton("Clear");
    private val _createBtn = JButton("Create");
    private val _showShotsBtn = JButton("Show Shots");

    // static
    companion object {
        val SHRINK = GroupLayout.PREFERRED_SIZE
        val GROW = GroupLayout.DEFAULT_SIZE
    }

    init {

        setupLayout();
        setupSpinners();
        setupEventListeners();

        //todo: relative to MainWindow

        size = _minSize;
        isAlwaysOnTop = true;
        isVisible = true;
    }


    private fun setupLayout() {

        var gl = GroupLayout(this.contentPane)
        this.layout = gl;

        gl.setVerticalGroup(
            gl.createSequentialGroup()
                .addComponent(
                    _mainPanel,
                    GROW,
                    GROW,
                    GROW
                )
        );

        gl.setHorizontalGroup(
            gl.createParallelGroup()
                .addComponent(
                    _mainPanel,
                    GROW,
                    GROW,
                    GROW
                )
        );

        gl = GroupLayout(_mainPanel);
        _mainPanel.layout = gl;

        gl.setVerticalGroup(
            gl.createParallelGroup()
                .addGap(10)
                .addGroup(
                    gl.createSequentialGroup() // resolution spinners group
                        .addGap(10)
                        .addComponent(_widthLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_widthSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_heightLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_heightSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_addShotBtn, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_createBtn, 20, SHRINK, SHRINK)
                        .addGap(10, 10, Int.MAX_VALUE)
                        .addComponent(_shotsCountLabel, 20, SHRINK, SHRINK)
                )
                .addGap(30)
                .addGroup(
                    gl.createSequentialGroup()
                )
                .addGroup(
                    gl.createSequentialGroup() // video settings group
                        .addGap(10)
                        .addComponent(_fpsLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_fpsSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_durationLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_durationSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_clearBtn, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_showShotsBtn, 20, SHRINK, SHRINK)

                )


        );

        gl.setHorizontalGroup(
            gl.createSequentialGroup()
                .addGap(10)
                .addGroup(
                    gl.createParallelGroup() // resolution spinners group
                        .addGap(10)
                        .addComponent(_shotsCountLabel, 20, SHRINK, SHRINK)
                        .addComponent(_widthLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_widthSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_heightLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_heightSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_addShotBtn, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_createBtn, 20, SHRINK, SHRINK)
                )
                .addGap(30)
                .addGroup(
                    gl.createParallelGroup() // video settings group
                        .addGap(10)
                        .addComponent(_fpsLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_fpsSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_durationLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(_durationSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_clearBtn, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(_showShotsBtn, 20, SHRINK, SHRINK)
                )
        );
    }

    private fun setupSpinners() {

        val removeSpinnerUpDownArrows = fun(spinner: JSpinner) {
            for (component in spinner.components)
                if (component.name != null && component.name.endsWith("Button"))
                    spinner.remove(component)
        }

        val widthModel = SpinnerNumberModel(800, 100, 1920, 100);
        _widthSpinner.model = widthModel;
        removeSpinnerUpDownArrows(_widthSpinner);

        val heightModel = SpinnerNumberModel(600, 100, 1920, 100);
        _heightSpinner.model = heightModel;
        removeSpinnerUpDownArrows(_heightSpinner);


        val fpsModel = SpinnerNumberModel(30, 24, 120, 5);
        _fpsSpinner.model = fpsModel;
        removeSpinnerUpDownArrows(_fpsSpinner);


        val durationModel = SpinnerNumberModel(5, 1, 60, 1);
        _durationSpinner.model = durationModel;
        removeSpinnerUpDownArrows(_durationSpinner);


    }

    private fun setupEventListeners() {

        _addShotBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        VideoSettings.addShot(
                            Shot(Plane(plane.xMin, plane.xMax, plane.yMin, plane.yMax)
                                .apply {
                                    width = 100;
                                    height = 100;
                                })
                        );
                        RefreshShotsCount();

                        _shotsListWindow.setThumbnails(_videoSettings.getKeyShots());
                        _shotsListWindow.repaint();
                        _shotsListWindow.isVisible = true;

                        // todo: show dialog only if it's needed?

                    }
                }
            }
        });

        _clearBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        VideoSettings.dispose();
                        _shotsListWindow.dispose();
                        _shotsListWindow.repaint();
                        _shotsListWindow.isVisible = true;
                        RefreshShotsCount();
                    }
                }
            }
        })

        _createBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        VideoSettings.width = _widthSpinner.value as Int;
                        VideoSettings.height = _heightSpinner.value as Int;
                        VideoSettings.fps = _fpsSpinner.value as Int;
                        VideoSettings.duration = _durationSpinner.value as Int;

                        //val filter: FileFilter = FileFilter {  }("MP3 File", "mp3") // TODO:


                        val fileChooser = JFileChooser().apply { selectedFile = File("video.mp4") };

                        fileChooser.showSaveDialog(_mainPanel);

                        val filename = fileChooser.selectedFile.absolutePath;

                        // todo: automatic .mp4 fill
                        VideoCreator.createVideo(filename, VideoSettings.calculateAllShots())
                    }
                }
            }
        });

        _showShotsBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        _shotsListWindow.isVisible = true;
                    };
                }
            }

        });

    }

    private fun RefreshShotsCount() {
        _shotsCountLabel.text = "Shots count: ${VideoSettings.getKeyShotsCount()}";
    }

}
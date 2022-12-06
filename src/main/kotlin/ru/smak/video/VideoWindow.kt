package ru.smak.video

import ru.smak.graphics.Plane
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*


class VideoWindow(plane: Plane) : JFrame() {

    private val _minSize = Dimension(400, 300);
    private val _mainPanel: JPanel = JPanel();
    private val _plane = plane;

    private val _videoSettings = VideoSettings;

    // labels
    private val _shotsCountLabel = JLabel("Shots count: ${_videoSettings.GetKeyShotsCount()}")

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
                        _videoSettings.AddState(Plane(_plane.xMin, _plane.xMax, _plane.yMin, _plane.yMax));
                        RefreshShotsCount();

                    }
                }
            }
        });

        _clearBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                e?.apply {
                    if (button == 1) {
                        _videoSettings.dispose()
                        RefreshShotsCount();
                    }
                }

            }
        });

    }

    private fun RefreshShotsCount()
    {
        _shotsCountLabel.text = "Shots count: ${_videoSettings.GetKeyShotsCount()}";
    }

}
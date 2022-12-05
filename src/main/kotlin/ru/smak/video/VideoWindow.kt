package ru.smak.video

import java.awt.Color
import java.awt.Dimension
import javax.swing.*


class VideoWindow() : JFrame() {

    val minSize = Dimension(400, 300);
    val mainPanel: JPanel = JPanel().also { it.background = Color.GRAY };

    val _videoSettings = VideoSettings;

    // labels
    val shotsCountLabel = JLabel("Shots count: ${_videoSettings.GetKeyShotsCount()}")

    // spinners
    val widthLabel = JLabel("Width");
    val widthSpinner = JSpinner();

    val heightLabel = JLabel("Height");
    val heightSpinner = JSpinner();

    val fpsLabel = JLabel("FPS");
    val fpsSpinner = JSpinner();

    val durationLabel = JLabel("Duration");
    val durationSpinner = JSpinner();

    // buttons
    val addShotBtn = JButton("Add Shot");
    val clearBtn = JButton("Clear");
    val createBtn = JButton("Create");
    val showShotsBtn = JButton("Show Shots");

    // static
    companion object {
        val SHRINK = GroupLayout.PREFERRED_SIZE
        val GROW = GroupLayout.DEFAULT_SIZE
    }

    init {


        SetupLayout();
        SetupSpinners();


        size = minSize;
        isVisible = true;
    }


    private fun SetupLayout() {


        var gl = GroupLayout(this.contentPane)
        this.layout = gl;

        gl.setVerticalGroup(
            gl.createSequentialGroup()
                .addComponent(
                    mainPanel,
                    GROW,
                    GROW,
                    GROW
                )
        );

        gl.setHorizontalGroup(
            gl.createParallelGroup()
                .addComponent(
                    mainPanel,
                    GROW,
                    GROW,
                    GROW
                )
        );

        gl = GroupLayout(mainPanel);
        mainPanel.layout = gl;

        gl.setVerticalGroup(
            gl.createParallelGroup()
                .addGap(10)
                .addGroup(
                    gl.createSequentialGroup() // resolution spinners group
                        .addGap(10)
                        .addComponent(widthLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(widthSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(heightLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(heightSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(addShotBtn,20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(createBtn,20, SHRINK, SHRINK)
                        .addGap(10,10,Int.MAX_VALUE)
                        .addComponent(shotsCountLabel,20, SHRINK, SHRINK)
                )
                .addGap(30)
                .addGroup(
                    gl.createSequentialGroup()
                )
                .addGroup(
                    gl.createSequentialGroup() // video settings group
                        .addGap(10)
                        .addComponent(fpsLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(fpsSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(durationLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(durationSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(clearBtn,20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(showShotsBtn,20, SHRINK, SHRINK)

                )


        );

        gl.setHorizontalGroup(
            gl.createSequentialGroup()
                .addGap(10)
                .addGroup(
                    gl.createParallelGroup() // resolution spinners group
                        .addGap(10)
                        .addComponent(shotsCountLabel,20, SHRINK, SHRINK)
                        .addComponent(widthLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(widthSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(heightLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(heightSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(addShotBtn,20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(createBtn,20, SHRINK, SHRINK)
                )
                .addGap(30)
                .addGroup(
                    gl.createParallelGroup() // video settings group
                        .addGap(10)
                        .addComponent(fpsLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(fpsSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(durationLabel, 20, SHRINK, SHRINK)
                        .addGap(5)
                        .addComponent(durationSpinner, 20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(clearBtn,20, SHRINK, SHRINK)
                        .addGap(10)
                        .addComponent(showShotsBtn,20, SHRINK, SHRINK)
                )
        );
    }


    private fun SetupSpinners() {

        val removeSpinnerUpDownArrows = fun(spinner: JSpinner) {
            for (component in spinner.components)
                if (component.name != null && component.name.endsWith("Button"))
                    spinner.remove(component)
        }

        val widthModel = SpinnerNumberModel(800, 100, 1920, 100);
        widthSpinner.model = widthModel;
        removeSpinnerUpDownArrows(widthSpinner);

        val heightModel = SpinnerNumberModel(600, 100, 1920, 100);
        heightSpinner.model = heightModel;
        removeSpinnerUpDownArrows(heightSpinner);


        val fpsModel = SpinnerNumberModel(30, 24, 120, 5);
        fpsSpinner.model = fpsModel;
        removeSpinnerUpDownArrows(fpsSpinner);


        val durationModel = SpinnerNumberModel(5, 1, 60, 1);
        durationSpinner.model = durationModel;
        removeSpinnerUpDownArrows(durationSpinner);


    }

}
package view

import model.BaseObjectViewModel
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

const val DEFAULT_IMAGE_WIDTH = 40
const val DEFAULT_IMAGE_HEIGHT = 40
val transparentBackground = Color(0, 0, 0, 0)

open class BaseViewObject(private val viewModel: BaseObjectViewModel, private val imageWidth: Int = DEFAULT_IMAGE_WIDTH,
                          private val imageHeight: Int = DEFAULT_IMAGE_HEIGHT): ViewObject, JFrame() {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        background =  transparentBackground
        isAlwaysOnTop = true
        title = viewModel.name
        this.iconImage = ImageIcon(javaClass.getResource("/fav_icon.png")).image
        this.updateContentPane()
        this.renderLocation()
    }

    override fun display(shouldDisplay: Boolean) {
        isVisible = shouldDisplay
    }

    override fun renderSprite() {
        this.updateContentPane()
    }

    override fun renderLocation() {
        this.updateLocation()
    }

    private fun updateLocation() {
        val (xLocation, yLocation) = viewModel.currentLocation
        setBounds(xLocation.toInt() - (imageWidth / 2), yLocation.toInt() - imageHeight, imageWidth, imageHeight)
    }

    private fun getScaledGif(): JLabel {
        val image = ImageIcon(viewModel.getSprite()).image
        val scaledImage = image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_DEFAULT)
        return JLabel(ImageIcon(scaledImage))
    }

    private fun updateContentPane() {
        val newSprite = getScaledGif()
        contentPane.removeAll();
        contentPane.add(newSprite);
        contentPane.revalidate();
        contentPane.repaint();
    }

}
package view

import model.BaseObjectViewModel
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.net.URL
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

const val DEFAULT_IMAGE_WIDTH = 40
const val DEFAULT_IMAGE_HEIGHT = 40
val transparentBackground = Color(0, 0, 0, 0)

open class BaseViewObject(private val viewModel: BaseObjectViewModel, protected val imageWidth: Int = DEFAULT_IMAGE_WIDTH,
                          protected val imageHeight: Int = DEFAULT_IMAGE_HEIGHT): ViewObject {
    protected val frame = JFrame()

    init {
        this.initializeJFrame()
        this.renderSprite()
        this.renderLocation()
    }

    override fun display(shouldDisplay: Boolean) {
        frame.isVisible = shouldDisplay
    }

    override fun renderSprite() {
        val newSprite = getScaledImage(viewModel.getSprite())
        frame.contentPane.removeAll()
        frame.contentPane.add(newSprite)
        frame.contentPane.revalidate()
        frame.contentPane.repaint()
    }

    override fun renderLocation() {
        val (xLocation, yLocation) = viewModel.currentLocation
        frame.setLocation(xLocation.toInt() - (imageWidth / 2), yLocation.toInt() - imageHeight)
    }

    protected fun getScaledImage(imageURL:URL?): Component {
        if (imageURL == null ) return Box.createRigidArea(Dimension(0, imageHeight))
        val image = ImageIcon(imageURL).image
        val scaledImage = image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_DEFAULT)
        return JLabel(ImageIcon(scaledImage))
    }

    private fun initializeJFrame() {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isUndecorated = true
        frame.background =  transparentBackground
        frame.isAlwaysOnTop = true
        frame.title = viewModel.name
        frame.iconImage = ImageIcon(javaClass.getResource("/fav_icon.png")).image
        frame.layout =  BoxLayout(frame.contentPane, BoxLayout.Y_AXIS)
        frame.contentPane.preferredSize = Dimension(500, 500)
        frame.pack()
    }

}
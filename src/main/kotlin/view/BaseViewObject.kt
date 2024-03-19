package view

import controller.UserActions
import model.BaseObjectModel
import java.awt.*
import java.net.URL
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

const val DEFAULT_IMAGE_WIDTH = 50
const val DEFAULT_IMAGE_HEIGHT = 50
val transparentBackground = Color(0, 0, 0, 0)

open class BaseViewObject(private val viewModel: BaseObjectModel,
                          protected val controller: UserActions,
                          protected val imageWidth: Int = DEFAULT_IMAGE_WIDTH,
                          protected val imageHeight: Int = DEFAULT_IMAGE_HEIGHT): ViewObject {
    protected val frame = JFrame()

    init {
        this.initializeJFrame()
        this.renderSprite()
        this.renderLocation()
        this.addCustomCursor()
    }

    override fun display(shouldDisplay: Boolean) {
        frame.isVisible = shouldDisplay
    }

    override fun renderSprite() {
        val newSprite = getScaledSprite(viewModel.getSprite())
        frame.contentPane.removeAll()
        frame.contentPane.add(newSprite)
        frame.contentPane.revalidate()
        frame.contentPane.repaint()
    }

    override fun renderLocation() {
        val (xLocation, yLocation) = viewModel.currentLocation
        frame.setLocation(xLocation.toInt() - (imageWidth / 2), yLocation.toInt() - imageHeight)
    }

    protected fun getScaledSprite(imageURL:URL?): Component {
        if (imageURL == null ) return Box.createRigidArea(Dimension(0, imageHeight))
        val scaledImage = getScaledImage(imageURL)
        return JLabel(ImageIcon(scaledImage))
    }

    private fun getScaledImage(imageURL: URL, width: Int = imageWidth, height: Int= imageHeight): Image {
        val image = ImageIcon(imageURL).image
        return image.getScaledInstance(width, height, Image.SCALE_DEFAULT)
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

    private fun addCustomCursor() {
        frame.rootPane.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
            getScaledImage(javaClass.getResource("/cursor.png")!!, imageWidth / 2, imageWidth / 2),
            Point(imageWidth / 2, imageWidth / 2), "Custom cursor, a pixel art finger pointing."
        )
    }

}
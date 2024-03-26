package view

import controller.UserActions
import model.BaseObjectModel
import model.KinematicsModel
import utils.Location
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.concurrent.ExecutorService
import javax.swing.JFrame

class KinematicsView(
    viewModel: KinematicsModel,
    controller: UserActions,
    imageWidth: Int = DEFAULT_IMAGE_WIDTH,
    imageHeight: Int = DEFAULT_IMAGE_HEIGHT
) : BaseObjectView(viewModel, controller, imageWidth, imageHeight) {

    init {
        val frameDragListener = FrameDragListener(frame, viewModel)
        frame.addMouseListener(frameDragListener)
        frame.addMouseMotionListener(frameDragListener)
    }

    class FrameDragListener(private val frame: JFrame, private val viewModel: KinematicsModel) : MouseAdapter() {
        private var mouseDownCompCoords: Point? = null
        override fun mouseReleased(e: MouseEvent) {
            mouseDownCompCoords = null
            val currCoords: Point = e.locationOnScreen
            viewModel.currentLocation = Location(currCoords.x.toDouble(),
                currCoords.y.toDouble())
            viewModel.isHeld = false
            viewModel.calculateVelocity()
        }

        override fun mousePressed(e: MouseEvent) {
            mouseDownCompCoords = e.getPoint()
            viewModel.isHeld = false;
        }

        override fun mouseDragged(e: MouseEvent) {
            val currCoords: Point = e.locationOnScreen
            viewModel.currentLocation = Location(currCoords.x.toDouble(),
                currCoords.y.toDouble())
            viewModel.isHeld = true
        }
    }
}
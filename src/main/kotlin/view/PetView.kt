package view

import controller.UserActions
import model.PetModelViewModel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener


class PetView(private val petViewModel: PetModelViewModel, controller: UserActions): PetActions, BaseViewObject(petViewModel, controller) {

    init {
        this.renderSpriteWithEmote()
        this.renderLocationWithEmote()
        this.addClickListener()
    }

    override fun sayQuote(quote: String) {
        TODO("Not yet implemented")
    }

    override fun renderSpriteWithEmote() {
        val newEmote = getScaledSprite(petViewModel.getEmote())
        val newSprite = getScaledSprite(petViewModel.getSprite())
        frame.contentPane.removeAll()
        frame.contentPane.add(newEmote)
        frame.contentPane.add(newSprite)
        frame.contentPane.revalidate()
        frame.contentPane.repaint()
    }

    override fun renderLocationWithEmote() {
        val (xLocation, yLocation) = petViewModel.currentLocation
        frame.setLocation(xLocation.toInt() - (imageWidth / 2), yLocation.toInt() - 2 * imageHeight)
    }

    private fun addClickListener() {
        val mouseListener: MouseListener = object : MouseAdapter() {
            override fun mouseClicked(p0: MouseEvent?) {
                controller.pet(petViewModel.name)
                renderSpriteWithEmote()
            }
//
//            override fun mouseEntered(p0: MouseEvent?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun mouseExited(p0: MouseEvent?) {
//                TODO("Not yet implemented")
//            }
        }
        frame.contentPane.addMouseListener(mouseListener)
    }
}
package view

import model.PetModelViewModel


class PetView(private val petViewModel: PetModelViewModel): PetActions, BaseViewObject(petViewModel) {

    init {
        this.renderSpriteWithEmote()
        this.renderLocationWithEmote()
    }

    override fun sayQuote(quote: String) {
        TODO("Not yet implemented")
    }

    override fun renderSpriteWithEmote() {
        val newEmote = getScaledImage(petViewModel.getEmote())
        val newSprite = getScaledImage(petViewModel.getSprite())
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
}
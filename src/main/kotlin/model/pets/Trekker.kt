package model.pets

import utils.Emotion
import java.net.URL


class Trekker() : BasePet("Trekker") {
    override var quotes: List<String> = super.quotes + listOf("trekker specific")
    override var emotion: Emotion? = Emotion.HUNGRY

    override fun getSprite(): URL {
        return getSpriteByFolderName("trekker")
    }

    override fun getEmote(): URL? {
        return when (emotion) {
            Emotion.PLAYFUL -> javaClass.getResource("/emotes/teddy.png")
            else -> super.getEmote()
        }
    }
}
package model.pets

import model.pets.BasePet
import java.net.URL

class Remy: BasePet("Remy") {
    override fun getSprite(): URL {
        return getSpriteByFolderName("remy")
    }
}
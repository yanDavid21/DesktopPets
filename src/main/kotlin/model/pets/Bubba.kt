package model.pets

import model.pets.BasePet
import java.net.URL

class Bubba: BasePet("Bubba") {
    override fun getSprite(): URL {
        return getSpriteByFolderName("bubba")
    }
}
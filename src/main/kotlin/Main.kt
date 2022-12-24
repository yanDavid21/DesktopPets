import controller.DesktopPetController
import model.BasePet
import model.Trekker

fun main() {
    val trekker: BasePet = Trekker()
    DesktopPetController(listOf(trekker)).start()
}
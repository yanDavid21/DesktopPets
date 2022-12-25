import controller.DesktopPetController
import model.BasePet
import model.Trekker


//TODO: chasing tail
fun main() {
    val trekker: BasePet = Trekker()
    DesktopPetController(listOf(trekker)).start()
}
import controller.DesktopPetController
import model.pets.BasePet
import model.pets.Trekker


//TODO: chasing tail, pet only trekker and not the icon
// self scheduling task
//https://stackoverflow.com/questions/23226563/scheduled-executor-poll-for-result-at-fix-rate-and-exit-if-timeout-or-result-va/23231576#23231576
fun main() {
    val trek : BasePet = Trekker()
    DesktopPetController(listOf(trek)).start()
}
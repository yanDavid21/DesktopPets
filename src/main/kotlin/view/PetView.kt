package view

import PetState
import model.BaseObjectViewModel

class PetView(viewModel: BaseObjectViewModel): PetActions, BaseViewObject(viewModel) {

    override fun sayQuote(quote: String) {
        TODO("Not yet implemented")
    }
}
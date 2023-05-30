package ui.common

import androidx.lifecycle.ViewModel
import navigation.Router
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var router: Router
}
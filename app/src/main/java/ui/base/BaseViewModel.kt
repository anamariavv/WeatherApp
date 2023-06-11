package ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import navigation.Router
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var router: Router

    protected fun runSuspend(job: suspend () -> Unit) {
        viewModelScope.launch { job() }
    }
}
package ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import navigation.NavGraph
import navigation.BottomNavigator
import ui.common.component.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BaseEntryPoint() {
    val navController: NavHostController = rememberNavController()
    val titleText: String = stringResource(id = R.string.top_app_bar_title_placeholder)

    Column {
        Scaffold(
            topBar = { TopAppBar(titleText) },
            bottomBar = { BottomNavigator(navController = navController) }
        ) {
            NavGraph(navController = navController)
        }
    }
}
package ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import navigation.NavGraph
import ui.common.component.BottomNavBar
import navigation.NavigationDelegate
import ui.common.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(
    navigationDelegate: NavigationDelegate,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navController: NavHostController = rememberNavController()
    val titleText: String = stringResource(id = R.string.top_app_bar_title_placeholder)
    val bottomNavBarState by viewModel.bottomNavBarState.collectAsState()

    LaunchedEffect(navController) {
        navigationDelegate.getNavigationEvents().collect { event ->
            navController.navigate(event.destination)
        }
    }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    titleText = titleText,
                    onButtonClick = viewModel::onCityActionButtonClicked
                )
            },
            bottomBar = {
                BottomNavBar(
                    items = bottomNavBarState.items,
                    onItemClicked = bottomNavBarState.onItemClicked,
                    selectedItem = bottomNavBarState.selectedItem
                )
            }
        ) {
            Column(modifier = Modifier.padding(paddingValues = it)) {
                NavGraph(navController = navController)
            }
        }
    }
}
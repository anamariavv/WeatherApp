package ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import navigation.NavGraph
import navigation.BottomNavigator
import ui.theme.Sky

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BaseEntryPoint() {
    val navController: NavHostController = rememberNavController()

    Column {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar (
                    title = { Text("Rijeka", maxLines = 1, fontSize = 30.sp) },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(modifier = Modifier.size(30.dp), imageVector = Icons.Rounded.Add, contentDescription = "Change location")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Sky)
                )
            },
            bottomBar = { BottomNavigator(navController = navController) }
        ) {
            NavGraph(navController = navController)
        }
    }
}


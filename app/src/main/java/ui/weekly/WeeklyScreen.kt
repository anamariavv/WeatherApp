package ui.weekly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Blue
import ui.theme.Shapes
import ui.theme.Typography
import ui.theme.White

@Composable
fun WeeklyScreen(viewModel: WeeklyViewModel = hiltViewModel()) {

	val dialogState by viewModel.dialogState.collectAsState()
	val weeklyForecastState by viewModel.weeklyForecastState.collectAsState()

	Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
		Text("5 day forecast", style = Typography.headlineSmall, modifier = Modifier.padding(20.dp).align(CenterHorizontally))

		LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
			items(weeklyForecastState.items) {
				Surface(shape = Shapes.medium, color = Blue) {
					Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
						Text(it.day, style = Typography.headlineMedium, modifier = Modifier.align(Alignment.CenterStart), color = White)
						Surface(modifier = Modifier.align(Alignment.Center).size(100.dp), shape = CircleShape) {
							Icon(painterResource(id = it.weatherIconId), contentDescription = "Sunny", tint = Color.Unspecified)
						}
						Text(it.temperature, style = Typography.headlineMedium, modifier = Modifier.align(Alignment.CenterEnd), color = White)
					}
				}
			}
		}
	}
}
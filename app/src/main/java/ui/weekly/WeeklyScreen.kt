package ui.weekly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import ui.theme.Typography
import ui.theme.White

@Composable
fun WeeklyScreen(viewModel: WeeklyViewModel = hiltViewModel()) {

	val dialogState by viewModel.dialogState.collectAsState()
	val weeklyForecastState by viewModel.weeklyForecastState.collectAsState()

	Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
		Text("5 day forecast", style = Typography.headlineSmall, modifier = Modifier.padding(20.dp))

		/*LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
			items(weeklyForecastState.items) {
				Card(modifier = Modifier.fillMaxWidth()) {
					Box {
						Text(it.day, modifier = Modifier.align(Alignment.CenterStart))
						Icon(painterResource(id = it.weatherIconId), contentDescription = "Sunny", modifier = Modifier.align(Alignment.Center), tint = Color.Unspecified)
						Text(it.temperature, style = Typography.labelLarge, modifier = Modifier.align(Alignment.CenterEnd), color = White)
					}
				}
			}
		}*/

		Column() {
			for(i in 1..5) {
				Card(modifier = Modifier.fillMaxWidth()) {
					Box {
						Text("Mon", style = Typography.headlineSmall, modifier = Modifier.align(Alignment.CenterStart))
						Icon(painterResource(id = R.drawable.partially_snowy), contentDescription = "Sunny", modifier = Modifier.align(Alignment.Center), tint = Color.Unspecified)
						Text(String.format("25° / 30°"), style = Typography.headlineSmall, modifier = Modifier.align(Alignment.CenterEnd), color = White)
					}
				}
			}
		}

	}

}
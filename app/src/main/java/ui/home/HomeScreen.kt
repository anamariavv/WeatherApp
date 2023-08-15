package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import model.city.City
import ui.theme.Blue
import ui.theme.Shapes
import ui.theme.Typography
import utils.empty


@Composable fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

	val dropdownState by viewModel.dropdownState.collectAsState()
	val twelveHourForecastState by viewModel.hourlyForecastState.collectAsState()
	val currentConditionsState by viewModel.currentConditionsState.collectAsState()

	Column(Modifier.fillMaxSize()) {
		favouritesDropdown(
			titleText = dropdownState.selectedValue?.localizedName ?: String.empty(),
			isExpanded = dropdownState.isExpanded,
			onDismiss = viewModel::closeDropdown,
			itemList = dropdownState.list,
			onItemSelected = viewModel::onItemSelected,
			onIconClicked = viewModel::toggleDropdownExpanded
		)

		Surface(modifier = Modifier.fillMaxWidth(0.7f).weight(0.7f).padding(10.dp).align(CenterHorizontally), shape = Shapes.medium) {
			Box {
				Icon(painterResource(id = currentConditionsState.weatherIconId), contentDescription = "Weather icon", tint = Color.Unspecified, modifier = Modifier.align(TopCenter))

				Column(modifier = Modifier.align(Center).padding(top = 30.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
					Text(currentConditionsState.temperature, style = Typography.headlineLarge)
					Text(currentConditionsState.realFeelTemperature, style = Typography.titleLarge)
					Text(currentConditionsState.weatherText, style = Typography.titleMedium)
				}
			}
		}

		Row(Modifier.fillMaxWidth().weight(0.15f).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
			if (currentConditionsState.hasWind) {
				Card(modifier = Modifier.weight(1f), shape = Shapes.medium, backgroundColor = Blue) {
					Column(modifier = Modifier.padding(10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
						Icon(painterResource(id = R.drawable.wind), contentDescription = "wind")
						Text(currentConditionsState.windSpeed!!, style = Typography.labelMedium)
						Text("Wind", style = Typography.labelSmall)
					}
				}
			}

			if (currentConditionsState.hasHumidity) {
				Card(modifier = Modifier.weight(1f), shape = Shapes.medium, backgroundColor = Blue) {
					Column(modifier = Modifier.padding(10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
						Icon(painterResource(id = R.drawable.raindrop), contentDescription = "Humidity")
						Text(currentConditionsState.humidity!!, style = Typography.labelMedium)
						Text("Humidity", style = Typography.labelSmall)
					}
				}
			}

			if (currentConditionsState.hasUvIndex) {
				Card(modifier = Modifier.weight(1f), shape = Shapes.medium, backgroundColor = Blue) {
					Column(modifier = Modifier.padding(10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
						Icon(painterResource(id = R.drawable.sunny), contentDescription = "UV intensity")
						Text(currentConditionsState.uvIndex!!, style = Typography.labelMedium)
						Text("UV", style = Typography.labelSmall)
					}
				}
			}
		}

		Surface(modifier = Modifier.fillMaxWidth().weight(0.15f).padding(10.dp).align(CenterHorizontally), shape = Shapes.medium, color = Blue) {
			Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start) {
				Row {
					Icon(painterResource(id = R.drawable.time), contentDescription = "12 hour forecast")
					Text("12 hour forecast", style = Typography.titleMedium)
				}
				LazyRow {
					items(twelveHourForecastState.hours) {
						Column(modifier = Modifier.padding(10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
							Text(it.temperature, style = Typography.labelSmall)
							Icon(painterResource(id = it.weatherIconId), contentDescription = "Sunny")
							Text(it.iconPhrase, style = Typography.labelSmall)
						}
					}
				}
			}
		}
	}
}

@Composable
fun favouritesDropdown(
	titleText: String,
	isExpanded: Boolean,
	onDismiss: () -> Unit,
	itemList: List<City>,
	onItemSelected: (City, Int) -> Unit,
	onIconClicked: (Boolean) -> Unit
) {
	Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
		Box {
			Text(modifier = Modifier.align(Center), text = titleText, style = Typography.headlineSmall)

			DropdownMenu(expanded = isExpanded, onDismissRequest = onDismiss) {
				itemList.forEachIndexed { index, item ->
					DropdownMenuItem(onClick = { onItemSelected(item, index) }) {
						Text(text = item.localizedName)
					}
				}
			}
		}

		IconButton(onClick = { onIconClicked(isExpanded) }) {
			Icon(Icons.Filled.KeyboardArrowDown, "Toggle dropdown")
		}
	}
}
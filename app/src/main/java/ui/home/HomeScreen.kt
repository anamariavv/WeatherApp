package ui.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import model.city.City
import ui.base.baseScreen
import ui.common.component.dialog
import ui.home.model.DropdownState
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast
import ui.theme.Blue
import ui.theme.Shapes
import ui.theme.Typography
import ui.theme.White
import utils.empty

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

	val dropdownState by viewModel.dropdownState.collectAsState()
	val twelveHourForecastState by viewModel.hourlyForecastState.collectAsState()
	val currentConditionsState by viewModel.currentConditionsState.collectAsState()
	val dialogState by viewModel.dialogState.collectAsState()
	val screenState by viewModel.screenState.collectAsState()
	val context = LocalContext.current

	/*val launcherNotifications = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.RequestPermission(),
		onResult = viewModel::onNotificationPermissionResult
	)*/

	val launcherLocation = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.RequestPermission(),
		onResult = viewModel::onLocationPermissionRequestResult
	)

	/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
			launcherNotifications.launch(Manifest.permission.POST_NOTIFICATIONS)
		}
	}*/

	baseScreen(
		state = screenState,
		titleId = null,
		content = {
			HomeScreenContent(
				dropdownState,
				twelveHourForecastState,
				currentConditionsState,
				viewModel::closeDropdown,
				viewModel::onItemSelected,
				viewModel::toggleDropdownExpanded,
				viewModel::navigateToWeeklyScreen,
			)
		}
	)

	if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && dropdownState.list.isEmpty()) {
		AlertDialog(
			onDismissRequest = viewModel::dismissDialog,
			properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
			title = { Text(text = "Permission Needed") },
			text = {
				Text(
					"Permission is needed to get forecast data based on location" + " Would you like to grant location permission?"
				)
			},
			confirmButton = {
				Button(onClick = {
					launcherLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
				}) {
					Text("Yes")
				}
			},
			dismissButton = {
				Button(onClick = viewModel::dismissDialog) {
					Text("No")
				}
			}
		)

	}

	dialog(dialogState, viewModel::dismissDialog)
}

@Composable
fun HomeScreenContent(
	dropdownState: DropdownState,
	twelveHourForecastState: UiHourlyForecast,
	currentConditionsState: UiCurrentConditions,
	closeDropdown: () -> Unit,
	onItemSelected: (City, Int) -> Unit,
	toggleDropdownExpanded: (Boolean) -> Unit,
	navigateToWeeklyScreen: () -> Unit
) {

	favouritesDropdown(
		titleText = dropdownState.selectedValue?.localizedName ?: String.empty(),
		isExpanded = dropdownState.isExpanded,
		onDismiss = closeDropdown,
		itemList = dropdownState.list,
		onItemSelected = onItemSelected,
		onIconClicked = toggleDropdownExpanded
	)

	Column {
		currentConditionSummary(currentConditionsState)

		currentConditionDetails(currentConditionsState)

		twelveHourForecastSummary(twelveHourForecastState)

		Button(onClick = navigateToWeeklyScreen, modifier = Modifier.padding(10.dp).align(CenterHorizontally)) {
			Text(text = "5 day forecast")
			Icon(Icons.Filled.KeyboardArrowRight, "View 5 day forecast")
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

@Composable
fun ColumnScope.currentConditionSummary(currentConditionsState: UiCurrentConditions) {
	Surface(modifier = Modifier.fillMaxWidth(0.7f).weight(0.5f).padding(10.dp).align(CenterHorizontally), shape = Shapes.medium) {
		Box {
			Icon(
				painterResource(id = currentConditionsState.weatherIconId),
				contentDescription = "Weather icon",
				tint = Color.Unspecified,
				modifier = Modifier.align(TopCenter)
			)

			Column(modifier = Modifier.align(Center).padding(top = 30.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
				Text(currentConditionsState.temperature, style = Typography.headlineLarge)
				Text(currentConditionsState.realFeelTemperature, style = Typography.labelLarge)
				Text(currentConditionsState.weatherText, style = Typography.titleMedium)
			}
		}
	}
}

@Composable
fun ColumnScope.currentConditionDetails(currentConditionsState: UiCurrentConditions) {
	Row(Modifier.fillMaxWidth().weight(0.10f).padding(horizontal = 10.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
		if (currentConditionsState.hasWind) {
			CurrentConditionItem(
				labelText = "Wind",
				valueText = currentConditionsState.windSpeed!!,
				iconId = R.drawable.wind,
				iconContentDescription = "Wind"
			)
		}

		if (currentConditionsState.hasHumidity) {
			CurrentConditionItem(
				labelText = "Humidity",
				valueText = currentConditionsState.humidity!!,
				iconId = R.drawable.raindrop,
				iconContentDescription = "Humidity"
			)
		}

		if (currentConditionsState.hasUvIndex) {
			CurrentConditionItem(
				labelText = "UV",
				valueText = currentConditionsState.uvIndex!!,
				iconId = R.drawable.sunny,
				iconContentDescription = "UV index"
			)
		}
	}
}

@Composable
fun RowScope.CurrentConditionItem(labelText: String, valueText: String, iconId: Int, iconContentDescription: String) {
	Card(modifier = Modifier.weight(1f), shape = Shapes.medium, backgroundColor = Blue) {
		Column(modifier = Modifier.padding(10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
			Icon(painterResource(id = iconId), contentDescription = iconContentDescription, tint = White)
			Text(valueText, style = Typography.labelMedium, color = White)
			Text(labelText, style = Typography.labelSmall, color = White)
		}
	}
}

@Composable
fun ColumnScope.twelveHourForecastSummary(twelveHourForecastState: UiHourlyForecast) {
	Surface(modifier = Modifier.fillMaxWidth().weight(0.20f).padding(10.dp).align(CenterHorizontally), shape = Shapes.medium) {
		Column(horizontalAlignment = Alignment.Start) {
			Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
				Icon(painterResource(id = R.drawable.time), contentDescription = "12 hour forecast")
				Text("12 hour forecast", style = Typography.titleLarge)
			}
			LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
				items(twelveHourForecastState.hours) {
					hourlyForecastItem(
						temperatureText = it.temperature,
						timeText = it.time,
						iconId = it.weatherIconId,
						modifier = Modifier.weight(1f).background(Blue, Shapes.medium)
					)
				}
			}
		}
	}
}

@Composable
fun hourlyForecastItem(temperatureText: String, timeText: String, iconId: Int, modifier: Modifier) {
	Card {
		Box(modifier = modifier) {
			Text(temperatureText, style = Typography.labelLarge, modifier = Modifier.align(TopCenter), color = White)
			Icon(painterResource(id = iconId), contentDescription = "Sunny", modifier = Modifier.align(Center), tint = Color.Unspecified)
			Text(timeText, style = Typography.labelLarge, modifier = Modifier.align(BottomCenter), color = White)
		}
	}
}

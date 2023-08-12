package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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

	Column(Modifier.fillMaxWidth()) {
		favouritesDropdown(titleText = dropdownState.selectedValue?.localizedName ?: String.empty(),
		                   isExpanded = dropdownState.isExpanded,
		                   onDismiss = viewModel::closeDropdown,
		                   itemList = dropdownState.list,
		                   onItemSelected = viewModel::onItemSelected,
		                   onIconClicked = viewModel::toggleDropdownExpanded)

		Surface(modifier = Modifier.fillMaxWidth().padding(10.dp).align(CenterHorizontally), shape = Shapes.medium, color = Blue) {			//current conditions most important + daily min/max
			Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
				Box(modifier = Modifier.padding(10.dp)) {
					Icon(painterResource(id = R.drawable.partially_cloudy), contentDescription = "Weather icon", tint = Color.Unspecified, modifier = Modifier.size(200.dp, 200.dp))
					Text("Mostly sunny", style = Typography.headlineSmall, modifier = Modifier.align(BottomCenter))
				}
				Column(modifier = Modifier.padding(10.dp).fillMaxHeight(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
					Text("27 C", style = Typography.headlineLarge)
					Text("Feels like 30", style = Typography.headlineSmall)
				}
			}
		}


		Surface(modifier = Modifier.fillMaxWidth().padding(10.dp).align(CenterHorizontally), shape = Shapes.medium, color = Blue) {			//current conditions - misc data
			Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start) {
				Text("Current conditions")
				Text("AQI 30")
				Text("UV index: 5")
				Text("UV: moderate")
				Text("Humidity: 45%")
				Text("Wind: 9 km/h")
			}
		}

		Surface(modifier = Modifier.fillMaxWidth().padding(10.dp).align(CenterHorizontally), shape = Shapes.medium, color = Blue) {
			Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start) {
				//Todo 12 hour forecast
				Text("12 hour forecast")
			}
		}

		Surface(modifier = Modifier.fillMaxWidth().padding(10.dp).align(CenterHorizontally), shape = Shapes.medium, color = Blue) {
			//todo: Daily summary + drop down for air quality air and pollen
			Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start) {
				Text("Daily summary")
				Text("AQI 30")
				Text("UV index: 5")
				Text("UV: moderate")
				Text("Humidity: 45%")
			}
		}

		Surface(Modifier.fillMaxWidth(), color = Blue) {
		//todo: 12 hour summary(only tempertature min/max with icon)
		}
	}

}

@Composable fun favouritesDropdown(titleText: String,
                                   isExpanded: Boolean,
                                   onDismiss: () -> Unit,
                                   itemList: List<City>,
                                   onItemSelected: (City, Int) -> Unit,
                                   onIconClicked: (Boolean) -> Unit) {
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
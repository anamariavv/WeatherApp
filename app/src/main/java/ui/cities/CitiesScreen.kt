@file:OptIn(ExperimentalMaterial3Api::class)

package ui.cities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ui.common.component.dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import model.city.City
import ui.base.baseScreen
import ui.cities.model.FavouriteCityListState
import ui.cities.model.SearchBarState
import ui.theme.Shapes
import ui.theme.Typography

@Composable fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {
	val searchBarState by viewModel.searchBarState.collectAsState()
	val favouriteCityListState by viewModel.favouriteCityListState.collectAsState()
	val dialogState by viewModel.dialogState.collectAsState()
	val screenState by viewModel.screenState.collectAsState()
	val context = LocalContext.current

	val launcher = rememberLauncherForActivityResult(
		ActivityResultContracts.RequestMultiplePermissions()
	) { permissions ->
		if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
			viewModel.getCurrentCity()
		} else if (permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)) {
			viewModel.getCurrentCity()
		} else {
			viewModel.showLocationDeniedDialog()
		}
	}

	baseScreen(
		state = screenState,
		titleId = R.string.cities_screen_title,
		content = {
			CitiesScreenContent(
				searchBarState,
				viewModel::updateQueryAndSearch,
				viewModel::performSearch,
				viewModel::onActiveChange,
				viewModel::onSearchbarCloseButtonClick,
				viewModel::toggleFavouriteCity,
				favouriteCityListState,
				viewModel::removeFavouriteCity,
				context,
				launcher,
				viewModel::getCurrentCity
			)
		})

	dialog(dialogState, viewModel::dismissDialog)
}

@Composable
fun CitiesScreenContent(
	searchBarState: SearchBarState,
	updateQueryAndSearch: (String) -> Unit,
	performSearch: (String) -> Unit,
	onActiveChange: (Boolean) -> Unit,
	onSearchbarCloseButtonClick: () -> Unit,
	toggleFavouriteCity: (City, Int) -> Unit,
	favouriteCityListState: FavouriteCityListState,
	removeFavouriteCity: (City, Int) -> Unit,
	context: Context,
	launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
	getCurrentCity: () -> Unit
) {
	Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {

		customSearchbar(
			searchBarState.queryText,
			updateQueryAndSearch,
			performSearch,
			searchBarState.isActive,
			SearchBarDefaults.inputFieldShape,
			onActiveChange,
			onSearchbarCloseButtonClick,
			toggleFavouriteCity,
			searchBarState.queryResult,
			stringResource(id = R.string.cities_screen_search_bar_placeholder),
			stringResource(id = R.string.cities_screen_search_bar_icon_content_description),
			stringResource(id = R.string.cities_screen_close_searchbar_icon_content_description)
		)

		favouriteCityList(favouriteCityListState.cities, removeFavouriteCity)

	}

	Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
		locationButton(context, launcher, getCurrentCity)
	}
}


@Composable fun customSearchbar(
	queryText: String,
	onQueryChange: (String) -> Unit,
	onSearch: (String) -> Unit,
	isActive: Boolean,
	shape: Shape,
	onActiveChange: (Boolean) -> Unit,
	onTrailingButtonClick: () -> Unit,
	onSearchResultItemClicked: (City, Int) -> Unit,
	queryResult: List<City>,
	placeholder: String,
	leadingIconContentDescription: String,
	trailingIconContentDescription: String,
) {
	SearchBar(query = queryText,
	          onQueryChange = onQueryChange,
	          onSearch = onSearch,
	          active = isActive,
	          shape = shape,
	          onActiveChange = onActiveChange,
	          trailingIcon = {
		          if (isActive) {
			          IconButton(
				          onClick = onTrailingButtonClick,
			          ) {
				          Icon(Icons.Rounded.Close, contentDescription = trailingIconContentDescription)
			          }
		          }
	          },
	          placeholder = { Text(placeholder) },
	          leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = leadingIconContentDescription) }
	) {
		LazyColumn(
			modifier = Modifier.fillMaxWidth(),
			contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_content_padding)),
			verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_item_spacing))
		) {
			itemsIndexed(queryResult) { index, it ->
				listItem(city = it, onButtonClick = { onSearchResultItemClicked(it, index) })
			}
		}
	}
}

@Composable fun listItem(city: City, onButtonClick: () -> Unit) {
	val icon: ImageVector
	val contentDescription: String

	when (city.isFavourite) {
		true -> {
			contentDescription = stringResource(id = R.string.cities_screen_remove_from_favourites_icon_content_description)
			icon = Icons.Filled.Favorite
		}
		else -> {
			contentDescription = stringResource(id = R.string.cities_screen_add_to_favourites_icon_content_description)
			icon = Icons.Outlined.FavoriteBorder
		}
	}

	Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
		Column(horizontalAlignment = Alignment.Start) {
			Text(city.localizedName, style = Typography.titleMedium)
			Text(city.countryLocalizedName, style = Typography.labelLarge, color = Color.Gray)
		}
		Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
			IconButton(onClick = onButtonClick) {
				Icon(icon, contentDescription)
			}
		}
	}
}

@Composable fun favouriteCityList(cities: List<City>, onButtonClick: (City, Int) -> Unit) {
	LazyColumn(
		modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp),
		contentPadding = PaddingValues(10.dp),
		verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_item_spacing))
	) {
		itemsIndexed(cities) { index, city ->
			Surface(shape = Shapes.medium, shadowElevation = 5.dp, modifier = Modifier.fillMaxWidth()) {
				Row(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
					Column(horizontalAlignment = Alignment.Start) {
						Text(city.localizedName, style = Typography.titleMedium)
						Text(city.countryLocalizedName, style = Typography.labelLarge, color = Color.Gray)
					}
					Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
						IconButton(onClick = { onButtonClick(city, index) }) {
							Icon(
								Icons.Filled.Delete,
								stringResource(id = R.string.cities_screen_remove_from_favourites_icon_content_description),
								tint = MaterialTheme.colorScheme.primary
							)
						}
					}
				}
			}

		}
	}
}

@Composable fun locationButton(context: Context, launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>, onPermissionGranted: () -> Unit) {
	Button(onClick = {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
			|| ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			onPermissionGranted()
		} else {
			launcher.launch(arrayOf(
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION))
		}
	}, modifier = Modifier.padding(10.dp)) {
		Text(stringResource(id = R.string.cities_screen_get_city_by_location_button_text))
	}
}
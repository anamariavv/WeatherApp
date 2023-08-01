@file:OptIn(ExperimentalMaterial3Api::class)

package ui.cities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ui.common.component.dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import model.City
import ui.theme.Typography

@Composable
fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {
	val searchBarState by viewModel.searchBarState.collectAsState()
	val favouriteCityListState by viewModel.favouriteCityListState.collectAsState()
	val dialogState by viewModel.dialogState.collectAsState()

	Column(
		Modifier
				.fillMaxWidth()
	) {

		customSearchbar(
			searchBarState.queryText,
			viewModel::updateQueryAndSearch,
			viewModel::performSearch,
			searchBarState.isActive,
			SearchBarDefaults.inputFieldShape,
			viewModel::onActiveChange,
			viewModel::onSearchbarCloseButtonClick,
			viewModel::toggleFavouriteCity,
			searchBarState.queryResult,
			stringResource(id = R.string.cities_screen_search_bar_placeholder),
			stringResource(id = R.string.cities_screen_search_bar_icon_content_description),
			stringResource(id = R.string.cities_screen_close_searchbar_icon_content_description)
		)

		favouriteCityList(favouriteCityListState.cities)

		OutlinedButton(onClick = { }) {
			Text(stringResource(id = R.string.cities_screen_get_city_by_location_button_text))
		}

		dialog(dialogState, viewModel::onDialogDismissed)
	}
}


@Composable
fun customSearchbar(
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
	SearchBar(
		query = queryText,
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
					Icon(
						Icons.Rounded.Close,
						contentDescription = trailingIconContentDescription
					)
				}
			}
		},
		placeholder = { Text(placeholder) },
		leadingIcon = {
			Icon(
				Icons.Rounded.Search,
				contentDescription = leadingIconContentDescription
			)
		},
	) {
		LazyColumn(
			modifier = Modifier.fillMaxWidth(),
			contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_content_padding)),
			verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_item_spacing))
		) {
			itemsIndexed(queryResult) { index, it ->
				listItem(
					city = it,
					onButtonClick = { onSearchResultItemClicked(it, index) }
				)
				Divider(
					color = Color.LightGray,
					thickness = dimensionResource(id = R.dimen.divider_thickness)
				)
			}
		}
	}
}

@Composable
fun favouriteCityList(cities: List<City>) {
	LazyColumn(
		modifier = Modifier.fillMaxWidth(),
		contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_content_padding)),
		verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_item_spacing))
	) {
		items(cities) {
			Row(modifier = Modifier.fillMaxWidth()) {
				Column {
					Text(it.localizedName)
					Text(it.countryLocalizedName)
				}
			}
		}
	}
}


@Composable
fun listItem(city: City, onButtonClick: () -> Unit) {
	val icon: ImageVector
	val contentDescription: String

	when (city.isFavourite) {
		true -> {
			contentDescription =
				stringResource(id = R.string.cities_screen_remove_from_favourites_icon_content_description)
			icon = Icons.Filled.Favorite
		}
		false -> {
			contentDescription =
				stringResource(id = R.string.cities_screen_add_to_favourites_icon_content_description)
			icon = Icons.Outlined.FavoriteBorder
		}
	}

	Row(modifier = Modifier.fillMaxWidth()) {
		Column(horizontalAlignment = Alignment.Start) {
			Text(city.localizedName, style = Typography.h6)
			Text(city.countryLocalizedName, style = Typography.caption, color = Color.LightGray)
		}
		Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
			IconButton(onClick = onButtonClick) {
				Icon(
					icon,
					contentDescription
				)
			}
		}
	}
}

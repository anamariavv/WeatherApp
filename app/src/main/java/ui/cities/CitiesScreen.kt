package ui.cities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import model.City
import ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {

    val searchBarState by viewModel.searchBarState.collectAsState()

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        SearchBar(
            query = searchBarState.queryText,
            onQueryChange = viewModel::updateQueryAndSearch,
            onSearch = viewModel::performSearch,
            active = searchBarState.isActive,
            shape = SearchBarDefaults.inputFieldShape,
            onActiveChange = viewModel::onActiveChange,
            placeholder = { Text("City Search") },
            leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "City search") },
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(searchBarState.queryResult) {
                    listItem(city = it, onButtonClick = viewModel::updateCurrentCity)
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }
        }
    }
}


@Composable
fun listItem(city: City, onButtonClick: (City) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(city.localizedName, style = Typography.h6)
            Text(city.country.localizedName, style = Typography.caption, color = Color.LightGray)
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            IconButton(onClick = { onButtonClick(city) }) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Add to favourites")
            }
        }
    }
}

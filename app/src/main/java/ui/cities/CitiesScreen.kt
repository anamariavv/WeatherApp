package ui.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {

    val searchBarState by viewModel.searchBarState.collectAsState()

    Column(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        SearchBar(query = searchBarState.queryText,
            onQueryChange = viewModel::updateQueryAndSearch,
            onSearch = viewModel::performSearch,
            active = searchBarState.isActive,
            shape = SearchBarDefaults.inputFieldShape,
            onActiveChange = {},
            placeholder = { Text("City Search") },
            leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "ApiCity search") }) {
        }
    }
}


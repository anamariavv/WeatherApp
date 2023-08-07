@file:OptIn(ExperimentalMaterialApi::class)

package ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

	val dropdownState by viewModel.dropdownState.collectAsState()

	Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
		Row() {
			Box() {
				Text(dropdownState.selectedValue?.localizedName ?: "", style = Typography.headlineSmall, modifier = Modifier.padding(20.dp))

				DropdownMenu(expanded = dropdownState.isExpanded, onDismissRequest = viewModel::closeDropdown) {
					dropdownState.list.forEachIndexed { index, item ->
						DropdownMenuItem(onClick = { viewModel.onItemSelected(item, index) }) {
							Text(text = item.localizedName)
						}
					}
				}
			}

			IconButton(modifier = Modifier.align(Alignment.CenterVertically), onClick = { viewModel.toggleDropdownExpanded(dropdownState.isExpanded) }) {
				Icon(Icons.Filled.ArrowDropDown, "Toggle dropdown")
			}
		}
	}

}

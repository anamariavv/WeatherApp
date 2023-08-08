@file:OptIn(ExperimentalMaterialApi::class)

package ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

	val dropdownState by viewModel.dropdownState.collectAsState()

	Column(Modifier.fillMaxWidth()) {
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
			Box {
				Text(modifier = Modifier.align(Center), text = dropdownState.selectedValue?.localizedName ?: "", style = Typography.headlineSmall)

				DropdownMenu(expanded = dropdownState.isExpanded, onDismissRequest = viewModel::closeDropdown) {
					dropdownState.list.forEachIndexed { index, item ->
						DropdownMenuItem(onClick = { viewModel.onItemSelected(item, index) }) {
							Text(text = item.localizedName)
						}
					}
				}
			}

			IconButton(onClick = { viewModel.toggleDropdownExpanded(dropdownState.isExpanded) }) {
				Icon(Icons.Filled.KeyboardArrowDown, "Toggle dropdown")
			}
		}

	}
}
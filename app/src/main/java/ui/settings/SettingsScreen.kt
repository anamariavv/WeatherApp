package ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import ui.base.baseScreen
import ui.common.component.dialog
import ui.settings.model.RadioGroupState

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
	val dialogState by viewModel.dialogState.collectAsState()
	val screenState by viewModel.screenState.collectAsState()
	val unitRadioGroupState by viewModel.unitRadioGroupState.collectAsState()

	baseScreen(state = screenState, R.string.settings, content = { SettingsScreenContent(unitRadioGroupState, viewModel::setSelectedOption) })

	dialog(dialogState, viewModel::dismissDialog)
}

@Composable
fun SettingsScreenContent(unitRadioGroupState: RadioGroupState, onItemSelected: (Boolean) -> Unit) {
	Box(Modifier.fillMaxSize()) {
		Column(Modifier.selectableGroup().align(Alignment.TopCenter)) {
			unitRadioGroupState.items.forEach {
				Row(
					Modifier.fillMaxWidth()
						.height(56.dp)
						.selectable(
							selected = it == unitRadioGroupState.selectedOption,
							onClick = { onItemSelected(it.isMetric) },
							role = Role.RadioButton
						).padding(horizontal = 16.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					RadioButton(
						selected = it == unitRadioGroupState.selectedOption,
						onClick =  { onItemSelected(it.isMetric) }
					)
					Text(
						text = it.text,
						modifier = Modifier.padding(start = 16.dp)
					)
				}

			}
		}
		Text(stringResource(id = R.string.settings_screen_data_info_text), modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp))
	}
}
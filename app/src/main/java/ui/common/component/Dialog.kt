@file:OptIn(ExperimentalMaterial3Api::class)

package ui.common.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import ui.base.model.DialogState

@Composable
fun dialog(
	state: DialogState,
	onDismissRequest: () -> Unit
) {
	//Todo customize when multiple types will be needed-use type with buttons composable call
	if (state.isVisible) {
		AlertDialog(
			shape = RoundedCornerShape(20.dp),
			onDismissRequest = onDismissRequest,
			title = {
				Text("Attention")
			},
			text = {
				Text(state.message)
			},
			confirmButton = {
				TextButton(onClick = onDismissRequest) {
					Text("Dismiss")
				}
			},
			properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
		)
	}
}
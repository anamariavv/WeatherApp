@file:OptIn(ExperimentalMaterial3Api::class)

package ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ui.base.model.DialogState

@Composable
fun dialog(
	state: DialogState,
	onDismissRequest: () -> Unit
) {
	//Todo customize when multiple types will be needed-use type with buttons composable call
	if (state.isVisible) {
		Dialog(
			onDismissRequest = onDismissRequest,
			properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
		) {
			Surface(
				shape = RoundedCornerShape(16.dp),
				color = Color.White
			) {
				Column(Modifier.padding(20.dp).fillMaxWidth()) {
					Text("Attention")
					Text(state.message)
					if(state.isLoading) {
						CircularProgressIndicator()
					}
					TextButton(onClick = onDismissRequest) {
						Text("Dismiss")
					}
				}
			}
		}
	}
}
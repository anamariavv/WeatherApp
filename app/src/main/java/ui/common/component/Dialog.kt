package ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.weatherapp.R
import ui.common.model.DialogState
import ui.theme.Shapes
import ui.theme.Typography
import utils.empty

@Composable
fun dialog(
	state: DialogState,
	onDismissRequest: () -> Unit
) {
	if (state !is DialogState.None) {
		Dialog(
			onDismissRequest = onDismissRequest,
			properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
		) {
			Surface(shape = Shapes.medium) {
				Column(
					Modifier
						.padding(20.dp)
						.fillMaxWidth()
				) {
					Text(text = state.message?.getTitleId()?.let { stringResource(id = it) } ?: String.empty(),
					     style = Typography.titleMedium,
					     modifier = Modifier.padding(bottom = 20.dp)
					)
					Text(text = state.message?.getMessageId()?.let { messageId ->
						state.message.getArguments()?.let {
							stringResource(id = messageId, *it.toTypedArray())
						} ?: stringResource(id = messageId)
					} ?: String.empty(),
					     style = Typography.bodyMedium,
					     modifier = Modifier.padding(bottom = 20.dp)
					)

					if (state is DialogState.Loading) {
						CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
					}

					TextButton(modifier = Modifier
							.align(End)
							.padding(top = 20.dp), onClick = onDismissRequest
					) {
						Text(stringResource(id = R.string.dialog_dismiss_button))
					}
				}
			}
		}
	}
}
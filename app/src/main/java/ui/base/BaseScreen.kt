package ui.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ui.base.model.BaseScreenState
import ui.theme.Typography

@Composable
fun baseScreen(
	state: BaseScreenState,
	titleId: Int?,
	content: @Composable () -> Unit
) {
	Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
		if (titleId != null) {
			Text(stringResource(id = titleId), style = Typography.headlineSmall, modifier = Modifier.padding(20.dp))

		}
		when (state) {
			is BaseScreenState.Content -> content()
			is BaseScreenState.Loading -> loadingIndicator()
			else -> emptyContent()
		}
	}
}

@Composable
fun loadingIndicator() {
	CircularProgressIndicator()
}

@Composable
fun emptyContent() {
	Text("No data to display", style = Typography.titleMedium, color = Color.LightGray)
}

package ui.common.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import ui.theme.Typography
import ui.theme.Blue
import ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(titleText: String) {

    CenterAlignedTopAppBar(
        title = {
            Text(titleText, maxLines = 1, style = Typography.h1, color = White)
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.large)),
                    imageVector = Icons.Sharp.Add,
                    contentDescription = stringResource(id = R.string.top_app_bar_button_content_description),
                    tint = White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Blue)
    )
}

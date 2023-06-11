package ui.common.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import navigation.BottomNavItem

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    onItemClicked: (BottomNavItem) -> Unit,
    selectedItem: BottomNavItem
) {

    BottomNavigation {
        items.forEach {
            BottomNavigationItem(
                selected = selectedItem == it,
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = stringResource(id = it.contentDescriptionId),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.large))
                    )
                },
                onClick = { onItemClicked(it) }
            )
        }
    }
}
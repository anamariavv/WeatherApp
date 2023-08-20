package navigation.component

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import navigation.NavigationCommand

object NavigationDirection {
	object Home : NavigationCommand {
		override val arguments: List<NamedNavArgument>
			get() = emptyList()
		override val destination: String
			get() = "route_home"
	}

	object Weekly {
		const val KEY = "locationKey"
		const val destination: String = "location/{$KEY}"

		val weeklyArguments: List<NamedNavArgument> = listOf(navArgument(KEY) {
			type = NavType.StringType
		})

		fun createRouteWithArgs(locationInfo: String) = object : NavigationCommand {
			override val arguments: List<NamedNavArgument>
				get() = weeklyArguments
			override val destination: String
				get() = "location/$locationInfo"
		}
	}

	object Settings : NavigationCommand {
		override val arguments: List<NamedNavArgument>
			get() = emptyList()
		override val destination: String
			get() = "route_settings"
	}
	object Cities : NavigationCommand {
		override val arguments: List<NamedNavArgument>
			get() = emptyList()
		override val destination: String
			get() = "route_cities"
	}
}
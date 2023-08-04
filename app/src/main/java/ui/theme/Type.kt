package ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	h1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 25.sp
	),
	h2 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 20.sp
	),
	h6 = TextStyle(
		fontWeight = FontWeight.Medium,
		fontFamily = FontFamily.Default,
		fontSize = 20.sp,
		letterSpacing = 0.15.sp
	),
	subtitle1 = TextStyle(
		fontWeight = FontWeight.Medium,
		fontFamily = FontFamily.Default,
		fontSize = 16.sp,
		letterSpacing = 0.15.sp,
		color = Color.LightGray
	),
	subtitle2 = TextStyle(
		fontWeight = FontWeight.Normal,
		fontFamily = FontFamily.Default,
		fontSize = 16.sp,
		letterSpacing = 0.15.sp,
		color = Color.Gray
	)


	/* Other default text styles to override
	button = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W500,
		fontSize = 14.sp
	),
	caption = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp
	)
	*/
)
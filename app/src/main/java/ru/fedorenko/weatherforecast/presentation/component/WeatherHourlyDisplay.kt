package ru.fedorenko.weatherforecast.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.fedorenko.weatherforecast.core.constant.Const
import ru.fedorenko.weatherforecast.domain.model.WeatherDataModel
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import ru.fedorenko.weatherforecast.R

@Composable
fun WeatherHourlyDisplay(
    weather: WeatherDataModel,
) {
    val time = remember(weather) { weather.time.format(DateTimeFormatter.ofPattern(Const.PATTERN_HOUR_MINUTE)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = time,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = weather.type.icon),
            contentDescription = weather.type.name.asString(),
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.your_celsius, weather.temperature.roundToInt()),
            fontWeight = FontWeight.Medium
        )
    }
}
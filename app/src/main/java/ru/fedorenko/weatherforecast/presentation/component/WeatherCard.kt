package ru.fedorenko.weatherforecast.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fedorenko.weatherforecast.core.constant.Const
import ru.fedorenko.weatherforecast.domain.model.WeatherDataModel
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import ru.fedorenko.weatherforecast.R

@Composable
fun WeatherCard(
    weather: WeatherDataModel,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.your_today, weather.time.format(DateTimeFormatter.ofPattern(Const.PATTERN_HOUR_MINUTE))),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = weather.type.icon),
                contentDescription = stringResource(id = R.string.cd_weather_today_type),
                modifier = Modifier.width(160.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.your_celsius, weather.temperature.roundToInt()),
                fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = weather.type.name.asString(),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    icon = R.drawable.ic_pressure,
                    contentDescription = R.string.cd_pressure_in_hpa,
                    value = weather.pressure.roundToInt(),
                    unit = stringResource(id = R.string.hpa)
                )
                WeatherDataDisplay(
                    icon = R.drawable.ic_drop,
                    contentDescription = R.string.cd_humidity_in_percent,
                    value = weather.humidity.roundToInt(),
                    unit = stringResource(id = R.string.percent)
                )
                WeatherDataDisplay(
                    icon = R.drawable.ic_wind,
                    contentDescription = R.string.cd_wind_speed_in_km_hour,
                    value = weather.windSpeed.roundToInt(),
                    unit = stringResource(id = R.string.km_hour)
                )
            }
        }
    }
}
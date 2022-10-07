package ru.fedorenko.weatherforecast.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.fedorenko.weatherforecast.domain.model.WeatherDataModel
import ru.fedorenko.weatherforecast.R

@Composable
fun WeatherForecast(
    data: List<WeatherDataModel>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.today),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(data) { weather ->
                WeatherHourlyDisplay(weather = weather)
            }
        }
    }
}
package com.example.netclanexplorer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.netclanexplorer.R
import com.example.netclanexplorer.model.Person
import com.example.netclanexplorer.ui.theme.progress_indicator
import com.example.netclanexplorer.ui.theme.progress_track_color
import com.example.netclanexplorer.ui.theme.surface_light

@Composable
fun PersonalCardItem(person: Person, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(15.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.padding(start = 25.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = surface_light,
                ),
                shape = RoundedCornerShape(13),
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "+INVITE",
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold,
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 60.dp)
                ) {
                    val name = person.firstName + " " + (person.middleName ?: "") + person.lastName
                    Text(
                        name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        person.address,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        stringResource(R.string.location_range, person.locationRange),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(15.dp)
                            .width(123.dp)
                            .clip(RoundedCornerShape(50)),
                        progress = 0.6f,
                        color = progress_indicator,
                        trackColor = progress_track_color
                    )
                }

                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        "Coffee | Business | Friendship",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        person.title,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        person.description ?: "",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Box(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                val abbreviation = "${person.firstName[0]}${person.lastName[0]}"
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(70.dp),
                    shape = RoundedCornerShape(19),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            abbreviation,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }


                }
            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalCardItemPreview() {
    PersonalCardItem(
        Person(
            firstName = "Bibek",
            middleName = null,
            lastName = "Bhujel",
            address = "Pokhara",
            locationRange = 400,
            title = "\"Hi! I am open for new connections \uD83D\uDE0A\"",
            "Anything in the context for th description and also for the overall thigns in life "
        )
    )

}
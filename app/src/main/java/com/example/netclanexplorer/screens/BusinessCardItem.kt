package com.example.netclanexplorer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
import com.example.netclanexplorer.ui.theme.tab_row_container_color

@Composable
fun BusinessCardItem(person: Person, modifier: Modifier = Modifier) {
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
                    Text(
                        person.firstName + " " + (person.middleName ?: "") + person.lastName,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 7.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = tab_row_container_color
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "call",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = tab_row_container_color
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_user),
                            contentDescription = "Add Friend",
                            modifier = Modifier.size(23.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )

                    }
                    Spacer(modifier = Modifier.width(100.dp))
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

@Preview
@Composable
fun BusinessCardItemPreview() {
    BusinessCardItem(
        person = Person(
            firstName = "Manish",
            middleName = "Raj",
            lastName = "Yadav",
            locationRange = 1000,
            address = "Kathmandu",
            title = "Hi community ! I am available at your service!",
            description = null
        )
    )

}
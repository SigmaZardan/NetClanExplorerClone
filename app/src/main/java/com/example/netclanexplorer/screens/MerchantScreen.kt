package com.example.netclanexplorer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netclanexplorer.R
import com.example.netclanexplorer.screens.commonUI.CommercePanelTopAppBar
import com.example.netclanexplorer.ui.theme.tab_row_container_color

@Composable
fun MerchantScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CommercePanelTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = tab_row_container_color,
                shape = CircleShape,
                modifier = Modifier.padding(bottom = 30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.icon_image),
                contentDescription = null,
                modifier = Modifier.size(240.dp),
            )

            Text(
                "Your explore list is EMPTY",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "Sorry, we could not find any user near you.Try increasing your search radius.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
                lineHeight = 17.sp,
                softWrap = true,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 13.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = tab_row_container_color
                )
            ) {
                Text("INCREASE RADIUS", modifier.padding(start = 20.dp, end = 20.dp))
            }


        }

    }
}

@Preview
@Composable
fun MerchantScreenPreview() {
    MerchantScreen()
}
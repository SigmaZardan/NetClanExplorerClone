package com.example.netclanexplorer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.netclanexplorer.screens.BusinessScreen
import com.example.netclanexplorer.screens.MerchantScreen
import com.example.netclanexplorer.screens.PersonalScreen
import com.example.netclanexplorer.screens.RefineScreen
import com.example.netclanexplorer.ui.theme.tab_row_container_color
import kotlinx.coroutines.launch


enum class NetClanScreen {
    Explore, Refine
}

enum class HomeTabs {
    Personal, Business, Merchant
}

data class ScaffoldViewState(
    val title: @Composable () -> Unit = {},
    val navigationIcon: @Composable () -> Unit = {}
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NetClanExplorerApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val scaffoldState = remember {
        mutableStateOf(
            ScaffoldViewState(
            )
        )

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            NetClanTopAppBar(
                scaffoldState = scaffoldState

            )
        }
    )
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NetClanScreen.Explore.name,
            modifier = Modifier.padding(paddingValues)
        ) {


            composable(route = NetClanScreen.Explore.name) {
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = tab_row_container_color,
                    indicator = { tabPositions ->
                        if (selectedTabIndex.value < tabPositions.size) {
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                ) {
                    HomeTabs.entries.forEachIndexed { index, currentTab ->
                        Tab(
                            selected = selectedTabIndex.value == index,
                            selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedContentColor = MaterialTheme.colorScheme.outline,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentTab.ordinal)
                                }
                            },
                            text = { Text(text = currentTab.name) },
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxSize()
                ) {
                    LaunchedEffect(Unit) {
                        scaffoldState.value = ScaffoldViewState(
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier.padding(start = 15.dp),
                                    ) {
                                        Text(
                                            stringResource(R.string.howdy_bibek_bhujel),
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            style = MaterialTheme.typography.displayMedium
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Filled.LocationOn,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onPrimary,
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .padding(end = 4.dp)
                                            )
                                            Text(
                                                stringResource(R.string.pokhara_lekhnath),
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                style = MaterialTheme.typography.displaySmall
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.refine),
                                            contentDescription = "Refine",
                                            modifier = Modifier
                                                .clickable { navController.navigate(NetClanScreen.Refine.name) }
                                                .size(30.dp),
                                            tint = MaterialTheme.colorScheme.onPrimary
                                        )
                                        Text(
                                            "Refine", style = MaterialTheme.typography.displaySmall,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.menu),
                                        contentDescription = "Menu",
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }


                            }
                        )
                    }
                    when (HomeTabs.entries[selectedTabIndex.value]) {
                        HomeTabs.Personal -> PersonalScreen()
                        HomeTabs.Business -> {
                            BusinessScreen()
                        }

                        HomeTabs.Merchant -> {
                            MerchantScreen()
                        }
                    }
                }
            }
            composable(route = NetClanScreen.Refine.name) {
                RefineScreen(
                    scaffoldState = scaffoldState,
                    onBackButtonClicked = {
                        navController.popBackStack()
                    },
                    onSaveAndExploreButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetClanTopAppBar(
    scaffoldState: MutableState<ScaffoldViewState>

) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = scaffoldState.value.title,

        navigationIcon = scaffoldState.value.navigationIcon

    )
}


@Preview(showBackground = true)
@Composable
fun NetClanExplorerAppPreview() {
    NetClanExplorerApp()
}
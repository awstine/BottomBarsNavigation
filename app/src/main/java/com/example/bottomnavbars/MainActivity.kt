package com.example.bottomnavbars

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomnavbars.ui.theme.BottomNavbarsTheme

data class BottomNavItem(
    val title: String,
    // val icon: Int,
    val selected: ImageVector,
    val unselected: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavbarsTheme {
                // A surface container using the 'background' color from the theme
                val items = listOf(
                    BottomNavItem(
                        title = "Home",
                        selected = Icons.Filled.Home,
                        unselected = Icons.Outlined.Home,
                        hasNews = false,
                        ),
                    BottomNavItem(
                        title = "Notifications",
                        selected = Icons.Filled.Notifications,
                        unselected = Icons.Outlined.Notifications,
                        hasNews = false,
                        badgeCount = 35,
                        ),
                    BottomNavItem(
                        title = "Close",
                        selected = Icons.Filled.Close,
                        unselected = Icons.Outlined.Close,
                        hasNews = false,
                        )
                )

//
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            //if for insttnce you want to navigate to specific screens
                                            //navController.navigate(item.title)
                                                  },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        //alwaysShowLabel = true,
                                        icon = {
                                            //Badge Box allows us set a badge on an icon
                                            BadgedBox(
                                                badge = {
                                                     if(item.badgeCount != null){
                                                            Text(text = item.badgeCount.toString())
                                                        }else if (item.hasNews){
                                                            Badge {
                                                                Text(text = "Mambo yako ")
                                                            }
                                                        }
                                                },
                                            ) {
                                                Icon(
                                                    imageVector = if (selectedItemIndex == index) item.selected else
                                                        item.unselected,
                                                    contentDescription = item.title
                                                )

                                            }
                                        }
                                    )
                                }

                            }
                        }
                    ) {paddingValues ->
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(paddingValues)
                    ) {

                    }

                    }
                }
            }
        }
    }
}

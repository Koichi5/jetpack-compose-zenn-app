package com.example.zennapp.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.zennapp.ui.theme.ZennTopAppBar
import com.example.zennapp.ui.theme.screens.home.HomeScreen
import com.example.zennapp.ui.theme.screens.home.ZennHomeScreenViewModel
import com.example.zennapp.ui.theme.screens.mypage.MyPageScreen
import com.example.zennapp.ui.theme.screens.mypage.ZennMyPageScreenViewModel
import com.example.zennapp.ui.theme.screens.topics.TopicsScreen
import com.example.zennapp.ui.theme.screens.topics.ZennTopicsScreenViewModel

sealed class Screen(val route: String, val label: String, val icon: @Composable () -> Unit) {
    data object Home :
        Screen("home", "Home", { Icon(Icons.Default.Home, contentDescription = null) })

    data object Topics :
        Screen("topics", "Topics", { Icon(Icons.Default.List, contentDescription = null) })

    data object MyPage :
        Screen("my_page", "My Page", { Icon(Icons.Default.Person, contentDescription = null) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZennAppScaffold() {
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Topics, Screen.MyPage)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { ZennTopAppBar(scrollBehavior = scrollBehavior) },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { screen.icon() },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        MyAppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MyAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    val zennHomeScreenViewModel: ZennHomeScreenViewModel =
        viewModel(factory = ZennHomeScreenViewModel.Factory)
    val zennMyPageScreenViewModel: ZennMyPageScreenViewModel =
        viewModel(factory = ZennMyPageScreenViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                zennUiState = zennHomeScreenViewModel.zennUiState,
                retryAction = zennHomeScreenViewModel::getArticles,
                getArticles = zennHomeScreenViewModel::getArticles,
                getLatestArticles = zennHomeScreenViewModel::getLatestArticles
            )
        }
        composable(Screen.MyPage.route) {
            MyPageScreen(
                zennUiState = zennMyPageScreenViewModel.zennUiState,
                retryAction = zennMyPageScreenViewModel::getMyArticles,
                getArticles = zennMyPageScreenViewModel::getMyArticles,
                getLatestArticles = zennMyPageScreenViewModel::getLatestArticles
            )
        }
        composable(Screen.Topics.route) {
            TopicsScreen(
                viewModel = viewModel(factory = ZennTopicsScreenViewModel.Factory)
            )
        }
    }
}


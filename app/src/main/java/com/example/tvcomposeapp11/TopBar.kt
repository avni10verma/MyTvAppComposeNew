import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Home", "Favorites", "Movies", "Apps")
    val tabRoutes = listOf("Home screen", "favourites", "movies", "apps")


    // Create FocusRequester for the Home tab
    val homeFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        // Request focus for the Home tab
        homeFocusRequester.requestFocus()
    }


    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .focusGroup(),
        contentColor = Color.White
    ) {
        // Add the user image icon as the first item in the TabRow
        IconTab(
            icon = Icons.Default.Person,
            contentDescription = "User",
            onClick = { navController.navigate("UserProfile") }
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Add the tab items
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(tabRoutes[index])
                },
                text = { Text(title) },
                modifier = Modifier
                    .then(if (index == 0) Modifier.focusRequester(homeFocusRequester) else Modifier) // Apply focusRequester to Home tab
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Add the search and settings icons

        IconTab(
            icon = Icons.Default.Search,
            contentDescription = "Search",
            onClick = { navController.navigate("Search screen") }
        )


        IconTab(
            icon = Icons.Default.Settings,
            contentDescription = "Settings",
            onClick = { navController.navigate("settings") }
        )

        }
    }


@Composable
fun IconTab(icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
    Tab(
        selected = false,
        onClick = onClick,
        text = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(Color.Black)
    )
}

package fr.outadoc.woolly.ui.feature.publictimeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.outadoc.woolly.common.feature.publictimeline.PublicTimelineScreenResources
import fr.outadoc.woolly.common.feature.publictimeline.PublicTimelineSubScreen
import fr.outadoc.woolly.ui.navigation.TopAppBarWithMenu
import org.kodein.di.compose.instance

@Composable
fun PublicTimelineTopAppBar(
    title: @Composable () -> Unit,
    currentSubScreen: PublicTimelineSubScreen,
    onCurrentSubScreenChanged: (PublicTimelineSubScreen) -> Unit,
    drawerState: DrawerState?
) {
    Surface(
        color = MaterialTheme.colors.primarySurface,
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Column {
            TopAppBarWithMenu(
                backgroundColor = MaterialTheme.colors.primarySurface,
                title = title,
                drawerState = drawerState,
                elevation = 0.dp
            )

            PublicTimelineTabRow(currentSubScreen, onCurrentSubScreenChanged)
        }
    }
}

@Composable
fun PublicTimelineTabRow(
    currentSubScreen: PublicTimelineSubScreen,
    onCurrentSubScreenChanged: (PublicTimelineSubScreen) -> Unit,
) {
    val res by instance<PublicTimelineScreenResources>()

    val tabs = listOf(
        PublicTimelineSubScreen.Local,
        PublicTimelineSubScreen.Global
    )

    TabRow(selectedTabIndex = tabs.indexOf(currentSubScreen)) {
        tabs.forEach { screen ->
            Tab(
                modifier = Modifier.height(48.dp),
                selected = currentSubScreen == screen,
                onClick = {
                    onCurrentSubScreenChanged(screen)
                }
            ) {
                Text(text = res.getScreenTitle(screen))
            }
        }
    }
}

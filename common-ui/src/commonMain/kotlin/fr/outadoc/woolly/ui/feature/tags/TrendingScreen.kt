package fr.outadoc.woolly.ui.feature.tags

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import fr.outadoc.woolly.common.feature.tags.viewmodel.TrendingViewModel
import org.kodein.di.compose.instance

@Composable
fun TrendingScreen(insets: PaddingValues) {
    val viewModel by instance<TrendingViewModel>()
    val trends by viewModel.trendingTags.collectAsState(initial = emptyList())

    val uriHandler = LocalUriHandler.current

    LazyColumn(contentPadding = insets) {
        items(trends) { tag ->
            TrendingTagListItem(
                tag = tag,
                onClick = { uriHandler.openUri(tag.url) }
            )
        }
    }
}

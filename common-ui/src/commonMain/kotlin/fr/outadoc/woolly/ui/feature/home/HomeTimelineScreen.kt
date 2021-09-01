package fr.outadoc.woolly.ui.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import fr.outadoc.mastodonk.api.entity.Attachment
import fr.outadoc.mastodonk.api.entity.Status
import fr.outadoc.woolly.common.feature.home.viewmodel.HomeTimelineViewModel
import fr.outadoc.woolly.ui.feature.status.StatusList
import org.kodein.di.compose.instance

@Composable
fun HomeTimelineScreen(
    insets: PaddingValues,
    listState: LazyListState,
    onStatusClick: (Status) -> Unit = {},
    onAttachmentClick: (Attachment) -> Unit = {}
) {
    val viewModel by instance<HomeTimelineViewModel>()
    StatusList(
        insets = insets,
        statusFlow = viewModel.homePagingItems,
        lazyListState = listState,
        onStatusAction = viewModel::onStatusAction,
        onStatusClick = onStatusClick,
        onAttachmentClick = onAttachmentClick
    )
}

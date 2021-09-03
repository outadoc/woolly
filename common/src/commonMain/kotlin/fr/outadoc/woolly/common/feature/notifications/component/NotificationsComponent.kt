package fr.outadoc.woolly.common.feature.notifications.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.paging.*
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.lifecycle.doOnDestroy
import fr.outadoc.mastodonk.api.entity.Notification
import fr.outadoc.mastodonk.api.entity.paging.PageInfo
import fr.outadoc.mastodonk.paging.api.endpoint.notifications.getNotificationsSource
import fr.outadoc.woolly.common.feature.client.MastodonClientProvider
import fr.outadoc.woolly.common.feature.client.latestClientOrThrow
import fr.outadoc.woolly.common.feature.mainrouter.AppScreen
import fr.outadoc.woolly.common.feature.navigation.ScrollableComponent
import fr.outadoc.woolly.common.feature.navigation.tryScrollToTop
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

class NotificationsComponent(
    componentContext: ComponentContext,
    pagingConfig: PagingConfig,
    private val clientProvider: MastodonClientProvider
) : ComponentContext by componentContext, ScrollableComponent {

    private val componentScope = MainScope()

    // TODO save state
    val listState = LazyListState()

    private val pagingSource: PagingSource<PageInfo, Notification>
        get() = clientProvider
            .latestClientOrThrow
            .notifications.getNotificationsSource()

    val pagingData: Flow<PagingData<Notification>> =
        Pager(pagingConfig) { pagingSource }
            .flow
            .cachedIn(componentScope)

    override suspend fun scrollToTop(currentConfig: AppScreen?) {
        listState.tryScrollToTop()
    }

    init {
        lifecycle.doOnDestroy {
            componentScope.cancel()
        }
    }
}

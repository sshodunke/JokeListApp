package com.smithshodunke.jokelistapp.presentation.util

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToEnd() = layoutInfo
    .visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount -1
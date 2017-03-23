package com.task.usecase;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.BaseCallback;

import java.util.List;

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface UseCase {
    void getNews(BaseCallback callback);

    NewsItem searchByTitle(List<NewsItem> news, String keyWord);
}

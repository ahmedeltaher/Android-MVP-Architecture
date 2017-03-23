package com.task.ui.component.news;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.BaseView;
import com.task.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public interface NewsContract {
    interface View extends BaseView {
        void initializeNewsList(List<NewsItem> news);

        void setLoaderVisibility(boolean isVisible);

        void navigateToDetailsScreen(NewsItem news);

        void setNoDataVisibility(boolean isVisible);

        void setListVisibility(boolean isVisible);

        void showSearchError();

        void incrementCountingIdlingResource();

        void decrementCountingIdlingResource();
    }

    interface Presenter {
        void getNews();

        RecyclerItemListener getRecyclerItemListener();

        void onSearchClick(String newsTitle);
    }
}

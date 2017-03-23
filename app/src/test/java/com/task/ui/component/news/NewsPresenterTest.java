
package com.task.ui.component.news;

import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.listeners.BaseCallback;
import com.task.usecase.NewsUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NewsPresenterTest {

    @Mock
    private NewsUseCase newsUseCase;
    @Mock
    private NewsModel newsModelMock;
    @Mock
    private NewsContract.View homeView;
    @Mock
    private BaseCallback callback;
    @Mock
    private List<NewsItem> newsItems;
    @Mock
    private NewsItem newsItem;

    private NewsPresenter newsPresenter;
    private String newsTitle = "this is test";
    private NewsModel newsModel;
    private TestModelsGenerator testModelsGenerator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testModelsGenerator = new TestModelsGenerator();
        newsModel = testModelsGenerator.generateNewsModel(newsTitle);
        doAnswer(invocation -> {
            ((BaseCallback) invocation.getArguments()[0]).onSuccess(newsModel);
            return null;
        }).when(newsUseCase).getNews(any(BaseCallback.class));
        newsPresenter = new NewsPresenter(newsUseCase);
        newsPresenter.setView(homeView);
    }

    @Test
    public void getNewsList() {
        // Let's do a synchronous answer for the callback
        doAnswer(invocation -> {
            ((BaseCallback) invocation.getArguments()[0]).onSuccess(newsModel);
            return null;
        }).when(newsUseCase).getNews(any(BaseCallback.class));

        newsPresenter.getNews();
        verify(homeView, times(1)).setLoaderVisibility(true);
        verify(homeView, times(2)).setNoDataVisibility(false);
        verify(homeView, times(1)).setListVisibility(false);
        verify(newsUseCase, times(1)).getNews(any(BaseCallback.class));
        assertThat(newsPresenter.getNewsModel(), is(equalTo(newsModel)));
    }

    @Test
    public void testSearchSuccess() {
        when(newsUseCase.searchByTitle(newsModel.getNewsItems(), newsTitle)).thenReturn(newsItem);
        newsPresenter.getNews();
        newsPresenter.onSearchClick(newsTitle);
        verify(homeView, times(1)).navigateToDetailsScreen(any(NewsItem.class));
    }

    @Test
    public void testSearchFailedWhileEmptyList() {
        newsPresenter.getNews();
        newsPresenter.onSearchClick(newsTitle);
        assertThat(newsModelMock.getNewsItems().size(), equalTo(0));
        verify(homeView, times(1)).showSearchError();
    }

    @Test
    public void testSearchFailedWhenNothingMatches() {
        when(newsUseCase.searchByTitle(any(), any())).thenReturn(null);
        newsPresenter.getNews();
        newsPresenter.onSearchClick(newsTitle);
        verify(homeView, times(1)).showSearchError();
    }

    @After
    public void tearDown() throws Exception {
    }
}
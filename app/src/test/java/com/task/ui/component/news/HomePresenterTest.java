
package com.task.ui.component.news;

import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.usecase.NewsUseCase;
import com.task.usecase.NewsUseCase.Callback;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    NewsUseCase newsUseCase;

    @Mock
    NewsModel newsModel;

    @Mock
    HomeView homeView;

    @Mock
    Callback callback;


    HomePresenter homePresenter;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    private String newsTitle = "this is test";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        homePresenter = new HomePresenter(newsUseCase);
        homePresenter.setView(homeView);
    }

    @Test
    public void getNewsList() {
        NewsModel newsModel = generateNewsModel();

        // Let's do a synchronous answer for the callback
        doAnswer(invocation -> {
            ((Callback) invocation.getArguments()[0]).onSuccess(newsModel);
            return null;
        }).when(newsUseCase).getNews(any(Callback.class));

        homePresenter.getNews();
        verify(homeView, times(1)).setLoaderVisibility(true);
        verify(homeView, times(1)).setNoDataVisibility(false);
        verify(homeView, times(2)).setListVisibility(false);
        verify(newsUseCase, times(1)).getNews(any(Callback.class));
        assertThat(homePresenter.getNewsModel(), is(equalTo(newsModel)));
    }

    @Test
    public void testSearchSuccess() {
    }

    @Test
    public void testSearchFailed() {
    }

    @Test
    public void onSearchClick() {
    }

    @After
    public void tearDown() throws Exception {
    }

    private NewsModel generateNewsModel() {
        NewsModel newsModel = new NewsModel();
        String stup = "some text";
        newsModel.setCopyright(stup);
        newsModel.setLastUpdated(stup);
        newsModel.setSection(stup);
        newsModel.setStatus(stup);
        newsModel.setNumResults(25L);
        List<NewsItem> newsItems = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            NewsItem newsItem = new NewsItem();
            newsItem.setTitle(stup);
            newsItem.setAbstract(stup);
            newsItem.setUrl(stup);
            newsItems.add(newsItem);
        }
        return newsModel;
    }
}
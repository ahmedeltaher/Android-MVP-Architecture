package com.task.ui.component.news;

import android.os.Handler;

import com.task.data.DataRepository;
import com.task.data.remote.ServiceResponse;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.listeners.BaseCallback;
import com.task.usecase.NewsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ThreadPoolExecutor;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ahmedeltaher on 3/8/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NewsUseCaseTest {
    @Mock
    DataRepository dataRepository;
    @Mock
    BaseCallback callback;
    @Mock
    ThreadPoolExecutor executor;

    Handler handler;
    NewsUseCase newsUseCase;
    ServiceResponse serviceResponse;
    TestModelsGenerator testModelsGenerator;

    @Before
    public void setUp() throws Exception {
        handler = mock(Handler.class);
        testModelsGenerator = new TestModelsGenerator();

        when(handler.post(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });

        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(executor).execute(any(Runnable.class));
    }

    @Test
    public void testGetNewsSuccessful() {
        serviceResponse = testModelsGenerator.getNewsSuccessfulModel();
        when(dataRepository.requestNews()).thenReturn(serviceResponse);
        newsUseCase = new NewsUseCase(dataRepository, handler, executor);
        newsUseCase.getNews(callback);
        verify(callback, times(1)).onSuccess(any(NewsModel.class));
        verify(callback, never()).onFail();
    }

    @Test
    public void testGetNewsFail() {
        serviceResponse = testModelsGenerator.getNewsErrorModel();
        when(dataRepository.requestNews()).thenReturn(serviceResponse);
        newsUseCase = new NewsUseCase(dataRepository, handler, executor);
        newsUseCase.getNews(callback);
        verify(callback, never()).onSuccess(any(NewsModel.class));
        verify(callback, times(1)).onFail();
    }

    @Test
    public void searchByTitleSuccess() {
        String stup = "this is news Title";
        newsUseCase = new NewsUseCase(dataRepository, handler, executor);
        NewsItem newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stup).getNewsItems(), stup);
        assertNotNull(newsItem);
        assertEquals(newsItem.getTitle(), stup);
    }

    @Test
    public void searchByTitleFail() {
        String stupTitle = "this is news Title";
        String stupSearch = "search title";
        newsUseCase = new NewsUseCase(dataRepository, handler, executor);
        NewsItem newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stupTitle).getNewsItems(), stupSearch);
        assertEquals(newsItem, null);
    }
}

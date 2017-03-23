package com.task.data;

import com.task.data.local.LocalRepository;
import com.task.data.remote.RemoteRepository;
import com.task.data.remote.ServiceResponse;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class DataRepository implements DataSource {
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public ServiceResponse requestNews() {
        return remoteRepository.getNews();
    }
}

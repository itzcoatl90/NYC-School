package com.itz.corp.feature.school.dagger.module;

import androidx.lifecycle.MutableLiveData;

import com.itz.corp.feature.school.model.SchoolsListNetworkData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolsListModule {

    @Provides
    @Singleton
    MutableLiveData<SchoolsListNetworkData> provideSchoolsListLiveData() {
        return new MutableLiveData<>();
    }
}

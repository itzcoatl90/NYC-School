package com.itz.corp.feature.sat.dagger.module;

import androidx.lifecycle.MutableLiveData;

import com.itz.corp.feature.sat.model.SATResultNetworkData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SATResultModule {

    @Provides
    @Singleton
    MutableLiveData<SATResultNetworkData> provideSatResultLiveData() {
        return new MutableLiveData<>();
    }
}

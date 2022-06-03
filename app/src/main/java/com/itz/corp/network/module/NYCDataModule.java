package com.itz.corp.network.module;

import com.itz.corp.network.NYCDataAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NYCDataModule {

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    NYCDataAPI provideNYCDataAPI(Converter.Factory factory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYC_DATA_URL)
                .addConverterFactory(factory)
                .build();
        return retrofit.create(NYCDataAPI.class);
    }

    private final String NYC_DATA_URL = "https://data.cityofnewyork.us";
}

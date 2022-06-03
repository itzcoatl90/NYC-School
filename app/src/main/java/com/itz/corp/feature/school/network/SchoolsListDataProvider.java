package com.itz.corp.feature.school.network;

import com.itz.corp.feature.school.model.School;
import com.itz.corp.network.NYCDataAPI;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Callback;

/**
 * Providers in this architecture are only meant to provide the data, as in automated tests a
 * call to a remote API may be irrelevant, the calls are decoupled to make easy to mock or stub the data
 * and test the expected client behavior.
 *
 * Given more time, DataProviders would use generics as loadData parameter and inherit from a shared
 * abstraction, as a way to indicate they could provide the data from a different source other than
 * a remote backend (like a local database or shared preferences).
 * */
public class SchoolsListDataProvider {

    private final NYCDataAPI nycDataAPI;

    @Inject
    public SchoolsListDataProvider(NYCDataAPI nycDataAPI) {
        this.nycDataAPI = nycDataAPI;
    }

    public void loadData(Callback<List<School>> callback) {
        nycDataAPI.getSchoolList().enqueue(callback);
    }
}

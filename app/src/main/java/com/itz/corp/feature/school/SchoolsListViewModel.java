package com.itz.corp.feature.school;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itz.corp.feature.school.model.School;
import com.itz.corp.feature.school.model.SchoolsListNetworkData;
import com.itz.corp.feature.school.network.SchoolsListDataProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LiveData in this ViewModel is a wrapper that could potentially provide data or network errors.
 *
 * This is to avoid having 2 different channels for the view (one with data and one with errors)
 * which elevates complexity as the view would need to know somehow what was the latest update from
 * both channels (to display data or handle an error).
 *
 * Given more time, a proper error handler would be implemented decoupling the viewModel with any
 * networking reference (delegating it entirely to the data provider).
 *
 * Also, given more time, this class would have used generics and inherit from a shared abstraction with:
 * - LiveData<T<D, E>> getNetworkData()
 * - onResponse(D): T<D, E>
 * - onError(E): T<D, E>
 * - reloadData()
 * */
@Singleton
public class SchoolsListViewModel implements Callback<List<School>> {

    private MutableLiveData<SchoolsListNetworkData> schoolsListNetworkData;
    private SchoolsListDataProvider schoolsListDataProvider;

    @Inject
    public SchoolsListViewModel(MutableLiveData<SchoolsListNetworkData> schoolsListNetworkData,
                                SchoolsListDataProvider schoolsListDataProvider) {
        this.schoolsListNetworkData = schoolsListNetworkData;
        this.schoolsListDataProvider = schoolsListDataProvider;
        reloadData();
    }

    public LiveData<SchoolsListNetworkData> getNetworkData() {
        return schoolsListNetworkData;
    }

    @Override
    public void onResponse(Call<List<School>> call, Response<List<School>> response) {
        schoolsListNetworkData.postValue(new SchoolsListNetworkData(response.body(), null));
    }

    @Override
    public void onFailure(Call<List<School>> call, Throwable t) {
        schoolsListNetworkData.postValue(new SchoolsListNetworkData(null, t));
    }

    public void reloadData() {
        schoolsListDataProvider.loadData(this);
    }
}

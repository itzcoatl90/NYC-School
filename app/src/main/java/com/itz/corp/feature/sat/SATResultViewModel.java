package com.itz.corp.feature.sat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itz.corp.feature.sat.model.SATResult;
import com.itz.corp.feature.sat.model.SATResultNetworkData;
import com.itz.corp.feature.sat.network.SATResultDataProvider;

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
public class SATResultViewModel implements Callback<List<SATResult>> {

    private MutableLiveData<SATResultNetworkData> satResultNetworkData;
    private SATResultDataProvider satResultDataProvider;

    @Inject
    public SATResultViewModel(MutableLiveData<SATResultNetworkData> satResultNetworkData,
                              SATResultDataProvider satResultDataProvider) {
        this.satResultNetworkData = satResultNetworkData;
        this.satResultDataProvider = satResultDataProvider;
    }

    public LiveData<SATResultNetworkData> getNetworkData() {
        return satResultNetworkData;
    }

    @Override
    public void onResponse(Call<List<SATResult>> call, Response<List<SATResult>> response) {
        /**
         * Despite we just request a single SAT result, API is responding with a list,
         * so we need to extract the only value it has. That fact cannot be changed from the client
         * side, which means we need that ugly ".get(0)" that you are seeing here.
         * */
        assert response.body() != null;
        if (response.body().isEmpty()) {
            onFailure(call, new Exception("Empty response was retrieved."));
        } else {
            satResultNetworkData.postValue(new SATResultNetworkData(response.body().get(0), null));
        }
    }

    @Override
    public void onFailure(Call<List<SATResult>> call, Throwable t) {
        satResultNetworkData.postValue(new SATResultNetworkData(null, t));
    }

    public void reloadData(String schoolDbn) {
        satResultDataProvider.loadData(schoolDbn, this);
    }
}

package com.itz.corp.network;

import com.itz.corp.feature.sat.model.SATResult;
import com.itz.corp.feature.school.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYCDataAPI {

    @GET("/resource/s3k6-pzi2.json")
    Call<List<School>> getSchoolList();

    @GET("/resource/f9bf-2cp4.json")
    Call<List<SATResult>> getSATResults(@Query("dbn") String schoolDbn);

}

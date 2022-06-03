package com.itz.corp.feature.school.model;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Given more time, this network data wrapper class would use
 * generics (for the data) and inherit from a shared abstraction.
 *
 * A toString was added for debugging purposes.
 * */
public class SchoolsListNetworkData {
    @Nullable private List<School> schoolsList;
    @Nullable private Throwable error;

    public SchoolsListNetworkData(@Nullable List<School> schoolsList, @Nullable Throwable error) {
        this.schoolsList = schoolsList;
        this.error = error;
    }

    @Nullable
    public List<School> getData() {
        return schoolsList;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "SchoolsListNetworkData{" +
                "schoolsList=" + schoolsList +
                ", error=" + error +
                '}';
    }
}

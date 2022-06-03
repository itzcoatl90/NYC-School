package com.itz.corp.feature.sat.model;

import androidx.annotation.Nullable;

/**
 * Given more time, this network data wrapper class would use
 * generics (for the data) and inherit from a shared abstraction.
 *
 * A toString was added for debugging purposes.
 * */
public class SATResultNetworkData {
    @Nullable private final SATResult satResult;
    @Nullable private final Throwable error;

    public SATResultNetworkData(@Nullable SATResult satResult, @Nullable Throwable error) {
        this.satResult = satResult;
        this.error = error;
    }

    @Nullable
    public SATResult getData() {
        return satResult;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "SATResultNetworkData{" +
                "satResult=" + satResult +
                ", error=" + error +
                '}';
    }
}

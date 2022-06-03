package com.itz.corp.feature.sat.model;

import com.google.gson.annotations.SerializedName;

public class SATResult {
    private final String dbn;
    @SerializedName("school_name")
    private final String schoolName;
    @SerializedName("num_of_sat_test_takers")
    private final String numOfSatTestTakers;
    @SerializedName("sat_critical_reading_avg_score")
    private final String satCriticalReadingAvgScore;
    @SerializedName("sat_writing_avg_score")
    private final String satWritingAvgScore;
    @SerializedName("sat_math_avg_score")
    private final String satMathAvgScore;

    public SATResult(String dbn,
                     String schoolName,
                     String numOfSatTestTakers,
                     String satCriticalReadingAvgScore,
                     String satMathAvgScore,
                     String satWritingAvgScore) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.numOfSatTestTakers = numOfSatTestTakers;
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
        this.satMathAvgScore = satMathAvgScore;
        this.satWritingAvgScore = satWritingAvgScore;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    /**
     * Given more time, a builder pattern instead of public constructor would be added,
     * in order to make cleaner instance creation from network adapter, tests stub generators or
     * model duplication (with a toBuilder method).
     *
     * I added toString for debugging purposes.
     * */

    @Override
    public String toString() {
        return "SATResult{" +
                "dbn='" + dbn + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", numOfSatTestTakers='" + numOfSatTestTakers + '\'' +
                ", satCriticalReadingAvgScore='" + satCriticalReadingAvgScore + '\'' +
                ", satMathAvgScore='" + satMathAvgScore + '\'' +
                ", satWritingAvgScore='" + satWritingAvgScore + '\'' +
                '}';
    }
}

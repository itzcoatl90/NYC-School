package com.itz.corp.feature.school.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Given more time, this model would be parcelable instead of Serializable, as parceling has better
 * performance than serializing. We will also just send one between activities, so performance won't
 * be affected that much.
 * */
public class School implements Serializable {

    public static final String SCHOOL_KEY = "SCHOOL_KEY";

    private final String dbn;
    private final String location;
    private final String website;
    private final String city;
    @SerializedName("school_name")
    private final String schoolName;
    @SerializedName("phone_number")
    private final String phoneNumber;
    @SerializedName("school_email")
    private final String schoolEmail;
    @SerializedName("state_code")
    private final String stateCode;

    public School(String dbn,
                  String schoolName,
                  String location,
                  String phoneNumber,
                  String schoolEmail,
                  String website,
                  String city,
                  String stateCode) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.schoolEmail = schoolEmail;
        this.website = website;
        this.city = city;
        this.stateCode = stateCode;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public String getStateCode() {
        return stateCode;
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
        return "School{" +
                "dbn='" + dbn + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", schoolEmail='" + schoolEmail + '\'' +
                ", website='" + website + '\'' +
                ", city='" + city + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}

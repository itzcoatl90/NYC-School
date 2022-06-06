package com.itz.corp.feature.sat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.itz.corp.NYCApplication;
import com.itz.corp.databinding.ActivitySatResultBinding;
import com.itz.corp.error.handler.ErrorHandler;
import com.itz.corp.feature.sat.model.SATResult;
import com.itz.corp.feature.sat.model.SATResultNetworkData;
import com.itz.corp.feature.school.model.School;

import javax.inject.Inject;

/**
 * Given more time, activity's view would be just a container and custom views (or maybe fragments) would be used,
 * decoupling the view and the activities.
 * More UX features would be added to the view presentation...
 *  - A miniature static google map with the location
 *  - Thumbnail to the website
 *  - Using WebView for the website in-app
 *  - A back button (arrow) on the toolbar
 *  - Interesting themes
 *  - Reusing the same activity for both screens (either with views or fragments)
 *  - Transition animation between screens
 * */
public class SATResultActivity extends AppCompatActivity {

    @Inject SATResultViewModel satResultViewModel;
    private ActivitySatResultBinding binding;
    private School school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((NYCApplication) getApplicationContext()).applicationComponent.inject(this);
        super.onCreate(savedInstanceState);

        binding = ActivitySatResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolBar();

        school = (School) getIntent().getSerializableExtra(School.SCHOOL_KEY);
        displaySchoolInfo(school);

        satResultViewModel.getNetworkData().observe(this, getObserverAndDataBinder());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupToolBar() {
        setSupportActionBar(binding.appToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private Observer<SATResultNetworkData> getObserverAndDataBinder() {
        return satResultNetworkData -> {
            if (satResultNetworkData != null && satResultNetworkData.getData() != null) {
                displaySatInfo(satResultNetworkData.getData());
            } else if (satResultNetworkData != null && satResultNetworkData.getError() != null) {
                ErrorHandler.showErrorDialog(this, satResultNetworkData.getError());
            } else {
                ErrorHandler.showErrorDialog(this);
            }
        };
    }

    private void displaySchoolInfo(@Nullable School school) {
        if (school != null) {
            binding.dbn.setText(school.getDbn());
            binding.schoolName.setText(school.getSchoolName());
            binding.location.setText(school.getLocation());
            binding.phoneNumber.setText(school.getPhoneNumber());
            binding.schoolEmail.setText(school.getSchoolEmail());
            binding.goToWebsite.setOnClickListener(view -> navigateToWebsite());
        }
    }

    private void displaySatInfo(SATResult satResult) {
        binding.numOfSatTestTakers.setText(satResult.getNumOfSatTestTakers());
        binding.satCriticalReadingAvgScore.setText(satResult.getSatCriticalReadingAvgScore());
        binding.satWritingAvgScore.setText(satResult.getSatWritingAvgScore());
        binding.satMathAvgScore.setText(satResult.getSatMathAvgScore());
    }

    private void navigateToWebsite() {
        String url = !school.getWebsite().startsWith("http://") && !school.getWebsite().startsWith("https://") ?
                "http://" + school.getWebsite() : school.getWebsite();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}

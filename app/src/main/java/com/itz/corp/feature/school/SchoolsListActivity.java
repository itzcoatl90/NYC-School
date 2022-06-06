package com.itz.corp.feature.school;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.itz.corp.NYCApplication;
import com.itz.corp.databinding.ActivitySchoolsListBinding;
import com.itz.corp.error.handler.ErrorHandler;
import com.itz.corp.feature.sat.SATResultActivity;
import com.itz.corp.feature.sat.SATResultViewModel;
import com.itz.corp.feature.school.model.School;
import com.itz.corp.feature.school.model.SchoolsListNetworkData;
import com.itz.corp.feature.school.ui.SchoolListener;
import com.itz.corp.feature.school.ui.SchoolsListAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Given more time, activity's view would be just a container and custom views (or maybe fragments) would be used,
 * decoupling the view and the activities.
 * More UX features would be added to the view presentation...
 *  - An inside filtering system for the school list
 *  - Trigger a list update when swiping the recyclerview (over-scrolling, or swipe to refresh)
 *  - Click animation on clicked card view
 *  - Interesting themes
 *  - Reusing the same activity for both screens (either with views or fragments)
 *  - Transition animation between screens/views/fragments
 * */
public class SchoolsListActivity extends AppCompatActivity implements SchoolListener {

    @Inject SchoolsListViewModel schoolsListViewModel;
    @Inject SATResultViewModel satResultViewModel;
    private ActivitySchoolsListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((NYCApplication) getApplicationContext()).applicationComponent.inject(this);
        super.onCreate(savedInstanceState);

        binding = ActivitySchoolsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appToolbar);
        schoolsListViewModel.getNetworkData().observe(this, getObserverAndDataBinder());
    }

    @Override
    public void showSchoolDetails(School school) {
        satResultViewModel.reloadData(school.getDbn());
        Intent intent = new Intent(this, SATResultActivity.class);
        intent.putExtra(School.SCHOOL_KEY, school);
        startActivity(intent);
    }

    private Observer<SchoolsListNetworkData> getObserverAndDataBinder() {
        return schoolsListNetworkData -> {
            if (schoolsListNetworkData != null && schoolsListNetworkData.getData() != null) {
                displaySchoolListInfo(schoolsListNetworkData.getData());
            } else if (schoolsListNetworkData != null && schoolsListNetworkData.getError() != null) {
                ErrorHandler.showErrorDialog(this, schoolsListNetworkData.getError());
            } else {
                ErrorHandler.showErrorDialog(this);
            }
        };
    }

    private void displaySchoolListInfo(List<School> schoolList) {
        binding.recyclerView.setAdapter(new SchoolsListAdapter(schoolList, this));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
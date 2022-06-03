package com.itz.corp.dagger.component;

import com.itz.corp.feature.sat.SATResultActivity;
import com.itz.corp.feature.sat.dagger.module.SATResultModule;
import com.itz.corp.feature.school.SchoolsListActivity;
import com.itz.corp.feature.school.dagger.module.SchoolsListModule;
import com.itz.corp.network.module.NYCDataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NYCDataModule.class,
        SchoolsListModule.class,
        SATResultModule.class})
public interface ApplicationComponent {
    void inject(SchoolsListActivity schoolsListActivity);
    void inject(SATResultActivity satResultActivity);
}

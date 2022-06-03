package com.itz.corp;

import android.app.Application;

import com.itz.corp.dagger.component.ApplicationComponent;
import com.itz.corp.dagger.component.DaggerApplicationComponent;

public class NYCApplication extends Application {
    public ApplicationComponent applicationComponent =
            DaggerApplicationComponent.create();
}

# Architecture
This project is a simple app that uses:
 - Dagger2
 - MVVM Architecture Pattern
 - Retrofit
 - LiveData

An easy explanation of the architecture can be done per package

## dagger.component

This is the dagger component that lives in an application scope, the app is relatively small, so no subcomponents were needed.

Here is where activities that require injection are defined and the modules used to provide the dependencies.

## error.handler

More like containing an Utilery class, methods that can be used to feedback when an error is triggered are living here.

## feature

This is where the features are living, subpackages here are kind of similar as they follow similar feature-level architecture

### sat

This package has the required code for the second screen, so it can be categorized as a "feature". At this level, the activity and the view model can be found.

#### SATResultActivity

The activity uses dependency injection to receive it's view model and avoiding making a backend call every time the view is recreated.

It also uses view binding to display the information that receives from the view model and from the intent (using serialization).

It also routes errors to an appropiate error handler.

Additionaly, it also handles starting a web browser when the url button of the school is clicked on the view.

#### SATResultViewModel

The ViewModel wraps data and errors for simplicity, it calls the data provider to trigger the backend call when needed.

It also keeps cached the lastest update from backend (either been data or error) for the view when it subscribes to it.

#### dagger.module

This package only contains the feature-level module that provides the liveData used for the SATResults updates

#### model

This package contains the raw data model (SATResult) and the wrapper where network errors are also hold.

#### network

This package contains the SATResult Data Provider, which is the class that handles pulling the data from the remote API.

It also passes the data to the callback, which is implemented within the viewmodel.

### school

Similar to Sat, this is another screen that can be categorized as another "feature".
It has a similar architecture explained in following subsections.

#### dagger.module

As in the sat package, it only contains the feature-level module for the liveData.

#### model

As in the set package, it contains the raw data model (School) and the wrapper where network errors are also hold.

#### network

As in the sat package, it contains the SchoolsList Data Provider that handles the backend call, pulling the data from the remote API and passing it to the viewModel, which also acts as the callback.

#### ui

For this feature, an adapter is required to display the list of schools pulled from the backend, it uses a recycler view and view binding. It requires a listener that the activity implements.

#### SchoolsListActivity

Similar to the sat activity, this one refers to the viewmodels to read and to trigger whenever data load is required (which is when the user clicks on a school). It also uses view binding and delegate display logic to the adapter.

#### SchoolsListViewModel

Similar to sat's viewmodel, wraps data and errors for the view to subscribe, uses the data provider to load the information that later keeps in cache for the view which can subscribe as many times as requires whenever it recreates.

## network

This is the app-level network package, it contains the required interface for retrofit, and required module for dagger.

## NYCApplication

This is the owned implementation of the application, the merely purpose of this one, is to hold the dependency graph for every activity that requires it.
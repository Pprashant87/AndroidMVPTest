package com.prashant.androidmvp.view.listener;

import com.prashant.androidmvp.models.Country;

/**
 * @author : Prashant P
 * @Name: MainView
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */


public interface MainView {

    void showProgress();

    void hideProgress();

    void updateData(Country CountryList);

    void displayErrorMessage(String mErrorMessage);
}

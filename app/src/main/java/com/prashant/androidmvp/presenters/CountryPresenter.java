package com.prashant.androidmvp.presenters;
/**
 * @author : Prashant P
 * @Name: CountryPresenter
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.prashant.androidmvp.models.Country;
import com.prashant.androidmvp.utils.AppConstants;
import com.prashant.androidmvp.utils.helper.VolleyRequestHelper;
import com.prashant.androidmvp.view.listener.MainView;
import com.prashant.androidmvp.view.listener.OnRequestCompletedListener;

public class CountryPresenter {

    private static final String TAG = CountryPresenter.class.getSimpleName();
    private Context mContext;
    private MainView mMainView;
    private VolleyRequestHelper volleyRequestHelper;

    public CountryPresenter(Context mContext, MainView mMainView) {
        this.mContext = mContext;
        this.mMainView = mMainView;
        this.volleyRequestHelper = new VolleyRequestHelper(mContext, requestCompletedListener);
    }

    /* The request completed listener */
    private OnRequestCompletedListener requestCompletedListener = new OnRequestCompletedListener() {
        @Override
        public void onRequestCompleted(String requestName, boolean status,
                                       String response, String errorMessage) {

            if (mMainView != null) {
                mMainView.hideProgress();
            }
            if (status) {
                try {
                    Gson gson = new Gson();
                    Country mCountry = gson.fromJson(response, Country.class);
                    if (mMainView != null) {
                        mMainView.updateData(mCountry);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (mMainView != null) {
                    mMainView.displayErrorMessage(errorMessage);
                }

            }

        }
    };

    public void fetchData() {
        if (mMainView != null) {
            mMainView.showProgress();
        }

        if (volleyRequestHelper != null) {
            volleyRequestHelper.makeJsonObjectReqest(AppConstants.GET_REQUEST, AppConstants.BASE_URL);
        }
    }

    public void onDestroy() {
        mMainView = null;
    }

}

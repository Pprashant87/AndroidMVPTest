package com.prashant.androidmvp.view.fragments;
/**
 * @author : Prashant P
 * @Name: MainCountryFragment
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.prashant.androidmvp.R;
import com.prashant.androidmvp.models.Country;
import com.prashant.androidmvp.models.Row;
import com.prashant.androidmvp.presenters.CountryPresenter;
import com.prashant.androidmvp.view.adapters.CountryAdapter;
import com.prashant.androidmvp.view.listener.MainView;

import java.util.List;

public class MainCountryFragment extends Fragment implements MainView {

    private static final String TAG = MainCountryFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private CountryPresenter mCountryPresenter;
    private CountryAdapter mCountryAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.activity_main, container, false);
        setUpView(myFragmentView);
        return myFragmentView;
    }


    private void setUpView(View mView) {
        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCountryPresenter = new CountryPresenter(getActivity(), this);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                setUpFetchData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpFetchData();
    }

    private void setUpFetchData() {
        if (mCountryPresenter != null)
        {
            mCountryPresenter.fetchData();
        }
    }


    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void updateData(Country mCountryList) {
        clear();
        updateListView(mCountryList);
    }

    @Override
    public void displayErrorMessage(String mErrorMessage) {
        if (!TextUtils.isEmpty(mErrorMessage))
        {
            Toast.makeText(getActivity(),mErrorMessage,Toast.LENGTH_SHORT).show();
        }
    }

    private void updateListView(Country mCountryList)
    {
        if (mCountryList != null) {
            List<Row> mRowList = mCountryList.getRows();
            setTitleForActionBar(mCountryList.getTitle());
            if (mRowList != null) {
                mCountryAdapter = new CountryAdapter(getActivity(), mRowList);
                mRecyclerView.setAdapter(mCountryAdapter);
            }
        }
    }

    private void setTitleForActionBar(String mTitle)
    {
        if (!TextUtils.isEmpty(mTitle))
        {
            mToolbar.setTitle(mTitle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }

    public void clear()
    {
        mRecyclerView.setAdapter(null);
    }
}

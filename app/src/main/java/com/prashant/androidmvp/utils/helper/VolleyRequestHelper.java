package com.prashant.androidmvp.utils.helper;
/**
 * @author : Prashant P
 * @Name: ConnectivityReceiver
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.prashant.androidmvp.utils.AppConstants;
import com.prashant.androidmvp.utils.AppController;
import com.prashant.androidmvp.utils.Logger;
import com.prashant.androidmvp.view.listener.OnRequestCompletedListener;

import org.json.JSONObject;


/**
 * VolleyRequest.java
 * An helper class to executes the web service using the volley. Supported
 * Methods: GET and POST. By default it will be executed on POST method.
 */
public class VolleyRequestHelper {

    private static final String TAG = VolleyRequestHelper.class.getSimpleName();


    private Context context;
    private RequestQueue requestQueue;
    private OnRequestCompletedListener mRequestCompletedListener;

    public VolleyRequestHelper(Context context) {
        this.context = context;
    }


    /**
     * Used to call web service and get response as JSON using post method.
     *
     * @param context  - context of the activity.
     * @param callback - The callback reference.
     */
    public VolleyRequestHelper(Context context, OnRequestCompletedListener callback) {
        this.mRequestCompletedListener = callback;
        this.context = context;
    }


    /**
     * Making json object request
     */
    public void makeJsonObjectReqest(final String mRequestName, String mURL) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                mURL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.d(TAG, response.toString());
                        if (mRequestCompletedListener != null) {
                            mRequestCompletedListener.onRequestCompleted(mRequestName, true, response.toString(), null
                            );
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String errorResponse = "";
                try {
                    VolleyError responseError = new VolleyError(
                            new String(error.networkResponse.data));
                    try {
                        final JSONObject responseJson = new JSONObject(responseError.getMessage());
                        // Show Alert Information
                        errorResponse = responseJson.getString(AppConstants.API_MESSAGE);
                    } catch (Exception e) {
                        errorResponse = "Unknown";
                    }
                } catch (Exception e) {
                    Logger.e(TAG, e.getMessage());
                }
                if (mRequestCompletedListener != null)
                {
                    mRequestCompletedListener.onRequestCompleted(
                            mRequestName, false, null,
                            errorResponse);
                }

            }
        });

        // Cancelling request
        AppController.getInstance().getRequestQueue().cancelAll(mRequestName);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,mRequestName);


    }
}
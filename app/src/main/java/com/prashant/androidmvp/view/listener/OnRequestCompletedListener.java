package com.prashant.androidmvp.view.listener;
/**
 * @author : Prashant P
 * @Name: OnRequestCompletedListener
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */
public interface OnRequestCompletedListener {
        /**
         * Called when the String request has been completed.
         *
         * @param requestName  the String refers the request name
         * @param status       the status of the request either success or failure
         * @param response     the String response returns from the Webservice. It may be
         *                     null if request failed.
         * @param errorMessage the String refers the error message when request failed to
         *                     get the response
         */
        void onRequestCompleted(String requestName, boolean status,
                                String response, String errorMessage);

    }
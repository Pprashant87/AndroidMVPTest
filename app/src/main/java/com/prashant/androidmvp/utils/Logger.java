package com.prashant.androidmvp.utils;
/**
 * @author : Prashant P
 * @Name: Logger
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */
import android.util.Log;


public class Logger {
    private static final String LOG_PREFIX = "Logger";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    //Default constructor.
    private Logger() {
    }


    /**
     * D void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void d(final String tag, String message) {
        if (AppConstants.LOG_DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * D void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void d(final String tag, Throwable cause) {
        if (AppConstants.LOG_DEBUG) {
            Log.d(tag, tag, cause);
        }
    }

    /**
     * V void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void v(final String tag, String message) {
        if (AppConstants.LOG_DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * V void.
     *
     * @param tag     the tag
     * @param message the message
     * @param cause   the cause
     */
    public static void v(final String tag, String message, Throwable cause) {
        if (AppConstants.LOG_DEBUG) {
            Log.v(tag, message, cause);
        }
    }

    /**
     * I void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void i(final String tag, String message) {
        if (AppConstants.LOG_DEBUG) {
            Log.i(tag, message);
        }
    }

    /**
     * I void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void i(final String tag, Throwable cause) {
        if (AppConstants.LOG_DEBUG) {
            Log.i(tag, tag, cause);
        }
    }

    /**
     * W void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void w(final String tag, String message) {
        if (AppConstants.LOG_DEBUG) {
            Log.w(tag, message);
        }
    }

    /**
     * W void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void w(final String tag, Throwable cause) {
        if (AppConstants.LOG_DEBUG) {
            Log.w(tag, tag, cause);
        }
    }

    /**
     * E void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void e(final String tag, String message) {
        if (AppConstants.LOG_DEBUG) {
            Log.e(tag, message);
        }
    }

    /**
     * E void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void e(final String tag, Throwable cause) {
        if (AppConstants.LOG_DEBUG) {
            Log.e(tag, tag, cause);
        }
    }

}


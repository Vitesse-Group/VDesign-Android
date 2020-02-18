package com.vitesse.group.vghomedecor.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceUtil {
    private static final String TAG = PreferenceUtil.class.getSimpleName();
    private static final String FILE_NAME = "shared_pref";
    public static final String IS_FIRST_TIME = "IS_FIRST_TIME";
    public static final String IS_LOGIN_FIRST_TIME = "IS_LOGIN_FIRST_TIME";
    public static final String IS_LOGIN_REMEMBER_ME = "IS_LOGIN_FIRST_TIME";

    private static PreferenceUtil preferenceUtils = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor shareEditor;

    private PreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    public static PreferenceUtil getInstance(Context context) {
        if (preferenceUtils == null) {
            preferenceUtils = new PreferenceUtil(context);
        }
        return preferenceUtils;
    }

    public void saveBooleanParam(String key, boolean value) {
        shareEditor.putBoolean(key, value).commit();
    }


    public boolean getBooleanParam(String key) {
        return sharedPreferences.getBoolean(key, false);
    }








}

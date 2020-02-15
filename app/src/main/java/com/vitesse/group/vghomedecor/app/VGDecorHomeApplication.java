package com.vitesse.group.vghomedecor.app;

import android.app.Application;
import android.net.Uri;

/**
 * Created by baji_g on 1/6/2017.
 */

public class VGDecorHomeApplication extends Application {
    public static VGDecorHomeApplication mVgDecorHomeApplication;
    public String mLoginUserType;

    public Uri getmSavedimageUri() {
        return mSavedimageUri;
    }

    public void setmSavedimageUri(Uri mSavedimageUri) {
        this.mSavedimageUri = mSavedimageUri;
    }

    private Uri mSavedimageUri;

    public int getSelectedTabPosition() {
        return selectedTabPosition;
    }

    public void setSelectedTabPosition(int selectedTabPosition) {
        this.selectedTabPosition = selectedTabPosition;
    }

    private int selectedTabPosition;

    public int getmDecorationImagePosition() {
        return mDecorationImagePosition;
    }

    public void setmDecorationImagePosition(int mDecorationImagePosition) {
        this.mDecorationImagePosition = mDecorationImagePosition;
    }

    private int mDecorationImagePosition;




    @Override
    public void onCreate() {
        super.onCreate();
        VGDecorHomeApplication.mVgDecorHomeApplication = this;
    }

    public String getmLoginUserType() {
        return mLoginUserType;
    }

    public void setmLoginUserType(String mLoginUserType) {
        this.mLoginUserType = mLoginUserType;
    }

    public static VGDecorHomeApplication getInstance() {
        return mVgDecorHomeApplication;
    }
}

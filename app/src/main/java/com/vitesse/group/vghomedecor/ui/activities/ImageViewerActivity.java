package com.vitesse.group.vghomedecor.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.utils.PreferenceUtil;

/**
 * Created by baji_g on 4/6/2017.
 */

public class ImageViewerActivity extends Activity {

    /**
     * onCreate(Bundle) is where you initialize your activity. Most importantly, here you will usually
     * call setContentView(int) with a layout resource defining your UI, and using findViewById(int) to
     * retrieve the widgets in that UI that you need to interact with programmatically.
     * <p/>
     * When the application is launched for the first time this screen will be displayed.
     * The application will display the splash screen and build version also.
     *
     * @param savedInstanceState Constructs a new, empty Bundle.
     */
    private ImageView backBtn;
    private ImageView mainImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        mainImage = findViewById(R.id.saved_image);
        backBtn = findViewById(R.id.back_btn);

        Glide.with(this)
                .load(VGDecorHomeApplication.getInstance().getmSavedimageUri())
                .into(mainImage);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewerActivity.super.onBackPressed();
            }
        });


    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     */
    @Override
    protected void onResume() {
        super.onResume();

    }
}
package com.vitesse.group.vghomedecor.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.ui.activities.DecorationHomeActivity;
import com.vitesse.group.vghomedecor.ui.activities.HomeActivity;
import com.vitesse.group.vghomedecor.ui.activities.ImageViewerActivity;
import com.vitesse.group.vghomedecor.ui.adapters.KitchenTextureAdapter;
import com.vitesse.group.vghomedecor.ui.adapters.SavedImagesAdapter;
import com.vitesse.group.vghomedecor.ui.adapters.SpinnerCustomAdapter;
import com.vitesse.group.vghomedecor.ui.autoscrollviewpager.ViewPagerAdapter;
import com.vitesse.group.vghomedecor.ui.models.ImageData;
import com.vitesse.group.vghomedecor.utils.Animations;
import com.vitesse.group.vghomedecor.utils.Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends androidx.fragment.app.Fragment {
    private ViewPager viewPager;
    private RecyclerView mSavedImagesRecycler;
    static final List<ImageData> mSavedImagesFromFile = new ArrayList<>();
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private RelativeLayout viewPagerLayout,savedImagesLayout,designNewLayout;
    private Spinner spinner;
    private TextView savedImageTitle;
    private LinearLayout smileyLayout,savedImageParentLayout;
    private int selectedPage = -1;
    private ImageView createNewdesignBtn;
    private ImageView savedImagesBtn;
    private SavedImagesAdapter savedImagesAdapter;
    private ImageView livingIcon,bedroomIcon,KitchenIcon;
    private String[] designNames={"Select Design Area","Living Room","Bed Room","Kitchen Room"};
    private int designImages [] = {0, R.drawable.hall_4_2, R.drawable.bedroom_3_5, R.drawable.kitchen_2_1};


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {


        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        mSavedImagesRecycler = view.findViewById(R.id.saved_images_recycler);
        viewPagerLayout = view.findViewById(R.id.viewPagerLayout);
        savedImagesLayout = view.findViewById(R.id.saved_images_layout);
        designNewLayout = view.findViewById(R.id.design_new_layout);
        sliderDotspanel = (LinearLayout) view.findViewById(R.id.SliderDots);
        createNewdesignBtn = (ImageView)view.findViewById(R.id.create_new_design_btn);
        savedImagesBtn = (ImageView)view.findViewById(R.id.saved_image_btn);
        savedImageTitle = view.findViewById(R.id.saved_image_title);
        smileyLayout = view.findViewById(R.id.smiley_layout);
        savedImageParentLayout = view.findViewById(R.id.saved_images_layout_parent);
        livingIcon = view.findViewById(R.id.living_icon);
        bedroomIcon = view.findViewById(R.id.bedroom_icon);
        KitchenIcon = view.findViewById(R.id.kitchen_icon);
        savedImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_in_from_left_right,R.anim.push_out_from_right_left);

            }
        });

        livingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(0);
                Intent intent = new Intent(getActivity(), DecorationHomeActivity.class);
                startActivity(intent);

            }
        });
        bedroomIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(1);
                Intent intent = new Intent(getActivity(), DecorationHomeActivity.class);
                startActivity(intent);

            }
        });
        KitchenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(2);
                Intent intent = new Intent(getActivity(), DecorationHomeActivity.class);
                startActivity(intent);

            }
        });
        /*createNewdesignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(selectedPage);
                Intent intent = new Intent(getActivity(), DecorationHomeActivity.class);
                startActivity(intent);

            }
        });*/

        loadSavedImages(Utility.directory);
        if(mSavedImagesFromFile.size()> 0){
            smileyLayout.setVisibility(View.GONE);
            savedImageParentLayout.setVisibility(View.VISIBLE);
            savedImagesAdapter = new SavedImagesAdapter(getContext(),HomeFragment.this, mSavedImagesFromFile);
            mSavedImagesRecycler.setAdapter(savedImagesAdapter);
            mSavedImagesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }else{
            smileyLayout.setVisibility(View.VISIBLE);
            savedImageParentLayout.setVisibility(View.GONE);
        }

        // Spinner element
        spinner = view.findViewById(R.id.spinner);
        spinner.setPopupBackgroundResource(R.color.bg_color);
        SpinnerCustomAdapter customAdapter=new SpinnerCustomAdapter(getContext(),designImages,designNames);
        spinner.setAdapter(customAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    selectedPage = -1;
                }else{
                    selectedPage = spinner.getSelectedItemPosition() -1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPage = -1;
            }
        });






        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) Utility.convertDpToPixel(24,VGDecorHomeApplication.getInstance()));

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
        updateViewParams();
    }

    private void updateViewParams() {
        int toolBarHeight = (int) Utility.convertDpToPixel(56,VGDecorHomeApplication.getInstance());
        int bottomNavHeight = (int) Utility.convertDpToPixel(56,VGDecorHomeApplication.getInstance());
        int stausBarHeight = (int) Utility.convertDpToPixel(56,VGDecorHomeApplication.getInstance());

        int height = Utility.getScreenHeight(VGDecorHomeApplication.getInstance()) ;
        int weight = (int) ((height - (toolBarHeight - bottomNavHeight - stausBarHeight))/3.6);
        Log.d("baji","weight"+weight);
        RelativeLayout.LayoutParams viewPagerLayoutParams = (RelativeLayout.LayoutParams) viewPagerLayout.getLayoutParams();
        viewPagerLayoutParams.height = weight;
        viewPagerLayout.setLayoutParams(viewPagerLayoutParams);

       viewPagerLayout.startAnimation(Animations.slideInLeft());

        RelativeLayout.LayoutParams viewPagerParams = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
       // viewPagerParams.height = (int) (weight - Utility.convertDpToPixel(36,VGDecorHomeApplication.getInstance()));
       // viewPagerParams.height = weight;
        viewPager.setLayoutParams(viewPagerParams);

        RelativeLayout.LayoutParams dotParams = (RelativeLayout.LayoutParams) sliderDotspanel.getLayoutParams();
      /*  dotParams.height = (int) Utility.convertDpToPixel(36,getActivity());
        dotParams.setMargins(0, (int) Utility.convertDpToPixel(4,getActivity()),0,2 * weight);
        sliderDotspanel.setLayoutParams(dotParams);*/
        sliderDotspanel.bringToFront();

        RelativeLayout.LayoutParams saveImagesParams = (RelativeLayout.LayoutParams) savedImagesLayout.getLayoutParams();
       // saveImagesParams.height = weight;
        savedImagesLayout.setLayoutParams(saveImagesParams);
        savedImagesLayout.startAnimation(Animations.slideInRight());

        RelativeLayout.LayoutParams designNewParams = (RelativeLayout.LayoutParams) designNewLayout.getLayoutParams();
       // designNewParams.height = weight;
        designNewLayout.setLayoutParams(designNewParams);
        designNewLayout.startAnimation(Animations.slideInLeft());
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }


    public static void loadSavedImages(File dir) {
        mSavedImagesFromFile.clear();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if(files != null && files.length >0) {
                for (File file : files) {
                    String absolutePath = file.getAbsolutePath();
                    String extension = absolutePath.substring(absolutePath.lastIndexOf("."));
                    if (extension.equals(".jpg")) {
                        loadImage(file);
                    }
                }
            }
        }
    }


    public static void loadImage(File file) {
        ImageData newItem = new ImageData();
        newItem.uri = Uri.fromFile(file);
        newItem.imageTag = "kitchen";
        mSavedImagesFromFile.add(0, newItem);
        Log.d("baji","===>"+newItem.uri);
        Log.d("baji","===>mSavedImages"+mSavedImagesFromFile.size());
    }

}

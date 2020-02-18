package com.vitesse.group.vghomedecor.ui.activities;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.ui.adapters.PageAdapter;
import com.vitesse.group.vghomedecor.ui.adapters.SpinnerCustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class DecorationHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem tabLivingArea;
    private TabItem tabBedroomArea;
    private TabItem tabKitchenArea;
    private int selectedPage;
    private boolean isFirstTime;
    private String[] designNames={"Select Design Area","Living Room","Bed Room","Kitchen Room"};
    private int designImages [] = {0, R.drawable.hall_4_2, R.drawable.bedroom_3_5, R.drawable.kitchen_2_1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration_dashboard);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        //setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabLivingArea = findViewById(R.id.tab_living_area);
        tabBedroomArea = findViewById(R.id.tab_bedroom_area);
        tabKitchenArea = findViewById(R.id.tab_kitchen_area);
        viewPager = findViewById(R.id.viewPager);



        if(!isFirstTime && VGDecorHomeApplication.getInstance().getSelectedTabPosition() == -1) {
            showAlertDialog();
        }else{
            pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(pageAdapter);
            viewPager.setCurrentItem(VGDecorHomeApplication.getInstance().getSelectedTabPosition());
        }
        //viewPager.setCurrentItem(2);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                selectedPage = tab.getPosition();
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(selectedPage);
                toolbar.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                        R.color.colorPrimary));
                tabLayout.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                        R.color.colorPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            R.color.colorPrimaryDark));
                }
              /*  if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(DecorationHomeActivity.this,
                                R.color.colorAccent));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(DecorationHomeActivity.this,
                                android.R.color.darker_gray));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(DecorationHomeActivity.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(DecorationHomeActivity.this,
                                R.color.colorPrimaryDark));
                    }
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DecorationHomeActivity.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.customview, viewGroup, false);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        // Spinner element
        final Spinner spinner = dialogView.findViewById(R.id.spinner);

        SpinnerCustomAdapter customAdapter=new SpinnerCustomAdapter(this,designImages,designNames);
        spinner.setAdapter(customAdapter);
        //spinner.setPopupBackgroundResource(R.drawable.button_round_with_orangebg);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    selectedPage = -1;
                }else{
                    selectedPage = spinner.getSelectedItemPosition() -1;
                   /* VGDecorHomeApplication.getInstance().setSelectedTabPosition(selectedPage);
                    pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(pageAdapter);
                    viewPager.setCurrentItem(selectedPage);*/
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPage = -1;
            }
        });


        final RadioButton livingRadio = dialogView.findViewById(R.id.radioButton_living);
        final RadioButton kitchenRadio = dialogView.findViewById(R.id.radioButton_kitchen);
        final RadioButton bedRoomRadio = dialogView.findViewById(R.id.radioButton_bedroom);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("baji","selected Page"+selectedPage);
                if(selectedPage != -1) {
                    VGDecorHomeApplication.getInstance().setSelectedTabPosition(selectedPage);
                    pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(pageAdapter);
                    viewPager.setCurrentItem(selectedPage);
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(),"Please select decoration Area",Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.show();
        isFirstTime = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

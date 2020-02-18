package com.vitesse.group.vghomedecor.ui.fragments;


import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.ui.adapters.LivingTextureAdapter;
import com.vitesse.group.vghomedecor.ui.models.TextureModel;
import com.vitesse.group.vghomedecor.utils.BitmapUtils;
import com.vitesse.group.vghomedecor.utils.Utility;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LivingAreaFragment extends Fragment {
    private static  String TAG = LivingAreaFragment.class.getSimpleName();
    private RecyclerView textureRecyclerView;
    private ArrayList<TextureModel> imageModelArrayList;
    private LivingTextureAdapter textureAdapter;
    private int [] mTextureImageList;
    private int[] mLivingAreaImageTextures = new int[]{R.drawable.living_texture_1, R.drawable.living_texture_2,R.drawable.living_texture_3,
            R.drawable.living_texture_4,R.drawable.living_texture_5,R.drawable.living_texture_6,R.drawable.living_texture_7,
            R.drawable.living_texture_8,R.drawable.living_texture_9,R.drawable.living_texture_10,R.drawable.living_texture_11,R.drawable.living_texture_12,
            R.drawable.living_texture_13,R.drawable.living_texture_14,R.drawable.living_texture_15,R.drawable.living_texture_16,R.drawable.living_texture_17,R.drawable.living_texture_18,
            R.drawable.living_texture_19,R.drawable.living_texture_20,R.drawable.living_texture_21,R.drawable.living_texture_22
    };

    private String[] myImageNameList = new String[]{"Apple","Mango" ,"Strawberry","Pineapple","Orange","Blueberry","Watermelon"};

    private int[] mLivingAreaImageStyles =  new int[]{R.drawable.hall_1_1, R.drawable.hall_1_2,R.drawable.hall_1_3, R.drawable.hall_1_4,R.drawable.hall_1_5,R.drawable.hall_1_6,
            R.drawable.hall_2_1, R.drawable.hall_2_2,R.drawable.hall_2_3, R.drawable.hall_2_4,R.drawable.hall_2_5,R.drawable.hall_2_6,
            R.drawable.hall_3_1, R.drawable.hall_3_2,R.drawable.hall_3_3, R.drawable.hall_3_4,R.drawable.hall_4_1,R.drawable.hall_4_2,
            R.drawable.hall_4_3,R.drawable.hall_4_4,R.drawable.hall_4_5,R.drawable.hall_4_6};
    private int [] mStylesImageList =  new int[]{R.drawable.hall_1_1,  R.drawable.hall_2_1,
            R.drawable.hall_3_1,R.drawable.hall_4_1};
    private View view;
    private ImageView mTextureMainImage;
    private LinearLayout mUndoRedoLayout;
    private ImageView mDecorationStyleImage;
    private ImageView mUndoImage;
    private ImageView mRedoImage;
    private ImageView mSave;
    private FragmentManager fragmentManager;
    private DecorationStylesDialogFragment decorationStylesDialogFragment;
    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private static Bitmap icon;
    private boolean hasChanges;
    private ArrayList<Integer> mUndoList = new ArrayList<>();
    private ArrayList<Integer> mRedoList = new ArrayList<>();

    public static LivingAreaFragment newInstance()
    {
        Bundle args = new Bundle();
        LivingAreaFragment fragment = new LivingAreaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_livingarea, container, false);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        mTextureMainImage = view.findViewById(R.id.living_image);
        textureRecyclerView = (RecyclerView) view.findViewById(R.id.texture_recycler);
        mUndoRedoLayout = view.findViewById(R.id.undo_redo_layout);
        mDecorationStyleImage = view.findViewById(R.id.textures);
        mUndoImage = view.findViewById(R.id.undo);
        mRedoImage = view.findViewById(R.id.redo);
        mSave = view.findViewById(R.id.save);
        fragmentManager = getActivity().getFragmentManager();
        // instance of the helper class

        mTextureImageList = mLivingAreaImageTextures;
        /*if(VGDecorHomeApplication.getInstance().getSelectedTabPosition() == Utility.TAB_LIVING_AREA_INDEX){
            Log.d("baji","fragment TAB_LIVING_AREA_INDEX");
            mTextureImageList = mLivingAreaImageTextures;
            mStylesImageList = mLivingAreaImageStyles;
        }else if(VGDecorHomeApplication.getInstance().getSelectedTabPosition() == Utility.TAB_BEDROOM_AREA_INDEX){
            Log.d("baji","fragment TAB_BEDROOM_AREA_INDEX");
            mTextureImageList = mBedRoomImageTextures;
            mStylesImageList = mBedRoomImageStyles;
        }else if(VGDecorHomeApplication.getInstance().getSelectedTabPosition() == Utility.TAB_KITCHEN_AREA_INDEX){
            Log.d("baji","fragment TAB_KITCHEN_AREA_INDEX");
            mTextureImageList = mKitchenAreaImageTextures;
            mStylesImageList = mKitchenAreaImageStyles;
        }
*/


        mDecorationStyleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decorationStylesDialogFragment = DecorationStylesDialogFragment.newInstance(LivingAreaFragment.this,mStylesImageList);
                FragmentManager manager =getActivity().getFragmentManager();
                decorationStylesDialogFragment.show(manager,TAG);
            }
        });
         icon = ((BitmapDrawable)mTextureMainImage.getDrawable()).getBitmap();
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check for the external storage permission
               // Utility.showAlertMessage();
                // show AlertDialog

               // alertDialogHelper.showAlertDialog("","Are you sure want to save image in Device","Ok","Cancel",1,false);
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // If you do not have permission, request it
                    requestPermissions(
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_STORAGE_PERMISSION);
                } else {
                    showSaveImageDialog(getContext());
                }

            }
        });
        mUndoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hasChanges){
                    if(mUndoList != null && mUndoList.size() > 1){
                      mRedoList.add(mUndoList.get(mUndoList.size() -1));
                      mUndoList.remove(mUndoList.size() - 1);
                        mTextureMainImage.setImageResource(mUndoList.get(mUndoList.size() - 1));
                      setUndoRedoBtnVisibility();
                    }
                }else{
                    Toast.makeText(getActivity(),"You have not changed any thing",Toast.LENGTH_LONG).show();
                }

            }
        });
        mRedoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasChanges){
                    if(mRedoList != null && mRedoList.size() >1 ){
                        mTextureMainImage.setImageResource(mRedoList.get(mRedoList.size() - 1));
                       mUndoList.add(mRedoList.get(mRedoList.size() - 1));
                        mRedoList.remove(mRedoList.size() - 1);

                        setUndoRedoBtnVisibility();
                    }
                }else{
                    Toast.makeText(getActivity(),"You have not changed any thing",Toast.LENGTH_LONG).show();
                }
            }
        });



        imageModelArrayList = fillTextures();
        textureAdapter = new LivingTextureAdapter(getContext(),LivingAreaFragment.this, mTextureImageList);
        textureRecyclerView.setAdapter(textureAdapter);
        textureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        updateViewParams();
        return view;
    }

    private void updateViewParams() {
        boolean isPortrait = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        RelativeLayout.LayoutParams mainImageParams = (RelativeLayout.LayoutParams) mTextureMainImage.getLayoutParams();
        mainImageParams.width =  isPortrait ? (int) ( Utility.getScreenWidth(getContext())): (int) ( Utility.getScreenWidth(getContext())) ;
        mainImageParams.height = isPortrait ? (int) (0.7 * Utility.getScreenHeight(getContext())) :(int) (  Utility.getScreenWidth(getContext()));
        mTextureMainImage.setLayoutParams(mainImageParams);

        RelativeLayout.LayoutParams undoRedoParams = (RelativeLayout.LayoutParams) mUndoRedoLayout.getLayoutParams();
        undoRedoParams.width =  isPortrait ? (int) (0.6 * Utility.getScreenWidth(getContext())) : (int) (0.4 * Utility.getScreenWidth(getContext()));
        undoRedoParams.height =  isPortrait ? (int) (0.065 * Utility.getScreenHeight(getContext())) : (int) (0.125 * Utility.getScreenHeight(getContext()));
        mUndoRedoLayout.setLayoutParams(undoRedoParams);

        RelativeLayout.LayoutParams textureRecycleParams = (RelativeLayout.LayoutParams) textureRecyclerView.getLayoutParams();
        textureRecycleParams.height =  isPortrait ? (int) (0.125 * Utility.getScreenHeight(getContext())) : (int) (0.25 * Utility.getScreenHeight(getContext()));
        textureRecyclerView.setLayoutParams(textureRecycleParams);
        Glide .with(this)
                .load(mLivingAreaImageStyles[0])
                .fitCenter()
                .placeholder(R.drawable.ic_login_bk)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(mTextureMainImage);
        setUndoRedoBtnVisibility();
        mUndoList.add(mStylesImageList[0]);
        mRedoList.add(mStylesImageList[0]);
    }

    private ArrayList<TextureModel> fillTextures() {

        ArrayList<TextureModel> list = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            TextureModel textureModel = new TextureModel();
            textureModel.setName(myImageNameList[i]);
           /* Glide .with(this)
                    .load(mLivingAreaImageTextures[i])
                    .fitCenter()
                    .placeholder(R.drawable.ic_login_bk)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                    .into(textureModel.setImage_drawable(););*/
            textureModel.setImage_drawable(mTextureImageList[i]);
            list.add(textureModel);
        }

        return list;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.menu_chats, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.action_chat) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }*/
        return true;
    }

    public void textureItemClicked(int adapterPosition) {
        hasChanges = true;
        mTextureMainImage.setImageResource(mLivingAreaImageStyles[adapterPosition]);
        icon = ((BitmapDrawable)mTextureMainImage.getDrawable()).getBitmap();
        mUndoList.add(mLivingAreaImageStyles[adapterPosition]);
        setUndoRedoBtnVisibility();
        Utility.dismissProgress();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Called when you request permission to read and write to external storage
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // If you get permission, launch the camera
                    showSaveImageDialog(getContext());
                } else {
                    // If you do not get permission, show a Toast
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    public void onStylesOkBtnClicked(int position) {
        hasChanges = true;
        textureRecyclerView.setAdapter(textureAdapter);
        //textureAdapter.notifyDataSetChanged();
        mTextureMainImage.setImageResource(mStylesImageList[position]);

        mUndoList.add(mStylesImageList[position]);
        setUndoRedoBtnVisibility();
        icon = ((BitmapDrawable)mTextureMainImage.getDrawable()).getBitmap();
                Utility.dismissProgress();

    }


    /**
     * Shows alert dialog with provided message.
     *
     *
     * @param context Application context.
     */
    public static void showSaveImageDialog(final Context context) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do you want to save the image");

            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Utility.showProgress(context,context.getString(R.string.saveimage_msg));
                    BitmapUtils.saveImage(context,icon,Utility.LIVING_IMAGE_TAG);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (final Exception e) {
            Log.d(LivingAreaFragment.class.getSimpleName(), "showAlertDialog(): Failed.", e);
        }
    }

    private void setUndoRedoBtnVisibility() {
        if (mUndoList.size() > 1) {
            mUndoImage.setColorFilter(Color.BLACK);
            mUndoImage.setEnabled(true);
        }else {
            mUndoImage.setColorFilter(Color.GRAY);
            mUndoImage.setEnabled(false);
        }

        if (mRedoList.size() > 1) {
            mRedoImage.setColorFilter(Color.BLACK);
            mRedoImage.setEnabled(true);
        }else {
            mRedoImage.setColorFilter(Color.GRAY);
            mRedoImage.setEnabled(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateViewParams();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

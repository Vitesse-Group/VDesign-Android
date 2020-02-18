package com.vitesse.group.vghomedecor.ui.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.utils.Utility;

public class DecorationStylesDialogFragment extends DialogFragment
{
    private static int[] mStylesImageList;

    private String[] mDecoStylesNames = new String[]{"Apple","Mango" ,"Strawberry","Pineapple","Orange","Blueberry","Watermelon"};
    private RecyclerView mStylesrecyclerView;
    private DecorationStylesAdapter decorationStylesAdapter;
    private Button mOkBtn,mCancelBtn;
    private Dialog dialog;
    private static Fragment mLivingAreaFragment;

    public static DecorationStylesDialogFragment newInstance(Fragment livingAreaFragment, int[] stylesImageList)
    {
        Bundle args = new Bundle();
        DecorationStylesDialogFragment fragment = new DecorationStylesDialogFragment();
        fragment.setArguments(args);
        mLivingAreaFragment = livingAreaFragment;
        mStylesImageList = stylesImageList;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
       /*  dialog = new Dialog(getActivity());
         dialog.setCancelable(false);
         dialog.setCanceledOnTouchOutside(false);
        int width = (int) (ViewGroup.LayoutParams.MATCH_PARENT - Utility.convertDpToPixel(48,getContext()));
        int height = (int) (ViewGroup.LayoutParams.MATCH_PARENT  - Utility.convertDpToPixel(48,getContext()));
        dialog.getWindow().setLayout(width, height);
        return dialog;*/
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.dialog_fragment_styles, container, false);

        mStylesrecyclerView = (RecyclerView)rootView.findViewById(R.id.mStylesRecyerID);
        mOkBtn = (Button) rootView.findViewById(R.id.dialog_buttonOk);
        mCancelBtn = (Button) rootView.findViewById(R.id.dialog_buttonCancel);
        decorationStylesAdapter = new DecorationStylesAdapter(getActivity(),mStylesImageList);
        mStylesrecyclerView.setHasFixedSize(true);
        mStylesrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mStylesrecyclerView.setAdapter(decorationStylesAdapter);

        boolean isPortrait = getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mStylesrecyclerView.getLayoutParams();
        layoutParams.height = isPortrait ? (int) (0.75 * Utility.getScreenHeight(getContext())) : (int) (0.6 * Utility.getScreenHeight(getContext()));
        mStylesrecyclerView.setLayoutParams(layoutParams);

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null && dialog.isShowing()){
                    dialog.cancel();
                    dialog.dismiss();
                }
                Utility.showProgress(getContext(),getString(R.string.progress_msg));
                if(mLivingAreaFragment instanceof LivingAreaFragment){
                    ((LivingAreaFragment) mLivingAreaFragment).onStylesOkBtnClicked(VGDecorHomeApplication.getInstance().getmDecorationImagePosition());
                }else  if(mLivingAreaFragment instanceof BedroomAreaFragment){
                    ((BedroomAreaFragment) mLivingAreaFragment).onStylesOkBtnClicked(VGDecorHomeApplication.getInstance().getmDecorationImagePosition());
                }else  if(mLivingAreaFragment instanceof KitchenAreaFragment){
                    ((KitchenAreaFragment) mLivingAreaFragment).onStylesOkBtnClicked(VGDecorHomeApplication.getInstance().getmDecorationImagePosition());
                }

            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dialog != null && dialog.isShowing()){
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        });


       // getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        boolean isPortrait = getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mStylesrecyclerView.getLayoutParams();
        layoutParams.height = isPortrait ? (int) (0.75 * Utility.getScreenHeight(getContext())) : (int) (0.6 * Utility.getScreenHeight(getContext()));
        mStylesrecyclerView.setLayoutParams(layoutParams);
    }
}
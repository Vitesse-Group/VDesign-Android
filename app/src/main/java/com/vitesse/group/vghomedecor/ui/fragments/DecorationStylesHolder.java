package com.vitesse.group.vghomedecor.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vitesse.group.vghomedecor.R;

public class DecorationStylesHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    ImageView styleImage;
    ImageView imageSelector;

    public DecorationStylesHolder(View itemView) {
        super(itemView);
        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        styleImage = (ImageView) itemView.findViewById(R.id.style_iv);
        imageSelector = (ImageView) itemView.findViewById(R.id.item_selector);
    }
}
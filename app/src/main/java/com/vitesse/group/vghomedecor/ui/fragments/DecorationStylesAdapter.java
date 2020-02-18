package com.vitesse.group.vghomedecor.ui.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;

public class DecorationStylesAdapter extends RecyclerView.Adapter<DecorationStylesHolder> {

    private Context mContext;
    private String[] mDecorationStylesNames;
    private int[] mDecorationStylesImages;
    public int selectedPosition = -1;

    public DecorationStylesAdapter(Context c,  int[] mStylesImageList) {
        this.mContext = c;
        //this.mDecorationStylesNames = decoStylesNames;
        this.mDecorationStylesImages = mStylesImageList;
    }

    //INITIALIE VH
    @Override
    public DecorationStylesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.texture_style_recycler_item,parent,false);
        DecorationStylesHolder holder = new DecorationStylesHolder(v);
        return holder;
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(final DecorationStylesHolder holder, int position) {
        holder.styleImage.setImageResource(mDecorationStylesImages[position]);
        if(selectedPosition == holder.getAdapterPosition()){
            holder.imageSelector.setVisibility(View.VISIBLE);
        }else{
            holder.imageSelector.setVisibility(View.GONE);
        }

        holder.styleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageSelector.setVisibility(View.VISIBLE);
                setSeletedPos(holder.getAdapterPosition());

            }
        });
    }

    private void setSeletedPos(int position){
        selectedPosition = position;
        VGDecorHomeApplication.getInstance().setmDecorationImagePosition(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDecorationStylesImages.length;
    }
}
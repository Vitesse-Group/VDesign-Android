package com.vitesse.group.vghomedecor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.ui.fragments.KitchenAreaFragment;
import com.vitesse.group.vghomedecor.ui.fragments.LivingAreaFragment;
import com.vitesse.group.vghomedecor.ui.models.TextureModel;
import com.vitesse.group.vghomedecor.utils.Utility;

import java.util.ArrayList;

public class KitchenTextureAdapter extends RecyclerView.Adapter<KitchenTextureAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private int[] imageModelArrayList;
    KitchenAreaFragment mLivingAreaFragment;
    MyViewHolder holder;
    private View view;
    private int selectedPosition = - 1 ;

    public KitchenTextureAdapter(Context context, KitchenAreaFragment livingAreaFragment, int[] imageModelArrayList){

        inflater = LayoutInflater.from(context);
        this.imageModelArrayList = imageModelArrayList;
        mLivingAreaFragment = livingAreaFragment;
    }

    @Override
    public KitchenTextureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         view = inflater.inflate(R.layout.texture_recycler_item, parent, false);
         holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final KitchenTextureAdapter.MyViewHolder holder, final int position) {

        holder.textureImageView.setImageResource(imageModelArrayList[position]);
        if(selectedPosition == position){
            holder.textureImageViewSelector.setVisibility(View.VISIBLE);
        }else{
            holder.textureImageViewSelector.setVisibility(View.GONE);
        }
        holder.textureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.showProgress(view.getContext(),view.getContext().getString(R.string.progress_msg));
                mLivingAreaFragment.textureItemClicked(holder.getAdapterPosition());
                setSelectedPosition(position);
            }
        });
    }

    private void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textureTitle;
        ImageView textureImageView;
        ImageView textureImageViewSelector;

        public MyViewHolder(View itemView) {
            super(itemView);

            textureTitle = (TextView) itemView.findViewById(R.id.tv);
            textureImageView = (ImageView) itemView.findViewById(R.id.iv);
            textureImageViewSelector = (ImageView) itemView.findViewById(R.id.item_selector);

        }

    }
}
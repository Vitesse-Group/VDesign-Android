package com.vitesse.group.vghomedecor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.ui.fragments.HomeFragment;
import com.vitesse.group.vghomedecor.ui.fragments.KitchenAreaFragment;
import com.vitesse.group.vghomedecor.ui.models.ImageData;
import com.vitesse.group.vghomedecor.utils.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavedImagesAdapter extends RecyclerView.Adapter<SavedImagesAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<ImageData> imageData = new ArrayList<>();
    HomeFragment mLivingAreaFragment;
    MyViewHolder holder;
    private View view;
    private int selectedPosition = 0 ;
    private Context mContext;

    public SavedImagesAdapter(Context context, HomeFragment livingAreaFragment, List<ImageData> imageModelArrayList){

        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.imageData = imageModelArrayList;
        mLivingAreaFragment = livingAreaFragment;
        VGDecorHomeApplication.getInstance().setmSavedimageUri(imageData.get(0).uri);
    }

    @Override
    public SavedImagesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         view = inflater.inflate(R.layout.saved_image_recycler_item, parent, false);
         holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SavedImagesAdapter.MyViewHolder holder, final int position) {

        String text = "Living";
        Glide.with(mContext)
                .load(imageData.get(holder.getAdapterPosition()).uri)
                .thumbnail(0.2f)// Uri of the picture
                .into(holder.textureImageView);
        if(selectedPosition == position){
            holder.textureImageViewSelector.setVisibility(View.VISIBLE);
        }else{
            holder.textureImageViewSelector.setVisibility(View.GONE);
        }
        if(imageData.get(holder.getAdapterPosition()).imageTag.contains(Utility.BEDROOM_IMAGE_TAG)){
            text = "Bedroom";
        }else if(imageData.get(holder.getAdapterPosition()).imageTag.contains(Utility.LIVING_IMAGE_TAG)){
            text = "Living";
        }else if(imageData.get(holder.getAdapterPosition()).imageTag.contains(Utility.KITCHEN_IMAGE_TAG)){
            text = "Kitchen";
        }
        holder.textureTitle.setText(text);
        holder.textureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Utility.showProgress(view.getContext(),view.getContext().getString(R.string.progress_msg));
               // mLivingAreaFragment.textureItemClicked(holder.getAdapterPosition());
                setSelectedPosition(position);
            }
        });
    }

    private void setSelectedPosition(int position) {
        selectedPosition = position;
        VGDecorHomeApplication.getInstance().setmSavedimageUri(imageData.get(selectedPosition).uri);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageData.size();
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
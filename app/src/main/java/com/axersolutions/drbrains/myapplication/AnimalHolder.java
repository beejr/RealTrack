package com.axersolutions.drbrains.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalHolder extends RecyclerView.ViewHolder {

    private TextView camera_name,txt_distance;
    private ImageView camera_icon,animal_icon;


    public AnimalHolder(@NonNull View itemView) {
        super(itemView);

        camera_icon = (ImageView) itemView.findViewById(R.id.camera_icon);
        animal_icon = (ImageView)itemView.findViewById(R.id.animal_icon);

        camera_name = (TextView)itemView.findViewById(R.id.camera_name);
        txt_distance = (TextView)itemView.findViewById(R.id.txtDistance);


    }

    public void setDetails(AnimalData animalData){
        camera_name.setText(animalData.getCamera_name());
        camera_icon.setImageResource(R.drawable.newred);
    }
}

package com.axersolutions.drbrains.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AnimalHolder extends RecyclerView.ViewHolder {

    private TextView camera_name,txt_distance;
    private ImageView camera_icon,animal_icon1, animal_icon2, animal_icon3,animal_icon4;
    private LinearLayout image_layout;

    public AnimalHolder(@NonNull View itemView) {
        super(itemView);

        image_layout = itemView.findViewById(R.id.image_layout);
        camera_icon = (ImageView) itemView.findViewById(R.id.camera_icon);
        animal_icon1 = (ImageView)itemView.findViewById(R.id.animal_icon1);
        animal_icon2 = (ImageView)itemView.findViewById(R.id.animal_icon2);
        animal_icon3 = (ImageView)itemView.findViewById(R.id.animal_icon3);
        animal_icon4 = (ImageView)itemView.findViewById(R.id.animal_icon4);

        camera_name = (TextView)itemView.findViewById(R.id.camera_name);
        txt_distance = (TextView)itemView.findViewById(R.id.txtDistance);


    }

    public void setDetails(AnimalData animalData,int position){

        List<AnimalData> animal_list_one = SplashActivity.animal_list_one;
        List<AnimalData> animal_list_two = SplashActivity.animal_list_two;
        camera_name.setText(animalData.getCamera_name());

        camera_icon.setImageResource(R.drawable.newred);

        if(position==0){

            int size = animal_list_one.size();

            for(int i=0;i<size;i++){

                Log.i("name", String.valueOf(animal_list_one.get(i).getAnimal_image()));

                final ImageView imageView = new ImageView(itemView.getContext());
                imageView.setLayoutParams(new LinearLayout.LayoutParams(160, 160)); // value is in pixels

  //              imageView.setImageDrawable(itemView.getResources().getDrawable(animal_list_one.get(i).getAnimal_image()));

                imageView.setImageResource(R.drawable.pawprint);
                image_layout.addView(imageView);
            }
        }
        else
            if(position==1){

                int size = animal_list_two.size();

                for(int i=0;i<size;i++){
                    final ImageView imageView = new ImageView(itemView.getContext());
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(160, 160)); // value is in pixels

                    //imageView.setImageDrawable(itemView.getResources().getDrawable(animal_list_two.get(i).getAnimal_image()));

                     imageView.setImageResource(R.drawable.pawprint);
                    image_layout.addView(imageView);
                }
            }




    }
}

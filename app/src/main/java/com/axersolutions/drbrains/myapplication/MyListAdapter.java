package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyListAdapter extends ArrayAdapter<AnimalData> {

    TextView animal_name,animal_status;
    List<AnimalData> animalList;
    //activity context
    Context context;
    //the layout resource file for the list items
    int resource;

    public MyListAdapter(Context context, int resource,List<AnimalData> animalList) {
        super(context, resource,animalList);
        this.context=context;
        this.resource=resource;
        this.animalList=animalList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView = view.findViewById(R.id.custom_image);
        animal_name = (TextView)view.findViewById(R.id.animalName);
        animal_status = (TextView)view.findViewById(R.id.status);

        AnimalData animalData = animalList.get(position);
        //adding values to the list item

        Log.i("id inside", String.valueOf(animalData.getAnimal_image()));
        imageView.setImageDrawable(context.getResources().getDrawable(animalData.getAnimal_image()));

        animal_name.setText(animalData.getAnimal_name());
        animal_status.setText(animalData.getCurrent_status());

        String animalname;
        animalname = animalData.getAnimal_name().toLowerCase();
        final int resourceIdone = context.getResources().getIdentifier(animalname, "drawable", context.getPackageName());

        SharedPreferences testprefs = context.getSharedPreferences("realtrack", MODE_PRIVATE);
        final int camera_id = testprefs.getInt("list_id", 1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
                Intent tracking_intent = new Intent(context, Tracking.class);

//              view.setAnimation(AnimationUtils.makeInAnimation(context,true));
                tracking_intent.putExtra("pos",position);
                tracking_intent.putExtra("i_am_from",2);



                tracking_intent.putExtra("cam_num",camera_id);


                tracking_intent.putExtra("animal",resourceIdone);
                  context.startActivity(tracking_intent);

            }
        });


        return view;
    }
}

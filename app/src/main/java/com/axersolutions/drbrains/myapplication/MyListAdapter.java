package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
    public View getView(int position, @Nullable View convertView,  @NonNull ViewGroup parent) {

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
        imageView.setImageDrawable(context.getResources().getDrawable(animalData.getAnimal_image()));
        animal_name.setText(animalData.getAnimal_name());
        animal_status. setText(animalData.getCurrent_status());

        return view;
    }
}

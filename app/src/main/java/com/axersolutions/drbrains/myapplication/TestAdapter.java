package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class TestAdapter extends ArrayAdapter<AnimalsCamera> {

    List<AnimalsCamera> animalcamera;
    //activity context
    Context context;
    //the layout resource file for the list items
    int resource;

    TextView animal_name,camera_name;

    public TestAdapter(Context context, int resource, List<AnimalsCamera> animalcamera) {
        super(context, resource);
        this.animalcamera=animalcamera;
        this.context=context;
        this.resource=resource;

    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view

        animal_name = (TextView)view.findViewById(R.id.animal_name);
        camera_name = (TextView)view.findViewById(R.id.camera_name);

        AnimalsCamera animalsCamera = animalcamera.get(position);

        animal_name.setText(animalsCamera.getAnimalname());
        camera_name.setText(animalsCamera.getCameraname());

        Log.i("animal",animalsCamera.getAnimalname());
        Log.i("animal",animalsCamera.getCameraname());
        return view;
    }
}

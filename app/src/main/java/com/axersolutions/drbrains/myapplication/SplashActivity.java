package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static List<AnimalData> animal_list_one =new ArrayList<>();
    public static List<AnimalData> animal_list_two =new ArrayList<>();
    public static List<AnimalData> animal_list_three =new ArrayList<>();
    public static List<String> animal_list =new ArrayList<>();
    public static List<String> camera_list =new ArrayList<>();


    public static ArrayList<AnimalData> animalDataArrayList=new ArrayList<>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();


    private String animal_name,animal_location,camera_name,camera_location,current_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        animalDataArrayList=new ArrayList<>();

        //Check Internet Connectivity
        boolean connected =false;
        boolean flag = true;
        ConnectivityManager conmgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = conmgr.getActiveNetworkInfo();
        connected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (connected == false) {
            Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
        }   //Insert data from Firebase to the Card



        createcard();

        final int SPLASH_DISPLAY_LENGTH = 2000; //splash screen will be shown for 5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                mainIntent.putExtra("check",0);
                startActivity(mainIntent);
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);




    }
    private void createcard() {

        animal_list_one = new ArrayList<>();
        animal_list_two = new ArrayList<>();
        animal_list_three = new ArrayList<>();
        animal_list = new ArrayList<>();

        myroof.child("Cameras").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    camera_name = String.valueOf(data.getValue());
                    AnimalData animalData = new AnimalData(" "," ",camera_name,"xyz"," ",R.drawable.pawprint);
                    animalDataArrayList.add(animalData);
                    Log.i("Camera List",camera_name);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        myroof.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                animal_list_one = new ArrayList<>();

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    animal_name = String.valueOf(data.child("name").getValue());
                    animal_location = String.valueOf(data.child("animal_location").getValue());
                    camera_name = String.valueOf(data.child("location").getValue());
                    camera_location = String.valueOf(data.child("cam_location").getValue());
                    current_status = String.valueOf(data.child("status").getValue());


                    animal_name = animal_name.toLowerCase();
                    Log.i("name here",animal_name+" is changed");
                    animal_name = animal_name.replaceAll("\\s+","");
                    Log.i("name here also",animal_name+" is changed");
                    int resourceIdone = getResources().getIdentifier(animal_name, "drawable", getPackageName());
                    Log.i("splashLogID", String.valueOf(resourceIdone));
                    animal_list.add(animal_name);

                    AnimalData animalData = new AnimalData(animal_name,animal_location,camera_name,camera_location,current_status,resourceIdone);
                    animal_list_one.add(animalData);
                    Log.i("Camera ONE",animal_name+" "+animal_location+" "+camera_name+" "+camera_location+" "+current_status);


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        myroof.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                animal_list_two = new ArrayList<>();

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    animal_name = String.valueOf(data.child("name").getValue());
                    animal_location = String.valueOf(data.child("animal_location").getValue());
                    camera_name = String.valueOf(data.child("location").getValue());
                    camera_location = String.valueOf(data.child("cam_location").getValue());
                    current_status = String.valueOf(data.child("status").getValue());

                    animal_name=animal_name.toLowerCase();
                    Log.i("name",animal_name);
                    animal_name = animal_name.replaceAll("\\s+","");
                    Log.i("name",animal_name);
                    int resourceIdtwo = getResources().getIdentifier(animal_name, "drawable", getPackageName());
                    Log.i("splashLogIDtwo", String.valueOf(resourceIdtwo));


                    AnimalData animalData = new AnimalData(animal_name,animal_location,camera_name,camera_location,current_status,resourceIdtwo);
                    animal_list_two.add(animalData);
                    animal_list.add(animal_name);
                    Log.i("Camera Two",animal_name+" "+animal_location+" "+camera_name+" "+camera_location+" "+current_status);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });





    }
}

package com.axersolutions.drbrains.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Tracking extends AppCompatActivity {

    int x;
    int y;
    int camera; //camera id requested to firebase dataase
    int anim;
    String animal_name,time,animal_location1,camera_name;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroot = database.getReference();

    //List<AnimalData> animalDataList = SplashActivity.animal_list_one;
    List<AnimalData> animalDataListselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

//        SharedPreferences testprefs = getSharedPreferences("realtrack", MODE_PRIVATE);
//        int camera_id = testprefs.getInt("list_id", 1);



        Intent trackingintent = getIntent();

        final int cam_number = trackingintent.getIntExtra("cam_num",0);
        final int animal_position = trackingintent.getIntExtra("pos",0);
        final int animal_tracks = trackingintent.getIntExtra("name",0);
        final int you_are_from = trackingintent.getIntExtra("i_am_from",1);



        Log.i("track", String.valueOf(animal_tracks));

        if(cam_number==0){
            animalDataListselected = SplashActivity.animal_list_one;
        }else if (cam_number==1)
        {
            animalDataListselected = SplashActivity.animal_list_two;

        }



//        String animalname;
//        animalname = animalData.getAnimal_name().toLowerCase();
//        final int resourceIdone = getApplicationContext().getResources().getIdentifier(animalname, "drawable", getPackageName());




        if(you_are_from==1){
            //implies you are from search suggestions page
            camera=cam_number;
            anim = animal_position;


        }
        else if(you_are_from==2){
            //implies you are from search camera list page
            camera=cam_number;
            anim = animal_position;

        }

        final AnimalData animalData = animalDataListselected.get(animal_position);



//        Log.i("pos",Integer.toString(animal_position));
/*
        SharedPreferences prefs = getSharedPreferences("realtrack", MODE_PRIVATE);
        int position = prefs.getInt("list_id", 1);*/


        myroot.child(Integer.toString(camera)).child(Integer.toString(anim)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    x = Integer.parseInt(String.valueOf(dataSnapshot.child("x").getValue()));
                    y = Integer.parseInt(String.valueOf(dataSnapshot.child("y").getValue()));
                    animal_name = String.valueOf(dataSnapshot.child("name").getValue());
                    time =  String.valueOf(dataSnapshot.child("time").getValue());
                    animal_location1 = String.valueOf(dataSnapshot.child("animal_location").getValue());


                    camera_name = String.valueOf(camera+1);


                Log.d("Invinsibe x",String.valueOf(x));
                    Log.d("Invinsibe y",String.valueOf(y));


                LinearLayout animal_tracking = (LinearLayout)findViewById(R.id.animal_tracks);

                animal_tracking.setVisibility(View.GONE);
                Log.d("Invinsibe","GONE");

                LinearLayout linearLayout00 = (LinearLayout)findViewById(R.id.action00);
                LinearLayout linearLayout01 = (LinearLayout)findViewById(R.id.action01);
                LinearLayout linearLayout02 = (LinearLayout)findViewById(R.id.action02);
                LinearLayout linearLayout03 = (LinearLayout)findViewById(R.id.action03);
                LinearLayout linearLayout10 = (LinearLayout)findViewById(R.id.action10);
                LinearLayout linearLayout11 = (LinearLayout)findViewById(R.id.action11);
                LinearLayout linearLayout12 = (LinearLayout)findViewById(R.id.action12);
                LinearLayout linearLayout13 = (LinearLayout)findViewById(R.id.action13);
                LinearLayout linearLayout20 = (LinearLayout)findViewById(R.id.action20);
                LinearLayout linearLayout21 = (LinearLayout)findViewById(R.id.action21);
                LinearLayout linearLayout22 = (LinearLayout)findViewById(R.id.action22);
                LinearLayout linearLayout23 = (LinearLayout)findViewById(R.id.action23);
                LinearLayout linearLayout30 = (LinearLayout)findViewById(R.id.action30);
                LinearLayout linearLayout31 = (LinearLayout)findViewById(R.id.action31);
                LinearLayout linearLayout32 = (LinearLayout)findViewById(R.id.action32);
                LinearLayout linearLayout33 = (LinearLayout)findViewById(R.id.action33);
                LinearLayout linearLayout40 = (LinearLayout)findViewById(R.id.action40);
                LinearLayout linearLayout41 = (LinearLayout)findViewById(R.id.action41);
                LinearLayout linearLayout42 = (LinearLayout)findViewById(R.id.action42);
                LinearLayout linearLayout43 = (LinearLayout)findViewById(R.id.action43);



                TextView status_res = (TextView)findViewById(R.id.status_res);
                TextView time_res = (TextView)findViewById(R.id.time_res);
                TextView camera_res = (TextView)findViewById(R.id.camera_res);
                TextView animal_location_res = (TextView)findViewById(R.id.animal_location_res);

                if(x == -1 || y == -1){
                    camera_res.setText("Unknown");

                }else{
                    camera_res.setAllCaps(true);
                    camera_res.setText(camera_name);

                }


                status_res.setAllCaps(true);
                status_res.setText(animal_name);


                time_res.setText(time);

                animal_location_res.setAllCaps(true);
                animal_location_res.setText(animal_location1);



                linearLayout00.setBackgroundResource(0);
                linearLayout01.setBackgroundResource(0);
                linearLayout02.setBackgroundResource(0);
                linearLayout03.setBackgroundResource(0);
                linearLayout10.setBackgroundResource(0);
                linearLayout11.setBackgroundResource(0);
                linearLayout12.setBackgroundResource(0);
                linearLayout13.setBackgroundResource(0);
                linearLayout20.setBackgroundResource(0);
                linearLayout21.setBackgroundResource(0);
                linearLayout22.setBackgroundResource(0);
                linearLayout23.setBackgroundResource(0);
                linearLayout30.setBackgroundResource(0);
                linearLayout31.setBackgroundResource(0);
                linearLayout32.setBackgroundResource(0);
                linearLayout33.setBackgroundResource(0);
                linearLayout40.setBackgroundResource(0);
                linearLayout41.setBackgroundResource(0);
                linearLayout42.setBackgroundResource(0);
                linearLayout43.setBackgroundResource(0);


/* ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
        imageView.setImageResource(R.drawable.chimpanzee);
        linearLayout.addView(imageView);
*/

                if(x == 0)
                {
                    animal_tracking.setVisibility(View.VISIBLE);
                    Log.d("Invinsibe","VISIBLE x");

                    if(y == 0){
                            linearLayout00.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                        //linearLayout00.setBackgroundResource(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                        //linearLayout00.setBackgroundResource(R.drawable.paw);

                    }
                    else if(y == 1){

                        Log.d("Invinsibe","VISIBLE y");
                        linearLayout01.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                      }
                    else if(y == 2){
                        linearLayout02.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                       }
                    else if(y ==3){
                        linearLayout03.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                        }

                }
                else if(x == 1)
                {

                    Log.d("Invinsibe 1","VISIBLE y");

                    animal_tracking.setVisibility(View.VISIBLE);
                    if(y == 0){
                        linearLayout10.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                       }
                    else if(y == 1){
                        linearLayout11.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                        }
                    else if(y == 2){
                        Log.d("Invinsibe 2","VISIBLE y");
                        linearLayout12.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                       }
                    else if(y ==3){
                        linearLayout13.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                       }

                }
                else if(x == 2)
                {

                    animal_tracking.setVisibility(View.VISIBLE);
                    if(y == 0){
                        linearLayout20.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));
                        ;}
                    else if(y == 1){linearLayout21.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y == 2){linearLayout22.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y ==3){linearLayout23.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}

                }
                else if(x == 3)
                {

                    animal_tracking.setVisibility(View.VISIBLE);
                    if(y == 0){linearLayout30.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y == 1){linearLayout31.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y == 2){linearLayout32.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y ==3){linearLayout33.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}

                }
                else if(x == 4)
                {

                    animal_tracking.setVisibility(View.VISIBLE);
                    if(y == 0){linearLayout40.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y == 1){linearLayout41.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y == 2){linearLayout42.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}
                    else if(y ==3){linearLayout43.setBackground(getApplicationContext().getResources().getDrawable(animalData.getAnimal_image()));}

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}



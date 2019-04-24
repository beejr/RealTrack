package com.axersolutions.drbrains.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimalAdapter adapter;
    private ArrayList<AnimalData> animalDataArrayList;
    private EditText search;
    private String animal_name,animal_location,camera_name,camera_location,current_status;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_left);
       // search = (EditText)findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.startAnimation(animation);

        animalDataArrayList = new ArrayList<>();
   //       cam1 = new ArrayList<>();
   //     cam2 = new ArrayList<>();

        adapter = new AnimalAdapter(this,animalDataArrayList);
        createcard();

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
/*
        myroof.child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    String animal_name = String.valueOf(data.child("name").getValue());
                    String animal_location = String.valueOf(data.child("animal_location").getValue());
                    String camera_name = String.valueOf(data.child("location").getValue());
                    String camera_location = String.valueOf(data.child("cam_location").getValue());
                    String current_status = String.valueOf(data.child("status").getValue());

                    // datalist(animal_name,animal_location,camera_name,camera_location,current_status,position);
                    Log.e("czxcv",animal_name+" "+animal_location+" "+camera_name+" "+camera_location+" "+current_status);


                }


                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
    }

    private void createcard() {
        AnimalData animalData = new AnimalData(animal_name,animal_location,"Camera ONE",camera_location,current_status,R.drawable.tiger);
        animalDataArrayList.add(animalData);
        AnimalData animalData1 = new AnimalData(animal_name,animal_location,"Camera TWO",camera_location,current_status,R.drawable.tiger);
        animalDataArrayList.add(animalData1);


    }

}

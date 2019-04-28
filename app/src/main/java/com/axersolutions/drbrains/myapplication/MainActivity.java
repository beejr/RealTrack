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
    private EditText search;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<AnimalData> animalDataArrayList = SplashActivity.animalDataArrayList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_left);
        // search = (EditText)findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.startAnimation(animation);


        adapter = new AnimalAdapter(this, animalDataArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

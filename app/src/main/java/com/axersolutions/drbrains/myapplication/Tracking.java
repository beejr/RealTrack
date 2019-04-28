package com.axersolutions.drbrains.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tracking extends AppCompatActivity {

    int x;
    int y;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroot = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);



        myroot.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    x = Integer.parseInt(String.valueOf(dataSnapshot.child("0").child("x").getValue()));
                    y = Integer.parseInt(String.valueOf(dataSnapshot.child("0").child("y").getValue()));

                    Log.d("Invinsibe x",String.valueOf(x));
                    Log.d("Invinsibe y",String.valueOf(y));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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


/* ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
        imageView.setImageResource(R.drawable.chimpanzee);
        linearLayout.addView(imageView);
*/

        if(x == 0)
        {

            animal_tracking.setVisibility(View.VISIBLE);
            Log.d("Invinsibe","VISIBLE x");

            if(y == 0){linearLayout00.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 1){

                Log.d("Invinsibe","VISIBLE y");
                linearLayout01.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 2){linearLayout02.setBackgroundResource(R.drawable.popuplogo);}
            else if(y ==3){linearLayout03.setBackgroundResource(R.drawable.popuplogo);}

        }
        else if(x == 1)
        {

            Log.d("Invinsibe 1","VISIBLE y");

            animal_tracking.setVisibility(View.VISIBLE);
            if(y == 0){linearLayout10.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 1){linearLayout11.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 2){
                Log.d("Invinsibe 2","VISIBLE y");

                linearLayout12.setBackgroundResource(R.drawable.popuplogo);}
            else if(y ==3){linearLayout13.setBackgroundResource(R.drawable.popuplogo);}

        }
        else if(x == 2)
        {

            animal_tracking.setVisibility(View.VISIBLE);
            if(y == 0){linearLayout20.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 1){linearLayout21.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 2){linearLayout22.setBackgroundResource(R.drawable.popuplogo);}
            else if(y ==3){linearLayout23.setBackgroundResource(R.drawable.popuplogo);}

        }
        else if(x == 3)
        {

            animal_tracking.setVisibility(View.VISIBLE);
            if(y == 0){linearLayout30.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 1){linearLayout31.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 2){linearLayout32.setBackgroundResource(R.drawable.popuplogo);}
            else if(y ==3){linearLayout33.setBackgroundResource(R.drawable.popuplogo);}

        }
        else if(x == 4)
        {

            animal_tracking.setVisibility(View.VISIBLE);
            if(y == 0){linearLayout40.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 1){linearLayout41.setBackgroundResource(R.drawable.popuplogo);}
            else if(y == 2){linearLayout42.setBackgroundResource(R.drawable.popuplogo);}
            else if(y ==3){linearLayout43.setBackgroundResource(R.drawable.popuplogo);}

        }
    }
}



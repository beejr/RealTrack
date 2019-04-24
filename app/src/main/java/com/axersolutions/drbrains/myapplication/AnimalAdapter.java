package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalHolder> {


    MyListAdapter adapter1;
    MyListAdapter adapter2;




    private Context context;
    private ArrayList<AnimalData> animalDataArrayList;
    PopupWindow popupWindow;
    ListView listView;
    TextView nofofanimals,noofanimals_result;

    TextView camera,name;
    LinearLayout linearLayout ;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();

    String animal_name,animal_location,camera_name,camera_location,current_status;

    public AnimalAdapter(Context context, ArrayList<AnimalData> animal) {
        this.context = context;
        this.animalDataArrayList = animal;
    }

    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewholder = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);

        linearLayout = (LinearLayout)viewholder.findViewById(R.id.image_layout);


        Log.i("viewcount","this comes twice");

        return new AnimalHolder(viewholder);

    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalHolder animalHolder, final int position) {

        List<AnimalData> animal_list = MainActivity.animal_list;
        AnimalData animalData = animalDataArrayList.get(position);
        animalHolder.setDetails(animalData);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup,null);

        listView=(ListView)popupView.findViewById(R.id.list);

        nofofanimals = (TextView)popupView.findViewById(R.id.noofanimals);
        noofanimals_result = (TextView)popupView.findViewById(R.id.noofanimals_result);

        myroof.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              int  childcount = (int) dataSnapshot.child("1").getChildrenCount();
                Log.d("childcount",Integer.toString(childcount));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









        adapter1 = new MyListAdapter(popupView.getContext(),R.layout.custom_listview,animal_list);
        listView.setAdapter(adapter1);
        Log.e("ptag0inside","inside0");

         animalHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
              public void onClick(View view) {

                int i = animalHolder.getAdapterPosition();
                Log.e("pri", String.valueOf(animalHolder.getAdapterPosition()));
//                Log.e("prija", String.valueOf(animalHolder.getItemId()));
//                Log.e("prijaasd", String.valueOf(animalHolder.getPosition()));
//                Log.e("prijaasdqwe", String.valueOf(animalHolder.getLayoutPosition()));





                 popupView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.myanim));
                 // create the popup window
                 // lets taps outside the popup also dismiss it
                 boolean focusable = true;
                 popupWindow = new PopupWindow(popupView,1000,1400,focusable);
                 //animation for the popup window
                 popupWindow.setAnimationStyle(R.anim.myanim);
                 //display the popup window
                 popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                 popupWindow.setOutsideTouchable(false);
                }
            });

        }


 //   private void datalist(String animal_name, String animal_location, String camera_name, String camera_location, String current_status, int position) {}

    @Override
    public int getItemCount() {
        return animalDataArrayList.size();
    }


}

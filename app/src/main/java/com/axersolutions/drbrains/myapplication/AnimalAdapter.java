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

    private Context context;
    private ArrayList<AnimalData> animalDataArrayList;
    PopupWindow popupWindow;
    ListView listView;
    TextView nofofanimals,noofanimals_result;

    TextView camera,name;
    List<AnimalData> animal_list,cam1,cam2;
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

        return new AnimalHolder(viewholder);

    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalHolder animalHolder, final int position) {

        AnimalData animalData = animalDataArrayList.get(position);
        animalHolder.setDetails(animalData);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup,null);

        listView=(ListView)popupView.findViewById(R.id.list);

        nofofanimals = (TextView)popupView.findViewById(R.id.noofanimals);
        noofanimals_result = (TextView)popupView.findViewById(R.id.noofanimals_result);
        animal_list = new ArrayList<>();
        cam1 = new ArrayList<>();
        cam2 = new ArrayList<>();
             myroof.child("1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren()){

                        animal_name = String.valueOf(data.child("name").getValue());
                        animal_location = String.valueOf(data.child("animal_location").getValue());
                        camera_name = String.valueOf(data.child("location").getValue());
                        camera_location = String.valueOf(data.child("cam_location").getValue());
                        current_status = String.valueOf(data.child("status").getValue());
                        AnimalData animalData = new AnimalData(animal_name,animal_location,camera_name,camera_location,current_status,R.drawable.cheetah);
                        cam2.add(animalData);
                        Log.e("ptag",animal_name+" "+animal_location+" "+camera_name+" "+camera_location+" "+current_status);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        AnimalData animalDatanew = new AnimalData("antelope","aaavo","anganeonnula","ivde","detecting",R.drawable.deer);
        cam1.add(animalDatanew);

        animalDatanew = new AnimalData("elephant","h,mmmm","ammmee","jaaani","detect",R.drawable.elephant);
        cam1.add(animalDatanew);


        Log.e("ptag1",cam1.toString());

        Log.e("ptag2",cam2.toString());


         animalHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
              public void onClick(View view) {

                int i = animalHolder.getAdapterPosition();
                Log.e("pri", String.valueOf(animalHolder.getAdapterPosition()));
                Log.e("prija", String.valueOf(animalHolder.getItemId()));
                Log.e("prijaasd", String.valueOf(animalHolder.getPosition()));
                Log.e("prijaasdqwe", String.valueOf(animalHolder.getLayoutPosition()));

              if(i == 0){
                    MyListAdapter myListAdapter = new MyListAdapter(popupView.getContext(),R.layout.custom_listview,cam1);
                    listView.setAdapter(myListAdapter);
                  Log.e("ptag0inside","inside0");



              }
                else if(i == 1) {
                   MyListAdapter myListAdapter = new MyListAdapter(popupView.getContext(),R.layout.custom_listview,cam1);
                   listView.setAdapter(myListAdapter);

                  //Log.e("ptag1inside","inside1");

                }
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

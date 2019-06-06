package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalHolder> {

    List<AnimalData> animal_list_one;
    List<AnimalData> animal_list_two;

    MyListAdapter adapter1;
    Context context;
    ArrayList<AnimalData> animalDataArrayList=SplashActivity.animalDataArrayList;
    PopupWindow popupWindow;

    ListView listView,testlistview;

    TextView title,nofofanimals,noofanimals_result;

    TextView camera,name;
    LinearLayout linearLayout ;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();


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

        animal_list_one = SplashActivity.animal_list_one;
        animal_list_two = SplashActivity.animal_list_two;

        AnimalData animalData = animalDataArrayList.get(position);
        animalHolder.setDetails(animalData,position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup,null);

        listView=(ListView)popupView.findViewById(R.id.list);
        testlistview=(ListView)popupView.findViewById(R.id.list1);

        title = (TextView)popupView.findViewById(R.id.title);
        nofofanimals = (TextView)popupView.findViewById(R.id.noofanimals);
        noofanimals_result = (TextView)popupView.findViewById(R.id.noofanimals_result);

        adapter1 = new MyListAdapter(popupView.getContext(),R.layout.custom_listview,animal_list_one);
        listView.setAdapter(adapter1);
        adapter1 = new MyListAdapter(popupView.getContext(),R.layout.custom_listview,animal_list_two);
        testlistview.setAdapter(adapter1);
        testlistview.setVisibility(View.GONE);


        title.setText("Camera One");
        noofanimals_result.setText(String.valueOf(animal_list_one.size()));
        animalHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
          @Override
              public void onClick(View view) {
                    int i = animalHolder.getAdapterPosition();

                        Log.e("p","ivide ethi");
                        testlistview.setVisibility(View.GONE);

                        SharedPreferences.Editor editor = context.getSharedPreferences("realtrack", MODE_PRIVATE).edit();
                        editor.putInt("list_id", i);
                        editor.commit();


                     if (i == 1){
                        title.setText("Camera Two");
                        noofanimals_result.setText(String.valueOf(animal_list_two.size()));
                        listView.setVisibility(View.GONE);
                        testlistview.setVisibility(View.VISIBLE);

                         editor = context.getSharedPreferences("realtrack", MODE_PRIVATE).edit();
                        editor.putInt("list_id", i);
                        editor.commit();


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

     @Override
     public int getItemCount() {
        return animalDataArrayList.size();
    }




}

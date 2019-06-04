package com.axersolutions.drbrains.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AnimalAdapter adapter;
    private EditText search;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myroof = database.getReference();

    //public List<AnimalsCamera> animal_list =new ArrayList<>();

    List<AnimalsCamera> animal_list;

    //CODE FOR ANIMAL_VIEW_LIST_SEARCH
    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> list_adapter_animal,list_adapter_camera;

    TestAdapter mTestAdapter;
    // Search EditText
    EditText inputSearch;



    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    private String animal_name,animal_location,camera_name,camera_location,current_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final ArrayList<AnimalData> animalDataArrayList = SplashActivity.animalDataArrayList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);

       // CardView cardView_animal_list = findViewById(R.id.card_view_animal_list);





        //Code for animal list search

        final List<String> animals =SplashActivity.animal_list;

        final List<AnimalData> animalsdata_cam1 =SplashActivity.animal_list_one;
        final List<String> animals_cam1 = new ArrayList<>();
        for(int i=0;i<animalsdata_cam1.size();i++)
            animals_cam1.add(animalsdata_cam1.get(i).getAnimal_name());

        final List<AnimalData> animalsdata_cam2 =SplashActivity.animal_list_two;
        final List<String> animals_cam2 = new ArrayList<>();
        for(int i=0;i<animalsdata_cam2.size();i++)
            animals_cam2.add(animalsdata_cam2.get(i).getAnimal_name());


//        fruitList.toArray(new String[fruitList.size()]);

       //animal_list = SplashActivity.animal_list;
//       Log.i("t", String.valueOf(animal_list.size()));
  //      Log.i("t", String.valueOf(animal_list.isEmpty()));




        EditText st = findViewById(R.id.search_box);
        final LinearLayout animals_list_ll = findViewById(R.id.animal_list_ll);

        //populate animals_list_ll with "cards" of animal names

        for(int i=0; i<animals.size(); i++){
            final int index = i;
            //create a linear layout to hold a text view
            //this textview will hold the animal name, i

            LinearLayout parent = new LinearLayout(getApplicationContext());
            parent.setOrientation(LinearLayout.VERTICAL);

            animals_list_ll.addView(parent);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 20);
            parent.setPadding(15,20,15,20);

            parent.setLayoutParams(layoutParams);

            TextView tv = new TextView(getApplicationContext());
            tv.setText(animals.get(i));
            tv.setTextSize(25);

            parent.setBackgroundColor(Color.parseColor("#FFDFDFDF"));
            parent.setTag(animals.get(i).toLowerCase());

            parent.addView(tv);
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "clicked "+animals.get(index), Toast.LENGTH_SHORT).show();

                    int cam_number;
                    int pos_in_list;
                    if(animals_cam1.contains(animals.get(index))) {
                        cam_number = 0;
                        pos_in_list=animals_cam1.indexOf(animals.get(index));
                    }
                    else {
                        cam_number = 1;
                        pos_in_list=animals_cam2.indexOf(animals.get(index));
                    }
                    Log.i("cam number",cam_number+"");
                    Log.i("pos in list",pos_in_list+"");

                    String animalname;
                    animalname = animals.get(index);
                    int resourceIdone = getResources().getIdentifier(animalname, "drawable", getPackageName());

                    Intent tracking_intent = new Intent(getApplicationContext(), Tracking.class);

                    tracking_intent.putExtra("cam_num",cam_number);
                    tracking_intent.putExtra("pos",pos_in_list);
                    tracking_intent.putExtra("name",animalname);

                    tracking_intent.putExtra("i_am_from",1);

                    getApplicationContext().startActivity(tracking_intent);

                }
            });
        }

        //Implement dynamic search
        st.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getApplicationContext(), "text changed!", Toast.LENGTH_SHORT).show();
                //change s to lowercase
                String se = s.toString().toLowerCase();
                //get relevant results from animals into search_results
                List<String> search_results =new ArrayList<>();

                search_results.clear();
                for(int i=0;i<animals.size();i++){
                    if(animals.get(i).contains(se)) {
                        Log.i(animals.get(i)+" contains",se);
                        search_results.add(animals.get(i));
                    }
                }

                if(se.equals("")) {
                    search_results.clear();
                    for (int i = 0 ;i<animals.size();i++)
                        search_results.add(animals.get(i));
                }

                //Log search_results elements
                Log.i("Search results","---"+search_results.size()+"---");
                for (int i = 0 ;i<search_results.size();i++)
                    Log.i("-->",search_results.get(i));


                //repopulate animal_list_ll with relevant results
                final int childCount = animals_list_ll.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View v = animals_list_ll.getChildAt(i);
                    if(!search_results.contains(v.getTag().toString()))
                        v.setVisibility(View.GONE);
                    else
                        v.setVisibility(View.VISIBLE);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });





//        list_adapter_animal = new TestAdapter(this,R.layout.list_item,animal_list);
//        listView.setAdapter(list_adapter_animal);

        /*mTestAdapter = new TestAdapter(this,R.layout.list_item,animal_list);
        lv.setAdapter(mTestAdapter);
        lv.setBackgroundColor(Color.BLUE);
*/

        String camera_list[] = SplashActivity.camera_list.toArray(new String[SplashActivity.camera_list.size()]);

//        // Adding items to listview
/*
        list_adapter_camera = new ArrayAdapter<String>(this, R.layout.list_item, R.id.camera_name,camera_list);
        lv.setAdapter(list_adapter_camera);
*/





//REST CODE
        final LinearLayout camera_view_container = findViewById(R.id.camera_view_container);
        final LinearLayout animal_view_container = findViewById(R.id.animal_view_container);



        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_left);
        // search = (EditText)findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.startAnimation(animation);


        adapter = new AnimalAdapter(this, animalDataArrayList);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Switch simpleSwitch = (Switch) findViewById(R.id.toggle_view);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);

                //to reset the calendar



                if(isChecked){

                    animal_view_container.setVisibility(View.GONE);
                    camera_view_container.setVisibility(View.VISIBLE);


                }
                else
                {

                    camera_view_container.setVisibility(View.GONE);
                    animal_view_container.setVisibility(View.VISIBLE);

                }

            }
        });



    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            recreate();

        }
    });









    }

    public void refreshme(View view){
        Toast.makeText(this, "refresh aayi", Toast.LENGTH_SHORT).show();
        /*LinearLayout camera_view_container = findViewById(R.id.camera_view_container);
        //camera_view_container.removeAllViews();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        */
        this.recreate();
        RecyclerView camera_view_container = findViewById(R.id.recyclerView);
        Toast.makeText(this, "there are "+camera_view_container.getChildCount()+"childeren", Toast.LENGTH_SHORT).show();

        for(int index = 0; index<((ViewGroup)camera_view_container).getChildCount(); ++index) {
            View nextChild = ((ViewGroup)camera_view_container).getChildAt(index);
            if(index>2)
                camera_view_container.removeView(nextChild);

        }
    }
}

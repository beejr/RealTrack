package com.axersolutions.drbrains.myapplication;

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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.axersolutions.drbrains.myapplication.SplashActivity.animal_list_one;

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

        String animals[] = SplashActivity.animal_list.toArray(new String[SplashActivity.animal_list.size()]);
//        fruitList.toArray(new String[fruitList.size()]);

       //animal_list = SplashActivity.animal_list;
//       Log.i("t", String.valueOf(animal_list.size()));
  //      Log.i("t", String.valueOf(animal_list.isEmpty()));

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        list_adapter_animal = new ArrayAdapter<String>(this, R.layout.list_item, R.id.animal_name, animals);
        lv.setAdapter(list_adapter_animal);


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


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.list_adapter_animal.getFilter().filter(cs);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


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

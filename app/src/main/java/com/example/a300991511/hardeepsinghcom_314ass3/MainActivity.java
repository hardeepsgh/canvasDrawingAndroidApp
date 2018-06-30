package com.example.a300991511.hardeepsinghcom_314ass3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv ;
    ArrayAdapter mAdapter  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lv = (ListView) findViewById(R.id._exercises);
        ArrayList<String> initialList = new ArrayList<String>(); //load these

        initialList.add(getString(R.string.app_canvas));
        initialList.add(getString(R.string.app_animation));
        initialList.add(getString(R.string.app_earth_view));

        ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, Canvas.class);


                if (position == 1){
                     intent = new Intent(MainActivity.this, AnimationTabActivity.class);

                }else if (position == 2){
                    intent = new Intent(MainActivity.this,  EarthView.class);

                }
                startActivity(intent);
            }
        });
    }


}

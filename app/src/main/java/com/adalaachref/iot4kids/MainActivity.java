package com.adalaachref.iot4kids;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     FstFragment mapf=new FstFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.fr1, mapf).commit();









    }







    }


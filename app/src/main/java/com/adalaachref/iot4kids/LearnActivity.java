package com.adalaachref.iot4kids;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        ListFragment lf=new ListFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.fr3, lf).commit();




    }
}

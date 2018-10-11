package com.adalaachref.iot4kids;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 17/10/2017.
 */

public class CustomAdapter2 extends ArrayAdapter<String>  implements View.OnClickListener {

    int ressource;
    List<String> contacts;




    public CustomAdapter2(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        this.ressource = resource;
        this.contacts = objects;
    }
    @TargetApi(17)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String currentContact = contacts.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(ressource, parent, false);
        final TextView marque1 = (TextView) convertView.findViewById(R.id.marque2);

        marque1.setBackgroundResource(R.drawable.rounded_orange);

        marque1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);



        marque1.setText(currentContact.toString());
        marque1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    marque1.setX(event.getX());
                    marque1.setY(event.getY());

                }
                return false;
            }
        });




        return convertView;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);


    }
}

package com.adalaachref.iot4kids;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 17/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<String>  implements View.OnClickListener {

    int ressource;
    List<String> contacts;




    public CustomAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        this.ressource = resource;
        this.contacts = objects;
    }
    @TargetApi(17)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String currentContact = contacts.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(ressource, parent, false);
        final TextView marque1 = (TextView) convertView.findViewById(R.id.marque1);



        marque1.setText(currentContact.toString());
        //marque1.setBackgroundResource(R.drawable.rounded_blue);

        marque1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        marque1.setTextColor(Color.parseColor("#0000FF"));




        return convertView;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);



    }
}

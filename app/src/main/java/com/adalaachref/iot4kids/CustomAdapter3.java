package com.adalaachref.iot4kids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adalaachref.iot4kids.model.Document;

import java.util.List;

/**
 * Created by dell on 10/12/2017.
 */

public class CustomAdapter3  extends ArrayAdapter<Document> implements View.OnClickListener {

    int ressource;
    List<Document> docs;




    public CustomAdapter3(Context context, int resource, List<Document> objects) {
        super(context, resource, objects);

        this.ressource = resource;
        this.docs = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Document currentContact = docs.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(ressource, parent, false);
        TextView tvDoc = (TextView) convertView.findViewById(R.id.nomF);
        ImageView imgDoc = (ImageView) convertView.findViewById(R.id.imgF);

        tvDoc.setText(currentContact.getText());

       new DownloadImageTask(imgDoc).execute("http://192.168.101.1/iot4kids/"+currentContact.getImage());


        return convertView;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Document dataModel=(Document) object;

    }
}

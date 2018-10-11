package com.adalaachref.iot4kids;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocFragment extends Fragment {

    public TextView tvdoc1, tvdoc2,tvdoc3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvdoc1= (TextView) view.findViewById(R.id.tvdoc);
        tvdoc2= (TextView)view.findViewById(R.id.tvdoc2);
        tvdoc3= (TextView)view.findViewById(R.id.tvdoc3);

        tvdoc1.setText("Introduction");
        tvdoc2.setText("The internet of things (IoT) is a rapidly expanding technology that is shaping up to bring the next revolution in computing and information technologies.");
        tvdoc3.setText("It is an advanced automation and analytics system that exploit networking,sensing,big data,and artificial intelligence technollogy to deliver complete system for a product.The IoT is removing repetitive tasks or creating things that just weren't possible before,This tutorial explores the Key concepts of IoT and provide in detail information also");

    }
}

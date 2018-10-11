package com.adalaachref.iot4kids;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {


    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView board1=(TextView) getView().findViewById(R.id.tvboard1);
        ImageView dot1=(ImageView) getView().findViewById(R.id.dot1);
        ImageView dot2=(ImageView) getView().findViewById(R.id.dot2);
        ImageView dot3=(ImageView) getView().findViewById(R.id.dot3);
        ImageView dot4=(ImageView) getView().findViewById(R.id.dot4);
        ImageView dot5=(ImageView) getView().findViewById(R.id.dot5);
        ImageView dot6=(ImageView) getView().findViewById(R.id.dot6);
        ImageView dot7=(ImageView) getView().findViewById(R.id.dot7);
        ImageView dot8=(ImageView) getView().findViewById(R.id.dot8);



        board1.setText("In this chapter, we will learn about the different components on the Arduino board. We will study the Arduino UNO board because it is the most popular board in the Arduino board family. In addition, it is the best board to get started with electronics and coding. Some boards look a bit different from the one given below, but most Arduinos have majority of these components in common.");

        dot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment2 firstFragment = new BoardFragment2();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });
        dot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment1 firstFragment = new BoardFragment1();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });
        dot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment3 firstFragment = new BoardFragment3();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();

            }
        });
        dot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment4 firstFragment = new BoardFragment4();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });
        dot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment5 firstFragment = new BoardFragment5();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });
        dot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment6 firstFragment = new BoardFragment6();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });
        dot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment7 firstFragment = new BoardFragment7();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();

            }
        });
        dot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardFragment8 firstFragment = new BoardFragment8();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
            }
        });




    }
}

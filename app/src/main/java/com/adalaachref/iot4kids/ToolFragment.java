package com.adalaachref.iot4kids;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment {


    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tool, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       TextView tvtool1= (TextView) view.findViewById(R.id.tvtool1);
        TextView tvtool2= (TextView) view.findViewById(R.id.tvtool2);
        TextView tvtool3= (TextView) view.findViewById(R.id.tvtool3);
        TextView tvtool4= (TextView) view.findViewById(R.id.tvtool4);
        TextView tvtool5= (TextView) view.findViewById(R.id.tvtool5);
        TextView tvtool6= (TextView) view.findViewById(R.id.tvtool6);



        tvtool1.setText("Arduino is a prototype platform (open-source) based on an easy-to-use hardware and software. It consists of a circuit board, which can be programed (referred to as a microcontroller) and a ready-made software called Arduino IDE (Integrated Development Environment), which is used to write and upload the computer code to the physical board.");
        tvtool2.setText("-         Arduino boards are able to read analog or digital input signals from different sensors and turn it into an output such as activating a motor, turning LED on/off, connect to the cloud and many other actions.");
        tvtool3.setText("-         You can control your board functions by sending a set of instructions to the microcontroller on the board via Arduino IDE (referred to as uploading software).");
        tvtool4.setText("-         Unlike most previous programmable circuit boards, Arduino does not need an extra piece of hardware (called a programmer) in order to load a new code onto the board. You can simply use a USB cable.");
        tvtool5.setText("-         Additionally, the Arduino IDE uses a simplified version of C++, making it easier to learn to program.");
        tvtool6.setText("-         Finally, Arduino provides a standard form factor that breaks the functions of the micro-controller into a more accessible package.");
    }
}

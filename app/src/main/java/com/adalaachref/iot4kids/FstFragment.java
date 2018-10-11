package com.adalaachref.iot4kids;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FstFragment extends Fragment {


    public FstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fst, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final SharedPreferences shared = getActivity().getSharedPreferences("pref1", getActivity().getApplicationContext().MODE_PRIVATE);
        String name=shared.getString("name", "");

        TextView username=(TextView)  getView().findViewById(R.id.username);
        ImageButton play=(ImageButton) getView().findViewById(R.id.play);
        ImageButton learn=(ImageButton) getView().findViewById(R.id.learn);
        Button logout =(Button) getView().findViewById(R.id.btnDeconnect);



        username.setText(name);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor editor = shared.edit();

                editor.putString("email", "");
                editor.putString("password", "");


                editor.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment firstFragment = new MapFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),LearnActivity.class);

                startActivity(intent);

            }
        });
    }
}

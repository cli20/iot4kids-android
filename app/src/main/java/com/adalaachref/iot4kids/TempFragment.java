package com.adalaachref.iot4kids;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TempFragment extends Fragment {


    public TempFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnNext=(Button) getView().findViewById(R.id.btnTemp);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt("idPuzzle",3);

                PuzzleFragment firstFragment = new PuzzleFragment();
                firstFragment.setArguments(b);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
            }
        });

    }
}

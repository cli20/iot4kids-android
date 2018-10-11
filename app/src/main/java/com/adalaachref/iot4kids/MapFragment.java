package com.adalaachref.iot4kids;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.adalaachref.iot4kids.model.User;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import java.util.List;


public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public MapFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences shared = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        int idUser=shared.getInt("id_user", 0);
        String name=shared.getString("name", "");
        String email = (shared.getString("email", ""));
        String password= (shared.getString("password", ""));
        int idNiveau=shared.getInt("id_niveau", 0);

        User u=new User(idUser,name,email,password,idNiveau);

        UserDataSource cds=new UserDataSource(getActivity().getApplicationContext());
        cds.open();
        try{
            cds.createUser(u);
        }catch (Exception e){

        }

        List<User> luu=cds.getAllUsers();

        ImageView image1= (ImageView) getView().findViewById(R.id.lvl1);
        ImageView image2= (ImageView) getView().findViewById(R.id.lvl2);
        ImageView image3= (ImageView) getView().findViewById(R.id.lvl3);
        ImageView image4= (ImageView) getView().findViewById(R.id.lvl4);
        ImageView image5= (ImageView) getView().findViewById(R.id.lvl5);
        ImageView image6= (ImageView) getView().findViewById(R.id.lvl6);
        ImageView image7= (ImageView) getView().findViewById(R.id.lvl7);
        ImageView image8= (ImageView) getView().findViewById(R.id.lvl8);
        ImageView image9= (ImageView) getView().findViewById(R.id.lvl9);
        ImageView image10= (ImageView) getView().findViewById(R.id.lvl10);
        ImageView image11= (ImageView) getView().findViewById(R.id.lvl11);

        ImageView ship1= (ImageView) getView().findViewById(R.id.ship1);
        ImageView ship2= (ImageView) getView().findViewById(R.id.ship2);
        ImageView ship3= (ImageView) getView().findViewById(R.id.ship3);
        ImageView ship4= (ImageView) getView().findViewById(R.id.ship4);
        ImageView ship5= (ImageView) getView().findViewById(R.id.ship5);
        ImageView ship6= (ImageView) getView().findViewById(R.id.ship6);
        ImageView ship7= (ImageView) getView().findViewById(R.id.ship7);
        ImageView ship8= (ImageView) getView().findViewById(R.id.ship8);
        ImageView ship9= (ImageView) getView().findViewById(R.id.ship9);
        ImageView ship10= (ImageView) getView().findViewById(R.id.ship10);
        ImageView ship11= (ImageView) getView().findViewById(R.id.ship11);






        if(idNiveau<2)
        {
            image2.setEnabled(false);
            image2.setImageResource(R.drawable.lock);
            ship2.setVisibility(View.INVISIBLE);

        }
        if(idNiveau<3)
        {
            image3.setEnabled(false);

            image3.setImageResource(R.drawable.lock);
            ship3.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<4)
        {
            image4.setEnabled(false);

            image4.setImageResource(R.drawable.lock);
            ship4.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<5)
        {
            image5.setEnabled(false);

            image5.setImageResource(R.drawable.lock);
            ship5.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<6)
        {
            image6.setEnabled(false);

            image6.setImageResource(R.drawable.lock);
            ship6.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<7)
        {
            image7.setEnabled(false);

            image7.setImageResource(R.drawable.lock);
            ship7.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<8)
        {
            image8.setEnabled(false);

            image8.setImageResource(R.drawable.lock);
            ship8.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<9)
        {
            image9.setEnabled(false);

            image9.setImageResource(R.drawable.lock);
            ship9.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<10)
        {
            image10.setEnabled(false);

            image10.setImageResource(R.drawable.lock);
            ship10.setVisibility(View.INVISIBLE);
        }
        if(idNiveau<11)
        {
            image11.setEnabled(false);

            image11.setImageResource(R.drawable.lock);
            ship11.setVisibility(View.INVISIBLE);
        }
        ship1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GameFragment firstFragment = new GameFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();


                    }
                }
        );
        ship2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BlinkingFragment firstFragment = new BlinkingFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                    }
                }
        );
        ship3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("id_niveau",3);
                        DragDrop firstFragment = new DragDrop();
                        firstFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                    }
                }
        );
        ship4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trafficFragment firstFragment = new trafficFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();

                    }
                }
        );
        ship5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("id_niveau",5);
                        DragDrop firstFragment = new DragDrop();
                        firstFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                    }
                }
        );
        ship6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TempFragment firstFragment = new TempFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                       /* Bundle bundle=new Bundle();
                        bundle.putInt("idPuzzle",3);
                        PuzzleFragment firstFragment = new PuzzleFragment();
                        firstFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();*/
                    }
                }
        );
        ship7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("id_niveau",7);
                        DragDrop firstFragment = new DragDrop();
                        firstFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                    }
                }
        );
        ship8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      /*  Menu firstFragment = new Menu();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();*/
                    }
                }
        );



    }
}

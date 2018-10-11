package com.adalaachref.iot4kids;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adalaachref.iot4kids.model.Document;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.ArrayList;


public class ListFragment extends Fragment {
    final String DEVELOPER_KEY="AIzaSyBxXYmT3WIES7xmXBHsP5nkwvnE-l4Wz8M";

    public ListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Document d1=new Document("Introduction","","IOT.png");
        Document d2=new Document("How it works","","works.png");
        Document d3=new Document("Tools","","tools.png");
        Document d4=new Document("Arduino Board Description","","board.png");
       final ArrayList<Document> lc=new ArrayList<>();
        lc.add(d1);
        lc.add(d2);
        lc.add(d3);
        lc.add(d4);


        ListView lv=(ListView)getView().findViewById(R.id.listF);

        CustomAdapter3 adapter= new CustomAdapter3(getActivity().getApplicationContext(),R.layout.list3,lc);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Document dataModel= lc.get(position);
                System.out.println(dataModel.getText().toString());

                if(dataModel.getText().toString().equals("Introduction"))
                {
                    DocFragment firstFragment = new DocFragment();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
                }
                else  if(dataModel.getText().toString().equals("How it works")){

                    final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity());

                    if (result != YouTubeInitializationResult.SUCCESS) {
                        //If there are any issues we can show an error dialog.
                        result.getErrorDialog(getActivity(), 0).show();
                    }


                    startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                            DEVELOPER_KEY, "QSIPNhOiMoE"));

                }
                else if(dataModel.getText().toString().equals("Tools"))
                {
                    ToolFragment firstFragment = new ToolFragment();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();
                }
                else if(dataModel.getText().toString().equals("Arduino Board Description"))
                {

                    BoardFragment firstFragment = new BoardFragment();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fr3, firstFragment).addToBackStack(null).commit();

                }





            }
        });

    }
}

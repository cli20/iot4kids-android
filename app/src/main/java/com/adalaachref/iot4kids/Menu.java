package com.adalaachref.iot4kids;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

/**
 * Created by Maroua on 17-11-07.
 */

public class Menu extends Fragment {
    Button bdoc,bpu,bdrag;
Context context;
    public Menu() {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);






    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String DEVELOPER_KEY="AIzaSyBxXYmT3WIES7xmXBHsP5nkwvnE-l4Wz8M";
        bdoc= (Button)getView().findViewById(R.id.doc);

        //Check for any issues
        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity());

        if (result != YouTubeInitializationResult.SUCCESS) {
            //If there are any issues we can show an error dialog.
            result.getErrorDialog(getActivity(), 0).show();
        }


        startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                DEVELOPER_KEY, "Gr-8q6b7-CE"));



       bdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



    }
}

package com.adalaachref.iot4kids;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adalaachref.iot4kids.model.Score;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * A simple {@link Fragment} subclass.
 */
public class QFragment extends Fragment {
    private static String TAG = MainActivity.class.getSimpleName();
    public TextView scoreView;
    ProgressBar progress;
    ImageView img, y1, y2, y3;
    TextView question;
    List<Quizz> listQ = new ArrayList<>();
    List<Score> listS = new ArrayList<>();
    Score sc = new Score();
    CountDownTimer mCountDownTimer;

    int i = 0;
    Quizz q = new Quizz();
    ProgressDialog PD;
    int score;

    public QFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

       final  SharedPreferences shared = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        final  int idUser=shared.getInt("id_user", 0);
        final int idLevel=shared.getInt("id_niveau", 0);
        Bundle bundle = getArguments();
        score = bundle.getInt("Score");

    /*    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.score);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareButton shareButton = (ShareButton)view.findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);*/


        final LinearLayout answer1 = (LinearLayout) view.findViewById(R.id.answer);
        LinearLayout answer2 = (LinearLayout) view.findViewById(R.id.answer1);
        LinearLayout answer3 = (LinearLayout) view.findViewById(R.id.answer2);
        final TextView q1 = (TextView) view.findViewById(R.id.q1);
        final TextView q2 = (TextView) view.findViewById(R.id.q2);
        final TextView q3 = (TextView) view.findViewById(R.id.q3);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        y1 = (ImageView) view.findViewById(R.id.y1);
        y2 = (ImageView) view.findViewById(R.id.y2);
        y3 = (ImageView) view.findViewById(R.id.y3);
        scoreView = (TextView) view.findViewById(R.id.score);

        JsonArrayRequest stringRequest = new JsonArrayRequest("http://192.168.101.1:5000/quiz/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {



                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = (JSONObject) response.get(i);
                                Quizz q = new Quizz();
                                q.setId(obj.getInt("id_quiz"));
                                q.setNom(obj.getString("nom"));
                                q.setCategorie(obj.getString("categorie"));
                                q.setQuestion(obj.getString("question"));
                                q.setReponse(obj.getString("reponse"));
                                q.setId_niveau(obj.getInt("id_niveau"));
                                System.out.println(q);
                                listQ.add(q);
                                //JSONObject matchday = obj.getJSONObject("name");


                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!" + error);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);


        //handle progress
        mCountDownTimer = new CountDownTimer(60000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {


                i++;


                progress.setProgress(i);


            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                progress.setProgress(60);
            }
        };
        mCountDownTimer.start();
        scoreView.setText("Score:" + score);

        answer3.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if ((listQ.get(2).getId() == 3) && (q3.getText().toString().equals(listQ.get(2).getReponse()))) {
                                               score++;

                                               y3.setImageResource(R.drawable.yes);
                                               //setting Score in DB
                                               JsonArrayRequest stringRequest1 = new JsonArrayRequest("http://192.168.101.1:5000/score/",
                                                       new Response.Listener<JSONArray>() {
                                                           @Override
                                                           public void onResponse(JSONArray response) {

                                                               try {


                                                                   for (int i = 0; i < response.length(); i++) {
                                                                       JSONObject obj = (JSONObject) response.get(i);
                                                                       Score s = new Score();
                                                                       s.setId(obj.getInt("id_score"));
                                                                       s.setScore(obj.getInt("score"));
                                                                       s.setId_user(obj.getInt("id_user"));

                                                                       listS.add(s);


                                                                   }



                                                               } catch (JSONException e) {
                                                                   e.printStackTrace();
                                                               }
                                                           }
                                                       }, new Response.ErrorListener() {
                                                   @Override
                                                   public void onErrorResponse(VolleyError error) {
                                                       System.out.println("That didn't work!" + error);
                                                   }
                                               });

                                               RequestQueue queue1 = Volley.newRequestQueue(getActivity());
                                               queue1.add(stringRequest1);

                                               final String urlJsonArry = "http://192.168.101.1:5000/score/"+score+"/"+idUser;


                                               JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                                                       new Response.Listener<JSONArray>() {
                                                           @Override
                                                           public void onResponse(JSONArray response) {

                                                           }

                                                       }, new Response.ErrorListener() {
                                                   @Override
                                                   public void onErrorResponse(VolleyError error) {
                                                       VolleyLog.d(TAG, "Error: " + error.getMessage());
                                                       Toast.makeText(getActivity().getApplicationContext(),
                                                               error.getMessage(), Toast.LENGTH_SHORT).show();

                                                   }
                                               });
                                               AppController.getInstance().addToRequestQueue(req);

                                               int lvl=2;

                                               if(idLevel<lvl)
                                               {
                                                   SharedPreferences.Editor editor = shared.edit();
                                                   editor.putInt("id_niveau",lvl);

                                                   editor.commit();
                                                   //final String urlJsonArry = "http://10.0.2.2:5000/level/"+3+"/"+idUserr;
                                                   final String urlJsonArray = "http://192.168.101.1:5000/level/"+lvl+"/"+idUser;

                                                   JsonArrayRequest req1 = new JsonArrayRequest(urlJsonArray,
                                                           new Response.Listener<JSONArray>() {
                                                               @Override
                                                               public void onResponse(JSONArray response) {

                                                               }

                                                           }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {
                                                           VolleyLog.d(TAG, "Error: " + error.getMessage());


                                                       }
                                                   });
                                                   AppController.getInstance().addToRequestQueue(req1);
                                               }


                                               Intent intent = new Intent(getActivity(),MainActivity.class);

                                               startActivity(intent);


                                           }}
                                   }

        );


        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y1.setImageResource(R.drawable.no);

            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y2.setImageResource(R.drawable.no);

            }
        });


    }



}







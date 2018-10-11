package com.adalaachref.iot4kids;


import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    ProgressBar progress;
    ImageView img, y1, y2, y3;
    TextView question;
    Score sc = new Score();
    List<Quizz> listQ = new ArrayList<>();
    CountDownTimer mCountDownTimer;
    int i = 0;
    Quizz q = new Quizz();
TextView scoreView;
int score;
    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        score=  bundle.getInt("Score");
        System.out.println(score+"Score2");

        final LinearLayout answer1 = (LinearLayout) view.findViewById(R.id.answer);
        LinearLayout answer2 = (LinearLayout) view.findViewById(R.id.answer1);
        LinearLayout answer3 = (LinearLayout) view.findViewById(R.id.answer2);
        final TextView q1=(TextView) view.findViewById(R.id.q1);
       final TextView q2=(TextView) view.findViewById(R.id.q2);
       final TextView q3=(TextView) view.findViewById(R.id.q3);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        y1 = (ImageView) view.findViewById(R.id.y1);
        y2 = (ImageView) view.findViewById(R.id.y2);
        y3 = (ImageView) view.findViewById(R.id.y3);
        scoreView = (TextView) view.findViewById(R.id.score);
        ImageView img = (ImageView) view.findViewById(R.id.image);
        JsonArrayRequest stringRequest = new JsonArrayRequest("http://192.168.101.1:5000/quiz/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response);
                        System.out.println("wow");
                        try {
                            System.out.println("wowv");





                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj =(JSONObject)response.get(i);
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


                            System.out.println("La liste de quiz"+listQ.toString());


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
//gettingScore


        StringRequest stringRequestSc = new StringRequest(Request.Method.GET, "http://192.168.101.1:5000/score/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        try {

                            JSONObject prem = new JSONObject(response);
                            JSONArray rounds = prem.getJSONArray("rounds");


                            // ArrayList<Match> listE = new ArrayList<>();


                            for (int i = 0; i < rounds.length(); i++) {
                                JSONObject obj = rounds.getJSONObject(i);

                                sc.setId(obj.getInt("id_score"));
                                sc.setScore(obj.getInt("score"));
                                sc.setId_user(obj.getInt("id_user"));




                                // JSONObject matchday = obj.getJSONObject("name");


                            }


                            //   System.out.println(listE.toString());


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

        RequestQueue queueSc = Volley.newRequestQueue(getActivity());
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
        scoreView.setText("Score:"+score);

        answer2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           System.out.println();
                                           if ((listQ.get(1).getId()==2)&&(q2.getText().toString().equals(listQ.get(1).getReponse())))
                                           {
                                                score++;
                                           System.out.println("fragment"+score);


                                           y2.setImageResource(R.drawable.yes);
                                           Bundle bundle = new Bundle();
                                           bundle.putInt("Score", score);

                                           QFragment qf =new  QFragment();

                                           qf.setArguments(bundle);
                                           getFragmentManager().beginTransaction().replace(R.id.sf,qf).commit();


                                       }}
                                   }

        );
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y1.setImageResource(R.drawable.no);

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y3.setImageResource(R.drawable.no);

            }
        });


    }


}







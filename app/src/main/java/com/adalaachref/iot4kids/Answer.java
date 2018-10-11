package com.adalaachref.iot4kids;


        import android.content.DialogInterface;
        import android.content.res.ColorStateList;
        import android.graphics.Color;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import com.adalaachref.iot4kids.model.Score;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Maroua on 17-11-27.
 */

public class Answer extends AppCompatActivity {

    LinearLayout answer1, answer2,answer3;
    ProgressBar progress;
    ImageView img,y1,y2,y3;
    TextView scoreView;
    int score=0;

    CountDownTimer mCountDownTimer;
    int i=0;
    Score sc = new Score();
    List<Quizz> listQ = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz);




        answer1=(LinearLayout)findViewById(R.id.answer);
        answer2=(LinearLayout)findViewById(R.id.answer1);
        answer3=(LinearLayout)findViewById(R.id.answer2);
        progress=(ProgressBar) findViewById(R.id.progress);
        scoreView =(TextView) findViewById(R.id.score);

        y1= (ImageView) findViewById(R.id.y1);
        y2= (ImageView) findViewById(R.id.y2);
        y3= (ImageView) findViewById(R.id.y3);
        final TextView q1=(TextView) findViewById(R.id.q1);
        final TextView q2=(TextView) findViewById(R.id.q2);
        final TextView q3=(TextView) findViewById(R.id.q3);


        JsonArrayRequest stringRequest = new JsonArrayRequest("http://192.168.101.1:5000/quiz/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {






                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj =(JSONObject)response.get(i);
                                Quizz q = new Quizz();
                                q.setId(obj.getInt("id_quiz"));
                                q.setNom(obj.getString("nom"));
                                q.setCategorie(obj.getString("categorie"));
                                q.setQuestion(obj.getString("question"));
                                q.setReponse(obj.getString("reponse"));
                                q.setId_niveau(obj.getInt("id_niveau"));

                                listQ.add(q);



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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
//gettingScore


        StringRequest stringRequestSc = new StringRequest(Request.Method.GET, "http://192.168.101.1:5000/score/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject prem = new JSONObject(response);
                            JSONArray rounds = prem.getJSONArray("rounds");


                            // ArrayList<Match> listE = new ArrayList<>();


                            for (int i = 0; i < rounds.length(); i++) {
                                JSONObject obj = rounds.getJSONObject(i);

                                sc.setId(obj.getInt("id_score"));
                                sc.setScore(obj.getInt("score"));
                                sc.setId_user(obj.getInt("id_user"));


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

        RequestQueue queueSc = Volley.newRequestQueue(this);
        queue.add(stringRequest);





        //handle progress
        mCountDownTimer=new CountDownTimer(60000,1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);

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

        answer1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           System.out.println("1t");


                                           if ((listQ.get(0).getId()==1)&&(q1.getText().toString().equals(listQ.get(0).getReponse()))) {
                                               score ++;
                                               y1.setImageResource(R.drawable.yes);
                                               Bundle bundle = new Bundle();
                                               bundle.putInt("Score", score);

                                               QuestionFragment qf = new QuestionFragment();

                                               qf.setArguments(bundle);
                                               getFragmentManager().beginTransaction().add(R.id.sf, qf).commit();
                                           }

                                       }
                                   }

        );
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y2.setImageResource(R.drawable.no);

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

package com.adalaachref.iot4kids;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adalaachref.iot4kids.model.Block;
import com.adalaachref.iot4kids.model.Program;
import com.adalaachref.iot4kids.model.Solution;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maroua on 17-11-07.
 */

public class DragDrop extends Fragment {
    private static String TAG = MainActivity.class.getSimpleName();


    public DragDrop() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_code, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SharedPreferences shared = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        final int idLevel=shared.getInt("id_niveau", 0);
        final int idUserr=shared.getInt("id_user", 0);
        final int id_niveau=getArguments().getInt("id_niveau");
        final String DEVELOPER_KEY="AIzaSyBxXYmT3WIES7xmXBHsP5nkwvnE-l4Wz8M";


        Button btnTuto=(Button)getView().findViewById(R.id.btnTuto);
        btnTuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_niveau==3)
                {
                    //Check for any issues
                    final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity());

                    if (result != YouTubeInitializationResult.SUCCESS) {
                        //If there are any issues we can show an error dialog.
                        result.getErrorDialog(getActivity(), 0).show();
                    }


                    startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                            DEVELOPER_KEY, "Gr-8q6b7-CE"));
                }
            }
        });
        // final String urlJsonArry = "http://10.0.2.2:5000/application/"+id_niveau;
        final String urlJsonArry = "http://192.168.101.1:5000/application/"+id_niveau;



        LinearLayout ln=(LinearLayout)getView().findViewById(R.id.linearLayout);
        ln.setBackgroundResource(R.drawable.rounded_corner);


        final ListView lv=(ListView)getView().findViewById(R.id.listT);
        ArrayList<String> lc=new ArrayList<String>();
        lc.add("Control");
        lc.add("Mouvement");

        CustomAdapter adapter= new CustomAdapter(getActivity().getApplicationContext(),R.layout.list,lc);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                String str=(String)o;
                if(str.equals("Control"))
                {   ListView lv2=(ListView)getView().findViewById(R.id.list2);
                    ArrayList<String>lc2=new ArrayList<String>();
                    lc2.add("when start pressed");
                    lc2.add("repeat indefinitely");
                    lc2.add("wait 1 seconde");
                    lc2.add("wait 3 secondes");
                    lc2.add("wait 10 secondes");
                    lc2.add("if(Temparature>20°)then");
                    lc2.add("else");
                    CustomAdapter2 adapter2= new CustomAdapter2(getActivity().getApplicationContext(),R.layout.list2,lc2);
                    lv2.setAdapter(adapter2);
                    lv2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                        public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                                       int pos, long id) {
                            // TODO Auto-generated method stub

                            DragShadow dragShadow = new DragShadow(v);
                            ClipData data = ClipData.newPlainText("", "");
                            v.startDrag(data, dragShadow, v, 0);
                            return true;
                        }
                    });
                }
                else if(str.equals("Mouvement")){
                    ListView lv2=(ListView)getView().findViewById(R.id.list2);
                    ArrayList<String>lc2=new ArrayList<String>();
                    lc2.add("digital 13 on");
                    lc2.add("digital 13 off");
                    lc2.add("digital 12 on");
                    lc2.add("digital 12 off");
                    lc2.add("digital 11 on");
                    lc2.add("digital 11 off");
                    lc2.add("digital 10 on");
                    lc2.add("digital 10 off");
                    lc2.add("digital 3 on");
                    lc2.add("digital 3 off");
                    CustomAdapter2 adapter2= new CustomAdapter2(getActivity().getApplicationContext(),R.layout.list2,lc2);
                    lv2.setAdapter(adapter2);
                    lv2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                        public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                                       int pos, long id) {
                            // TODO Auto-generated method stub

                            DragShadow dragShadow = new DragShadow(v);
                            ClipData data = ClipData.newPlainText("", "");
                            v.startDrag(data, dragShadow, v, 0);
                            return true;
                        }
                    });

                }
            }
        });


       getView().findViewById(R.id.linearLayout).setOnDragListener(DropListener);

        Button btnBuild =(Button)getView().findViewById(R.id.btnBuild);
        btnBuild.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout ln=(LinearLayout)getView().findViewById(R.id.linearLayout);
                int count = ln.getChildCount();
               final List<String> blocks=new ArrayList<String>();
                for(int i=0; i<count; i++) {
                    TextView tv = (TextView)ln.getChildAt(i);
                   blocks.add(tv.getText().toString());

                }
                JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d(TAG, response.toString());
                                try {
                                    List<Solution> ls=new ArrayList<>();
                                    String nom_app="";

                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject app = (JSONObject) response.get(i);
                                        nom_app=app.getString("nom_app");
                                        int ids=app.getInt("ids");
                                        int idb=app.getInt("idb");
                                        int posb=app.getInt("posb");
                                        String nom=app.getString("nom");
                                        Block b=new Block(idb,nom);
                                        boolean testid=false;
                                        boolean test2=false;

                                        for(Solution s:ls)
                                        {

                                            if(s.getIdSolution()==ids)
                                            { testid=true;

                                            }
                                            else
                                            {
                                                testid=false;
                                            }
                                            if(testid)
                                            {
                                                test2=true;
                                                s.getMb().put(posb,b);
                                               // testid=false;
                                                //System.out.println(s.getIdSolution());
                                            }
                                        }
                                        if(!test2)
                                        {   Map<Integer,Block> mb=new HashMap<>();
                                            Solution s=new Solution(ids,mb);
                                            s.getMb().put(posb,b);
                                            ls.add(s);

                                        }
                                    }
                                    Program app=new Program(id_niveau,nom_app,ls);
                                    System.out.println(ls);
                                    boolean build=false;
                                    for(Solution s:ls)
                                    {boolean test=true;
                                        int j=1;
                                     for(String block: blocks)
                                     {
                                         System.out.println(s.getMb().get(j).getNom());
                                         System.out.println(block);
                                         if(!block.equals(s.getMb().get(j).getNom()))
                                         { test=false;

                                         }
                                         j++;
                                     }
                                     if(test)
                                         build=true;
                                    }
                                    if(!(ls.get(0).getMb().size()==blocks.size()))
                                    {
                                        build=false;
                                    }
                                    if(build)
                                    {  int lvl=id_niveau+1;
                                        if(idLevel<lvl)
                                    {

                                        SharedPreferences.Editor editor = shared.edit();
                                        editor.putInt("id_niveau",lvl);

                                        editor.commit();

                                        final String urlJsonArry = "http://192.168.101.1:5000/level/"+lvl+"/"+idUserr;

                                        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                                                new Response.Listener<JSONArray>() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {

                                                    }

                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                              /*  Toast.makeText(getActivity().getApplicationContext(),
                                                        error.getMessage(), Toast.LENGTH_SHORT).show();*/

                                            }
                                        });
                                        AppController.getInstance().addToRequestQueue(req);
                                    }
                                        //Spinner spinner = new Spinner(getActivity());
                                        //myContainer.addView(spinner);
                                        AlertDialog.Builder alertadd = new AlertDialog.Builder(getActivity());
                                        LayoutInflater factory = LayoutInflater.from(getActivity());
                                        final View view = factory.inflate(R.layout.code_image, null);
                                        alertadd.setView(view);
                                        ImageView imagev=(ImageView) view.findViewById(R.id.dialog_imageview);
                                        if(id_niveau==2)
                                        Glide.with(getActivity().getApplicationContext()).load("http://192.168.101.1/iot4kids/title.gif").into(new DrawableImageViewTarget(imagev));
                                        else if(id_niveau==4)
                                            Glide.with(getActivity().getApplicationContext()).load("http://192.168.101.1/iot4kids/title22.gif").into(new DrawableImageViewTarget(imagev));
                                        alertadd.setNeutralButton("close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dlg, int sumthin) {
                                                MapFragment firstFragment = new MapFragment();
                                                getFragmentManager().beginTransaction()
                                                        .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();
                                            }
                                        });

                                        alertadd.show();
                                        Toast.makeText(getActivity().getApplicationContext(),"Build Succeded",Toast.LENGTH_LONG).show();


                                    }

                                    else
                                        Toast.makeText(getActivity().getApplicationContext(),"Build failed",Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Error: " + e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }

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

            }
        });




    }

    private class DragShadow extends View.DragShadowBuilder {

        ColorDrawable greyBox;

        public DragShadow(View view) {
            super(view);
            // TODO Auto-generated constructor stub
            greyBox = new ColorDrawable(Color.BLUE);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            // TODO Auto-generated method stub
            // super.onDrawShadow(canvas);

            greyBox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize,
                                           Point shadowTouchPoint) {
            // TODO Auto-generated method stub
            // super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
            View v = getView();

            int height = (int) v.getHeight() / 2;
            int width = (int) v.getWidth() / 2;

            greyBox.setBounds(0, 0, width, height);
            shadowSize.set(width, height);

            shadowTouchPoint.set(width / 2, height / 2);

        }


    }

    View.OnDragListener DropListener = new View.OnDragListener() {
        @TargetApi(17)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            // TODO Auto-generated method stub

            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Drag ", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Drag ", "Exit");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Drag ", "Drop");

                   TextView target=new TextView(getActivity().getApplicationContext());
                    LinearLayout  ln= (LinearLayout) v;
                    TextView dragg = (TextView) event.getLocalState();
                    target.setText(dragg.getText());
                    target.setBackgroundResource(R.drawable.rounded_orange);

                    target.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    target.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                           // TextView tv=(TextView) v;
                           // ((LinearLayout) tv.getParent()).removeView(tv);
                          //  tv.setVisibility(View.GONE) ;

                         //   ((LinearLayout)v.getParent()).removeView(v);
                            return false;
                        }
                    });
                    ln.addView(target);
                    break;
                default:
                    break;
            }

            return true;
        }
    };
}

package com.adalaachref.iot4kids;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adalaachref.iot4kids.model.Component;
import com.adalaachref.iot4kids.model.Position;
import com.adalaachref.iot4kids.model.Puzzle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.URL;

public class PuzzleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src1");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
public Context context;
  private  ProgressBar spinner,spinner2;

    // json array response url
    // private String urlJsonArry = "https://api.androidhive.info/volley/person_array.json";


    private static String TAG = MainActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;


    // temporary string to show the parsed response
    private String jsonResponse;




    public PuzzleFragment()
    {}

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_puzzle, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        int id_p=getArguments().getInt("idPuzzle");
        //private String urlJsonArry = "http://10.0.2.2:5000/puzzle/1";
        String urlJsonArry = "http://192.168.101.1:5000/puzzle/"+id_p;
        TextView tip=(TextView) getView().findViewById(R.id.textView);

        tip.setBackgroundResource(R.drawable.rounded_blue);

        final SharedPreferences shared = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        final int idLevel=shared.getInt("id_niveau", 0);
        final int idUserr=shared.getInt("id_user", 0);
        Button btnNext=(Button) getView().findViewById(R.id.btnSuiv);
        btnNext.setEnabled(false);




        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            final Puzzle p1 =new Puzzle();
                            try {
                                // Parsing json array response
                                // loop through each json object
                                jsonResponse = "";
                                Map<Position,Component> mp=new HashMap<>();
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response.get(i);
                                    int id=person.getInt("id_puzzle");
                                    String nom=person.getString("nom");
                                    String carte=person.getString("carte");
                                    String completed=person.getString("completed");
                                    String type=person.getString("type");
                                    int id_niveau=person.getInt("id_niveau");
                                    int id_position=person.getInt("id_position");
                                    int xMax=person.getInt("xMax");
                                    int yMax=person.getInt("yMax");
                                    int xMin=person.getInt("xMin");
                                    int yMin=person.getInt("yMin");
                                    int x=person.getInt("x");
                                    int y=person.getInt("y");
                                    int idComponent=person.getInt("id_composant");
                                    String img=person.getString("image");
                                    p1.setIdLevel(id_niveau);
                                    p1.setIdPuzzle(id);
                                    p1.setCarte(carte);
                                    p1.setCompleted(completed);
                                    Position pos=new Position(xMax,yMax,xMin,yMin,x,y);
                                    Component com=new Component(idComponent,nom,img);
                                    mp.put(pos,com);


                                    jsonResponse += "idPuzzle: " + id + "\n\n";
                                    p1.setContent(mp);
                                }

                                ImageView ard=(ImageView) getView().findViewById(R.id.ard);
                              spinner=(ProgressBar)  getView().findViewById(R.id.progress1);
                                spinner.setVisibility(View.GONE);
                                new DownloadImageTask1(ard).execute("http://192.168.101.1/iot4kids/"+p1.getCarte());
                                spinner.setVisibility(View.VISIBLE);


                                ImageButton btnPuzzle=(ImageButton) getView().findViewById(R.id.btnPuzzle);
                                btnPuzzle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder alertadd = new AlertDialog.Builder(getActivity());
                                        LayoutInflater factory = LayoutInflater.from(getActivity());
                                        final View view = factory.inflate(R.layout.puzzle_image, null);
                                        ImageView ard1=(ImageView) view.findViewById(R.id.dialog_imageview);
                                        spinner2=(ProgressBar) view.findViewById(R.id.progress2);
                                        spinner2.setVisibility(View.GONE);
                                        new DownloadImageTask2(ard1).execute("http://192.168.101.1/iot4kids/"+p1.getCompleted());
                                        spinner2.setVisibility(View.VISIBLE);
                                        alertadd.setView(view);
                                        alertadd.setNeutralButton("close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dlg, int sumthin) {

                                            }
                                        });

                                        alertadd.show();
                                    }
                                });

                               final int size =p1.getContent().entrySet().size();
                                Iterator it=p1.getContent().entrySet().iterator();
                                final List<Boolean> lb=new ArrayList<>();


                                while(it.hasNext())
                                {
                                    Map.Entry pair = (Map.Entry)it.next();
                                    final  Position p=(Position)pair.getKey();
                                    final Component c=(Component)pair.getValue();
                                    final ImageView img=new ImageView(getActivity());
                                    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(200,100);
                                    img.setLayoutParams(lp);

                                    new DownloadImageTask(img).execute("http://192.168.101.1/iot4kids/"+c.getImg());

                                    img.setOnTouchListener(new View.OnTouchListener() {
                                                               @Override
                                                               public boolean onTouch(View v, MotionEvent event) {

                                                                   RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img.getLayoutParams();
                                                                   switch(event.getAction())
                                                                   {
                                                                       case MotionEvent.ACTION_DOWN:
                                                                           break;
                                                                       case MotionEvent.ACTION_MOVE:

                                                                           int x_cord = (int)event.getRawX();
                                                                           int y_cord = (int)event.getRawY();
                                                                           layoutParams.leftMargin = x_cord -25;
                                                                           layoutParams.topMargin = y_cord - 75;

                                                                           img.setLayoutParams(layoutParams);
                                                                           break;
                                                                       case MotionEvent.ACTION_UP:
                                                                           if((p.getxMax()>=event.getRawX())&&(p.getxMin()<=event.getRawX())&&(p.getyMax()>=event.getRawY())&&(p.getyMin()<=event.getRawY())) {
                                                                               Toast.makeText(getActivity().getApplicationContext(), "the component " + c.getName() + " is on the right place", Toast.LENGTH_SHORT).show();
                                                                               int x_cord1 = (int)p.getX();
                                                                               int y_cord1 = (int)p.getY();
                                                                               layoutParams.leftMargin = x_cord1 -25;
                                                                               layoutParams.topMargin = y_cord1 - 75;
                                                                                lb.add(true);
                                                                               if(size==lb.size())
                                                                               {
                                                                                   Button b=(Button) getView().findViewById(R.id.btnSuiv);
                                                                                   b.setEnabled(true);
                                                                                   b.setOnClickListener(new View.OnClickListener() {
                                                                                       @Override
                                                                                       public void onClick(View v) {

                                                                                           Bundle bundle=new Bundle();
                                                                                           bundle.putInt("id_niveau",p1.getIdLevel());
                                                                                           DragDrop firstFragment = new DragDrop();
                                                                                           firstFragment.setArguments(bundle);
                                                                                           getFragmentManager().beginTransaction()
                                                                                                   .replace(R.id.fr1, firstFragment).addToBackStack(null).commit();

                                                                                       }
                                                                                   });

                                                                               }
                                                                               img.setLayoutParams(layoutParams);
                                                                               img.setClickable(false);
                                                                               img.setOnTouchListener(new View.OnTouchListener() {
                                                                                   @Override
                                                                                   public boolean onTouch(View v, MotionEvent event) {
                                                                                       return false;
                                                                                   }
                                                                               });


                                                                           }
                                                                           System.out.println("x :"+event.getRawX());
                                                                           System.out.println("y :"+event.getRawY());
                                                                           break;
                                                                       default:
                                                                           break;
                                                                   }
                                                                   return true;
                                                               }
                                                           }
                                    );
                                    RelativeLayout fr=(RelativeLayout)getView().findViewById(R.id.frame1);

                                   fr.addView(img);
                                    it.remove();
                                }

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

            // Adding request to request queue

            AppController.getInstance().addToRequestQueue(req);





    }

    public class DownloadImageTask1 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask1(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            spinner.setVisibility(View.GONE);
            bmImage.setImageBitmap(result);
        }
    }
    public class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask2(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            spinner2.setVisibility(View.GONE);
            bmImage.setImageBitmap(result);
        }
    }

}

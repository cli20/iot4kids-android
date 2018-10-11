package com.adalaachref.iot4kids;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dell on 21/11/2017.
 */

public class PostTask extends AsyncTask<String, Void, String> {




    @Override
    protected String doInBackground(String... params) {

        String urlString="http://10.0.2.2:5000/user/add";

        try{
          /*  String data = "name=" + URLEncoder.encode(name,"utf-8") +
                    "&email=" + URLEncoder.encode(email,"utf-8") +
                    "&password=" + URLEncoder.encode(password,"utf-8");
            byte[] outputBytes=data.getBytes();
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.connect();

            OutputStream os=conn.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null)
                result += line;
            bufferedReader.close();
            inputStream.close();
            conn.disconnect();
            return result;*/

            String name = params[0];
            String email=params[1];
            String password=params[2];
            String myurl = "http://10.0.2.2:5000/user/add";

                URL url = new URL(myurl);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();                        			httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = "name=" + URLEncoder.encode(name,"utf-8") +
                    "&email=" + URLEncoder.encode(email,"utf-8") +
                    "&password=" + URLEncoder.encode(password,"utf-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null)
                    result += line;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "done";
    }

    protected void onPostExecute(String page)
    {
        //onPostExecute
    }
}

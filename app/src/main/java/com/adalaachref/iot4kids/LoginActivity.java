package com.adalaachref.iot4kids;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adalaachref.iot4kids.model.User;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
   // private String urlJsonArry = "http://10.0.2.2:5000/user";
    private String urlJsonArry = "http://192.168.101.1:5000/user";

    private String jsonResponse;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;
   CallbackManager callbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        PackageInfo info;


        SharedPreferences shared = getSharedPreferences("pref1", MODE_PRIVATE);
        int idUser=shared.getInt("id_user", 0);
        String name=shared.getString("name", "");
        String email = (shared.getString("email", ""));
        String password= (shared.getString("password", ""));
        int idNiveau=shared.getInt("id_niveau", 0);
        User u=new User(idUser,name,email,password,idNiveau);
        EditText ed1=(EditText) findViewById(R.id.input_email);
        EditText ed2=(EditText) findViewById(R.id.input_password);
        System.out.println(email);

        //facebook
        try {
            info = getPackageManager().getPackageInfo(
                    "com.adalaachref.iot4kids",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        loginButton.setReadPermissions("email","public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
                finish();

            }


            public void displayUserInfo(JSONObject object)
            {

                String first_name,last_name,email,id ;
                try {
                    first_name =object.getString("first_name");
                    last_name=object.getString("last_name");
                    email=object.getString("email");
                    id=object.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        if(!email.equals("") && !password.equals(""))
        {

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);


           /* ed1.setText(email);
            ed2.setText(password);*/


        }


        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

       final String email = _emailText.getText().toString();
       final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        System.out.println("1");
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        final  List<User> lu=new ArrayList<User>();
                        final User u=new User();
                        System.out.println("2");
                        try {
                            // Parsing json array response
                            // loop through each json object
                            System.out.println("3");
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);

                                int id=person.getInt("id_user");
                                String name=person.getString("name");
                                String email=person.getString("email");
                                String password=person.getString("password");
                                int idNiveau=person.getInt("id_niveau");
                                User u1 =new User(id,name,email,password,idNiveau);

                                lu.add(u1);
                            }
                            System.out.println(lu);
                            boolean b=false;
                            for (int j=0;j<lu.size();j++) {
                                System.out.println(j);
                                if ((email.equals(lu.get(j).getEmail())) && (password.equals(lu.get(j).getPassword()))) {
                                    SharedPreferences sharedpreferences = getSharedPreferences("pref1", getApplicationContext().MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putInt("id_user",lu.get(j).getIdUser());
                                    editor.putString("name", lu.get(j).getName());
                                    editor.putString("email", email);
                                    editor.putString("password", password);
                                    editor.putInt("id_niveau",lu.get(j).getIdNiveau());

                                    editor.commit();
                                    u.setIdUser(lu.get(j).getIdUser());
                                    u.setEmail(lu.get(j).getEmail());
                                    u.setIdNiveau(lu.get(j).getIdNiveau());
                                    u.setName(lu.get(j).getName());
                                    u.setPassword(lu.get(j).getPassword());
                                    b = true;
                                    System.out.println("true");
                                }
                            }
                            // On complete call either onLoginSuccess or onLoginFailed
                            if(b)
                            {
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                onLoginSuccess(u);
                                              progressDialog.dismiss();
                                            }
                                        }, 3000);
                            }

                            else{
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                onLoginFailed();
                                            progressDialog.dismiss();
                                            }
                                        }, 3000);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(req);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically

                this.finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(User u) {
        _loginButton.setEnabled(true);



        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
      finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
            _passwordText.setError("between 3 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}


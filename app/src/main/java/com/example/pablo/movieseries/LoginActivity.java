package com.example.pablo.movieseries;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText edtUser, edtPassword;
    private TextView txtRegister;
    private SharedPreferences spUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        txtRegister = (TextView)findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(this);

        spUsuario = getSharedPreferences("usuario", MODE_PRIVATE);

        if(spUsuario.contains("user")){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String url = "http://www.pabloperezlopez.com/movieseries/check_login.php";

                JSONObject jsonLogin = new JSONObject();
                try {
                    jsonLogin.put("nombreLogin", edtUser.getText().toString());
                    jsonLogin.put("password", edtPassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonLogin,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(response.getString("estado").equals("1")) {
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        SharedPreferences.Editor editor = spUsuario.edit();
                                        editor.putString("user", edtUser.getText().toString());
                                        editor.putString("email", response.getJSONObject("usuario").getString("Email"));
                                        editor.putString("id", response.getJSONObject("usuario").getString("idUsuario"));
                                        editor.commit();
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                        Snackbar.make(findViewById(android.R.id.content), R.string.login_incorrecto, Snackbar.LENGTH_LONG)
                                                .show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };

                requestQueue.add(getRequest);
                break;

            case R.id.txtRegister:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
                break;
        }

    }



}

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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtLogin;
    private EditText edtUserRegister, edtEmailRegister, edtPasswordRegister, edtRepPasswordRegister;
    private Button btnRegister;
    private SharedPreferences spUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spUsuario = getSharedPreferences("usuario", MODE_PRIVATE);

        txtLogin = (TextView)findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(this);

        edtEmailRegister = (EditText)findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = (EditText)findViewById(R.id.edtPasswordRegister);
        edtRepPasswordRegister = (EditText)findViewById(R.id.edtRepPasswordRegister);
        edtUserRegister = (EditText)findViewById(R.id.edtUserRegister);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()){
            case R.id.btnRegister:
                if(formIsValid()){
                    insertUsuario();
                }
                break;
            case R.id.txtLogin:
                i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void insertUsuario() {
        String url = "http://www.pabloperezlopez.com/movieseries/insertar_usuario.php";

        JSONObject jsonUsuario = new JSONObject();
        try {
            jsonUsuario.put("nombreLogin", edtUserRegister.getText().toString());
            jsonUsuario.put("password", edtPasswordRegister.getText().toString());
            jsonUsuario.put("email", edtEmailRegister.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonUsuario,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("estado").equals("1")) {

                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                SharedPreferences.Editor editor = spUsuario.edit();
                                editor.putString("id", response.getJSONObject("idUsuario").getString("idUsuario"));
                                editor.putString("email", edtEmailRegister.getText().toString());
                                editor.putString("user", edtUserRegister.getText().toString());
                                editor.commit();
                                startActivity(i);
                                finish();
                            }
                            else
                                Snackbar.make(findViewById(android.R.id.content), R.string.existe_usuario, Snackbar.LENGTH_LONG)
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
    }

    private boolean formIsValid() {
        Boolean todoOk = true;
        String errores = "";
        if(edtEmailRegister.getText().toString().equals("") || edtUserRegister.getText().toString().equals("") || edtPasswordRegister.getText().toString().equals("") || edtRepPasswordRegister.getText().toString().equals("")) {
            errores += getString(R.string.campos_vacios) + "\n";
            todoOk = false;
        } if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmailRegister.getText().toString()).matches()){
            errores += getString(R.string.formato_email) + "\n";
            todoOk = false;
        } if(!edtPasswordRegister.getText().toString().equals(edtRepPasswordRegister.getText().toString())) {
            errores += getString(R.string.password_no_coinciden);
            todoOk = false;
        }

        if(!todoOk)
            Snackbar.make(findViewById(android.R.id.content), errores, Snackbar.LENGTH_LONG)
                    .show();

        return todoOk;
    }

}

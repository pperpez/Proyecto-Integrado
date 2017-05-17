package com.example.pablo.movieseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText edtUser, edtPassword;
    private TextView txtRegister;

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
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnLogin:
                // Petición a la base de datos (Comprobar si los datos existen)
                i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.txtRegister:
                i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
        }
        finish();
    }
}

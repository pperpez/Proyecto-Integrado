package com.example.pablo.movieseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtLogin;
    private EditText edtUserRegister, edtEmailRegister, edtPasswordRegister, edtRepPasswordRegister;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        Intent i;
        switch (v.getId()){
            case R.id.btnRegister:
                // Petición a la base de datos y validaciones (el usario y/o email no existe, campos de password coinciden)
                i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.txtLogin:
                i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                break;
        }
        finish();
    }
}

package com.matanhassin.matkonli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.matanhassin.matkonli.R;

public class login_page extends AppCompatActivity {

    EditText emailInput;
    EditText passwordInput;
    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        registerBtn = findViewById(R.id.login_Page_Register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterPage();
            }
        });

    }

    private void toRegisterPage() {
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }
}
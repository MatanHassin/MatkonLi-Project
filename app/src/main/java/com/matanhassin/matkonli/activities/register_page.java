package com.matanhassin.matkonli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.matanhassin.matkonli.R;

public class register_page extends AppCompatActivity {

    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        loginBtn = findViewById(R.id.register_Page_Login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginPage();
            }
        });

    }

    private void toLoginPage() {
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }
}
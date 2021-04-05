package com.matanhassin.matkonli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.matanhassin.matkonli.R;
import com.matanhassin.matkonli.model.ModelFirebase;
import com.matanhassin.matkonli.model.Utils;

public class register_page extends AppCompatActivity {

    EditText userName;
    EditText email;
    EditText password;
    ImageButton photoUpload;
    Button loginBtn;
    Button registerBtn;
    Uri profileImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        userName = findViewById(R.id.register_Page_Username_textInputLayout);
        email = findViewById(R.id.register_Page_Email_textInputLayout);
        password = findViewById(R.id.register_Page_Password_textInputLayout);
        photoUpload = findViewById(R.id.register_Page_Camera_imageButton);
        photoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.imageFromGallery(register_page.this);
            }
        });
        registerBtn = findViewById(R.id.register_Page_Register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFirebase.registerUser(userName.getText().toString(),password.getText().toString(),email.getText().toString(),profileImageUri,new ModelFirebase.Listener<Boolean>(){
                    @Override
                    public void onComplete() {
                        register_page.this.finish();
                    }

                    @Override
                    public void onFail() {

                    }
                });
            }
        });
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
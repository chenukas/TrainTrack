package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button BtnMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BtnMove = findViewById(R.id.signup);
        BtnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetosignup();

            }
        });
    }

    private void movetosignup() {
        Intent intent = new Intent(Login.this, form.class);
        startActivity(intent);
    }


}

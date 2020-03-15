package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button BtnMve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);

                finish();
            }
        }, 3000);

        BtnMve = findViewById(R.id.registration);

        BtnMve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToRegistration();

            }
        });
    }

    private void moveToRegistration(){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}

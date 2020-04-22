package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    Button plus;
    Button payNow;
    CheckBox cardCheck;
    private ArrayList<String> description;
    private ArrayList<String> cardResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardCheck = findViewById(R.id.checkBox2);
        plus = findViewById(R.id.button5);
        payNow = findViewById(R.id.button6);

        cardResult = new ArrayList<>();
        Intent intent = getIntent();
        final String Value1 = intent.getStringExtra("Value1");

        addNewCard();
        pay();
        tripDetails();

        cardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(cardCheck.isChecked())
                   cardResult.add("");
               else
                   cardResult.add("");
            }
        });
    }




    public void addNewCard(){


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, AddNewCard.class);
                startActivity(intent);
            }
        });
    }

    public void pay(){
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, PaymentHistory.class);
                startActivity(intent);
            }
        });
    }

    public void tripDetails(){


    }



}

package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    Button plus,payNow;
    RadioButton cardCheck;
    private ArrayList<String> description;
    private ArrayList<String> cardResult;
    String purchasedTicketId;
    double total;
    String s;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardCheck = findViewById(R.id.radioButtonCard);
        plus = findViewById(R.id.button5);
        payNow = findViewById(R.id.button6);

        //cardResult = new ArrayList<>();



        addNewCard();
        pay();
        tripDetails();

       /* cardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(cardCheck.isChecked())
                   final String cardNo = i.getStringExtra("s");
               else
                   cardResult.add("");
            }
        });*/
    }


    //Intent intent = getIntent();

    //final String card1 = intent.getStringExtra("card");



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
        Intent intent = getIntent();
        purchasedTicketId = intent.getStringExtra("ticketId");
        total = intent.getDoubleExtra("total",0);

        Toast.makeText(getApplicationContext(), "Ticket ID: " +purchasedTicketId + "\nTotal: "
                + Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();

    }

    public void tripDetails(){


    }



}

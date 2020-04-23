package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    String purchasedTicketId;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        purchasedTicketId = intent.getStringExtra("ticketId");
        total = intent.getDoubleExtra("total",0);

        Toast.makeText(getApplicationContext(), "Ticket ID: " +purchasedTicketId + "\nTotal: "
                + Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();
    }
}

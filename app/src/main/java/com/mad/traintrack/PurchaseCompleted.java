package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PurchaseCompleted extends AppCompatActivity {

    TextView textViewTicketId;
    String ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_completed);

        Intent intent = getIntent();
        ticketId = intent.getStringExtra("ticketId");

        textViewTicketId = findViewById(R.id.textView53);

        textViewTicketId.setText(ticketId);

        ticketId = "";
        Intent intentHome = new Intent(PurchaseCompleted.this, Home.class);
        startActivity(intentHome);

    }
}

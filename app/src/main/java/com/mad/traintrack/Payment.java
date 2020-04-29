package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    TextView textViewName, textViewTicketId, textViewTotal;
    Button paynow;
    String purchasedTicketId, customerName;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        purchasedTicketId = intent.getStringExtra("ticketId");
        total = intent.getDoubleExtra("total",0);
        customerName = intent.getStringExtra("customerName");

        textViewName = findViewById(R.id.textView50);
        textViewTicketId = findViewById(R.id.textView51);
        textViewTotal =findViewById(R.id.textView14);
        paynow = findViewById(R.id.button6);

        //Toast.makeText(getApplicationContext(), "Ticket ID: " +purchasedTicketId + "\nTotal: "+ Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();

        textViewName.setText(customerName);
        textViewTicketId.setText(purchasedTicketId);
        textViewTotal.setText(String.valueOf(total));

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Payment.this,PurchaseCompleted.class);
                intent2.putExtra("ticketId", purchasedTicketId);
                startActivity(intent2);

            }
        });


    }
}

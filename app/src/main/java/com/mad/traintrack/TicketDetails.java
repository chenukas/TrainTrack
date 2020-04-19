package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicketDetails extends AppCompatActivity {

    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        openPaymentGateway();
    }

    public void openPaymentGateway() {
        proceed = (Button) findViewById(R.id.button4);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketDetails.this, Payment.class);
                startActivity(intent);
            }
        });
    }


}

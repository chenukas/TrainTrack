package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {

    Button userprofile;
    Button purchaseticket;
    Button viewtickets;
    Button viewschedule;
    Button tripreminder;
    Button recentpayments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openPurchaseTicket();
        openPurchasedTickets();
        openViewSchedule();
        openTripReminder();
        openRecentPayments();
        openUserProfile();

    }

    public void openPurchaseTicket() {
        purchaseticket = (Button) findViewById(R.id.button11);

        purchaseticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SearchTrain.class);
                startActivity(intent);
            }
        });
    }

    public void openPurchasedTickets() {
        viewtickets = (Button) findViewById(R.id.button12);

        viewtickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PurchasedTickets.class);
                startActivity(intent);
            }
        });
    }

    public void openViewSchedule() {
        viewschedule = (Button) findViewById(R.id.button13);

        viewschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TrainSchedule.class);
                startActivity(intent);
            }
        });
    }

    public void openTripReminder() {
        tripreminder = (Button) findViewById(R.id.button14);

        tripreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ViewReminder.class);
                startActivity(intent);
            }
        });
    }

    public void openRecentPayments() {
        recentpayments = (Button) findViewById(R.id.button15);

        recentpayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PaymentHistory.class);
                startActivity(intent);
            }
        });
    }

    public void openUserProfile() {
        userprofile = (Button) findViewById( R.id.userprofile);

        userprofile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, UserDetails.class);
                startActivity(intent);

            }
        });
    }


}

package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewSchedule extends AppCompatActivity {

    TextView textViewTrainNo, textViewFrom, textViewTo, textViewFirst, textViewSecond, textViewThird;
    Button buyNow;
    String selectedRouteId, trainNo, trainFrom, trainTo, firstClass, secondClass, thirdClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        Intent intent = getIntent();
        selectedRouteId = intent.getStringExtra("selectedRouteId");

        textViewTrainNo = findViewById(R.id.textView62);
        textViewFrom = findViewById(R.id.textView63);
        textViewTo = findViewById(R.id.textView64);
        textViewFirst = findViewById(R.id.textView65);
        textViewSecond = findViewById(R.id.textView66);
        textViewThird = findViewById(R.id.textView67);
        buyNow = findViewById(R.id.purchaseSchedule);

        final DatabaseReference dbCheckRoute = FirebaseDatabase.getInstance().getReference("route");
        dbCheckRoute.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    Route route = dSnapshot.getValue(Route.class);
                    assert route != null;
                    if (selectedRouteId.equals(route.getRouteId())) {
                        trainNo = route.getTrainNo();
                        trainFrom = route.getFrom();
                        trainTo = route.getTo();
                        firstClass = String.valueOf(route.getFirstClass());
                        secondClass = String.valueOf(route.getSecondClass());
                        thirdClass = String.valueOf(route.getThirdClass());
                        break;
                    }
                }
                textViewTrainNo.setText(trainNo);
                textViewFrom.setText(trainFrom);
                textViewTo.setText(trainTo);
                textViewFirst.setText(firstClass);
                textViewSecond.setText(secondClass);
                textViewThird.setText(thirdClass);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPurchase = new Intent(ViewSchedule.this, TicketDetails.class);
                intentPurchase.putExtra("selectedRouteId",selectedRouteId);
                startActivity(intentPurchase);
            }
        });

    }
}

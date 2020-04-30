package com.mad.traintrack;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainSchedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> routeId, startTime;
    ArrayAdapter<CharSequence> AdapterFrom, AdapterTo;
    ArrayAdapter trainTime;
    Spinner spinnerStart, spinnerEnd;
    String beginning, destination;
    ListView trainList;
    Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        final DatabaseReference dbSearchRef = FirebaseDatabase.getInstance().getReference("route");

        routeId = new ArrayList<>();
        startTime = new ArrayList<>();
        spinnerStart = findViewById(R.id.From);
        spinnerEnd = findViewById(R.id.To);
        buttonSearch = findViewById(R.id.Search);
        trainList = findViewById(R.id.trainList);

        AdapterFrom = ArrayAdapter.createFromResource(this, R.array.stationsFrom, android.R.layout.simple_spinner_item);
        AdapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStart.setAdapter(AdapterFrom);
        AdapterTo = ArrayAdapter.createFromResource(this, R.array.stationsTo, android.R.layout.simple_spinner_item);
        AdapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnd.setAdapter(AdapterTo);
        spinnerStart.setOnItemSelectedListener(this);
        spinnerEnd.setOnItemSelectedListener(this);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeId.clear();
                startTime.clear();

                dbSearchRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dSnapshot: dataSnapshot.getChildren()){
                            Route route = dSnapshot.getValue(Route.class);
                            assert route != null;
                            if (beginning.equals(route.getFrom()) && destination.equals(route.getTo())) {
                                routeId.add(route.getRouteId());
                                startTime.add(route.getStartTime());
                            }
                        }

                        trainTime = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_list_item, startTime);
                        trainList.setAdapter(trainTime);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        trainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRouteId = routeId.get(position);
                Intent intent = new Intent(TrainSchedule.this, ViewSchedule.class);
                intent.putExtra("selectedRouteId", selectedRouteId);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        beginning = spinnerStart.getSelectedItem().toString();
        destination = spinnerEnd.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent(TrainSchedule.this,Home.class);
        startActivity(intent3);
    }
}

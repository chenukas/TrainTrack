package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchTrain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button search;
    ArrayList<String> routeId, startTime;
    ArrayAdapter<CharSequence> adapterFrom, adapterTo;
    ArrayAdapter time;
    Spinner spinnerFrom, spinnerTo ;
    String startStation, endStation;
    ListView searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("route");

        routeId = new ArrayList<>();
        startTime = new ArrayList<>();
        spinnerFrom = findViewById(R.id.spinner1);
        spinnerTo = findViewById(R.id.spinner2);

        search = findViewById(R.id.button8);
        searchResult = findViewById(R.id.searchResult);

        adapterFrom = ArrayAdapter.createFromResource(this, R.array.stationsFrom, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapterFrom);
        adapterTo = ArrayAdapter.createFromResource(this, R.array.stationsTo, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapterTo);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeId.clear();
                startTime.clear();
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                            Route route = dSnapshot.getValue(Route.class);
                            assert route != null;
                            if (startStation.equals(route.getFrom()) && endStation.equals(route.getTo())) {
                                routeId.add(route.getRouteId());
                                startTime.add(route.getStartTime());
                            }
                        }
                        time = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_list,startTime);
                        searchResult.setAdapter(time);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRouteId = routeId.get(position);
                Toast.makeText(getApplicationContext(), selectedRouteId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchTrain.this, TicketDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        startStation = spinnerFrom.getSelectedItem().toString();
        endStation = spinnerTo.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

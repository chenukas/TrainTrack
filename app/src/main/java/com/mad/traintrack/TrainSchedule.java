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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainSchedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button search;
    ArrayList<String> routeId, startTime;
    ArrayAdapter<CharSequence> adapterFrom, adapterTo;
    ArrayAdapter time;
    Spinner selectFrom, selectTo ;
    String startStation, endStation;
    ListView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("route");

        routeId = new ArrayList<>();
        startTime = new ArrayList<>();
        selectFrom = findViewById(R.id.selectFrom);
        selectTo = findViewById(R.id.selectTo);

        search = findViewById(R.id.btnSearch);
        result = findViewById(R.id.trainList);

        adapterFrom = ArrayAdapter.createFromResource(this, R.array.stationsFrom, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectFrom.setAdapter(adapterFrom);
        adapterTo = ArrayAdapter.createFromResource(this, R.array.stationsTo, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTo.setAdapter(adapterTo);
        selectFrom.setOnItemSelectedListener(this);
        selectTo.setOnItemSelectedListener(this);

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
                        time = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_list_item,startTime);
                        result.setAdapter(time);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRouteId = routeId.get(position);
                Intent intent = new Intent(TrainSchedule.this, TicketDetails.class);
                intent.putExtra("selectedRouteId", selectedRouteId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        startStation = selectFrom.getSelectedItem().toString();
        endStation = selectTo.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

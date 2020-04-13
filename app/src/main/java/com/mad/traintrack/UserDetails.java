package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetails extends AppCompatActivity {
    EditText txtfirstname, txtlastname, txtnic, txtdob, txtaddress, txtemail, txtphone, txtpassword;
    ToggleButton btnview;
    Button btnupdate;
    DatabaseReference dbRef;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        txtfirstname = findViewById(R.id.editText9);
        txtlastname = findViewById(R.id.editText11);
        txtnic = findViewById(R.id.editText12);
        txtdob = findViewById(R.id.editText14);
        txtaddress = findViewById(R.id.editText17);
        txtemail = findViewById(R.id.editText15);
        txtphone = findViewById(R.id.editText16);
        txtpassword = findViewById(R.id.editText18);

        btnview = findViewById(R.id.toggleButton);
        btnupdate = findViewById(R.id.button9);

        user = new User();

        btnview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                txtfirstname.setText(dataSnapshot.child("firstname").getValue().toString());
                                txtlastname.setText(dataSnapshot.child("lastname").getValue().toString());
                                txtnic.setText(dataSnapshot.child("nic").getValue().toString());
                                txtdob.setText(dataSnapshot.child("dob").getValue().toString());
                                txtaddress.setText(dataSnapshot.child("address").getValue().toString());
                                txtemail.setText(dataSnapshot.child("email").getValue().toString());
                                txtphone.setText(dataSnapshot.child("phone").getValue().toString());
                                txtpassword.setText(dataSnapshot.child("password").getValue().toString());
                            } else
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    txtfirstname.setVisibility(View.VISIBLE);
                    txtlastname.setVisibility(View.VISIBLE);
                    txtdob.setVisibility(View.VISIBLE);
                    txtnic.setVisibility(View.VISIBLE);
                    txtaddress.setVisibility(View.VISIBLE);
                    txtpassword.setVisibility(View.VISIBLE);
                    txtphone.setVisibility(View.VISIBLE);
                    txtemail.setVisibility(View.VISIBLE);
                } else {
                    txtfirstname.setVisibility(View.INVISIBLE);
                    txtlastname.setVisibility(View.INVISIBLE);
                    txtdob.setVisibility(View.INVISIBLE);
                    txtnic.setVisibility(View.INVISIBLE);
                    txtaddress.setVisibility(View.INVISIBLE);
                    txtpassword.setVisibility(View.INVISIBLE);
                    txtphone.setVisibility(View.INVISIBLE);
                    txtemail.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("User");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("user1")) {
                            try {
                                user.setFirstname(txtfirstname.getText().toString().trim());
                                user.setLastname(txtlastname.getText().toString().trim());
                                user.setNic(Integer.parseInt(txtnic.getText().toString().trim()));
                                user.setDob(txtdob.getText().toString().trim());
                                user.setAddress(txtaddress.getText().toString().trim());
                                user.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                                user.setEmail(txtemail.getText().toString().trim());
                                user.setPassword(txtpassword.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                                dbRef.setValue(user);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }


        private void clearControls() {
            txtfirstname.setText("");
            txtlastname.setText("");
            txtnic.setText("");
            txtdob.setText("");
            txtaddress.setText("");
            txtemail.setText("");
            txtphone.setText("");
            txtpassword.setText("");
        }

    }


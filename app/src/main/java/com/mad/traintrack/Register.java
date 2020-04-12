package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText txtfirstname, txtlastname, txtnic, txtdob, txtaddress, txtemail, txtphone, txtpassword;
    Button btnsignup;
    DatabaseReference dbRef;
    User user;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        txtfirstname = findViewById(R.id.firstname);
        txtlastname = findViewById(R.id.lastname);
        txtnic = findViewById(R.id.nic);
        txtdob = findViewById(R.id.dob);
        txtaddress = findViewById(R.id.address);
        txtemail = findViewById(R.id.email);
        txtphone = findViewById(R.id.phone);
        txtpassword = findViewById(R.id.password);

        btnsignup = findViewById(R.id.signup);

        user = new User();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("User");
                try {
                    if (TextUtils.isEmpty(txtfirstname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the First Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtlastname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Last Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtdob.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Date Of birth", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtnic.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the NIC", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtaddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtemail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtpassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Password", Toast.LENGTH_SHORT).show();
                    else {
                        user.setFirstname(txtfirstname.getText().toString().trim());
                        user.setLastname(txtlastname.getText().toString().trim());
                        user.setAddress(txtaddress.getText().toString().trim());
                        user.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                        user.setDob(txtdob.getText().toString().trim());
                        user.setEmail(txtemail.getText().toString().trim());
                        user.setNic(Integer.parseInt(txtnic.getText().toString().trim()));
                        user.setPassword(txtpassword.getText().toString().trim());

                        //dbRef.push().setValue(user);
                        dbRef.child("user1").setValue(user);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "Error. Please Try Again", Toast.LENGTH_SHORT).show();


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

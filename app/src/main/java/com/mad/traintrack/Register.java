package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText txtfirstname, txtlastname, txtnic, txtdob, txtaddress, txtemail, txtphone, txtpassword;
    Button btnsignup;
    DatabaseReference dbRef;
    User user;

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

                        dbRef.push().setValue(user);
                        //dbRef.child("std1").setValue(std);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        openlogin();
                        clearControls();
                    }

                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
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

    public void openlogin() {
        Button btn = (Button) findViewById( R.id.signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}

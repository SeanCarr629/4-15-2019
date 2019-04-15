package com.example.addnewinventoryitem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerTest extends AppCompatActivity {



String selectedItem, itemName;
EditText etItemNumber, etItemName;
DatabaseReference reff, reff2;
FirebaseDatabase database, database2;
DatabaseReference key;
Inventory inventory, inventory2;
Integer itemQuantity, count = 0;
Button btnSubmitNumber, btnSubmitItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);


        etItemNumber = findViewById(R.id.etItemNumber);
        btnSubmitItem = findViewById(R.id.btnSumbitItem);
        etItemName = findViewById(R.id.etItemName);


        database = FirebaseDatabase.getInstance();
        reff = database.getReference();





                ///Selected spinner item


        btnSubmitItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                {
                    database2 = FirebaseDatabase.getInstance();
                    reff2 = database.getReference("Inventory");


                   itemName = etItemName.getText().toString();

                    FirebaseDatabase.getInstance().getReference("Inventory")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Inventory inventory2 = snapshot.getValue(Inventory.class);


                                        if(inventory2.getItemName().equals(itemName)) {
                                            count = 1;

                                            Toast.makeText(SpinnerTest.this, "Item Already " +
                                                    "Exists", Toast.LENGTH_LONG).show();

                                        }



                                    }


                                    if (count == 0) {

                                        inventory = new Inventory();
                                        inventory.setItemName(itemName);
                                        inventory.setItemQuantity(0);
                                        reff2.push().setValue(inventory);
                                        Toast.makeText(SpinnerTest.this, "Item Added", Toast.LENGTH_LONG).show();

                                    }
                                    count = 0;

                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });






                }


            }




        });














        }

    }








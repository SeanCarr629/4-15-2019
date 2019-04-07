package com.example.addnewinventoryitem;

import android.content.Intent;
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
        btnSubmitNumber = findViewById(R.id.btnSumbitNmbr);

        database = FirebaseDatabase.getInstance();
        reff = database.getReference();

        FirebaseDatabase.getInstance().getReference("Inventory").addValueEventListener
                (new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> inventoryList = new ArrayList<String>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Inventory inventory = snapshot.getValue(Inventory.class);
                    if (inventory.getItemName()!=null){
                        inventoryList.add(inventory.getItemName());

                    }
                }

                Spinner spinnerProperty = (Spinner) findViewById(R.id.spinner1);
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(SpinnerTest.this, android.R.layout.simple_spinner_item, inventoryList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProperty.setAdapter(addressAdapter);


                ///Selected spinner item

                spinnerProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem = (String) parent.getItemAtPosition(position);

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                             inventory = snapshot.getValue(Inventory.class);


                            if (inventory.getItemName().equals(selectedItem)) {
                                key = snapshot.getRef();


                               //Toast.makeText(SpinnerTest.this, "Key " + key, Toast.LENGTH_LONG).show();

                            }
                        }

                        // Notify the selected item text
                       /* Toast.makeText
                                (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                                .show(); */
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



       btnSubmitNumber.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {


              itemQuantity = Integer.parseInt(etItemNumber.getText().toString());
               key.child("itemQuantity").setValue(itemQuantity);



            //  Toast.makeText(SpinnerTest.this, "Key " + key, Toast.LENGTH_LONG).show();
             // Toast.makeText(SpinnerTest.this, "ItemQ " + itemQuantity, Toast.LENGTH_LONG).show();

           }


        });






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








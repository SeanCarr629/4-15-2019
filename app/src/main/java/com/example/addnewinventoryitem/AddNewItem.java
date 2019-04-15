package com.example.addnewinventoryitem;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddNewItem extends AppCompatActivity {

    EditText etItemName, etItemQuantity;
    Button btnSubmit;
    DatabaseReference reff;
    Inventory inventory;
    FirebaseDatabase database;
    String itemName;
    Integer itemQuantity;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item2);

        etItemName = (EditText)  findViewById(R.id.etItemName);

        etItemQuantity = (EditText) findViewById(R.id.etItemQuantity);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        inventory = new Inventory();



        database = FirebaseDatabase.getInstance();
         reff = database.getReference("Inventory");

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                {

                    itemName = etItemName.getText().toString();
                    itemQuantity = Integer.parseInt(etItemQuantity.getText().toString());



                    FirebaseDatabase.getInstance().getReference("Inventory")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Inventory inventory = snapshot.getValue(Inventory.class);


                                        if(inventory.getItemName().equals(itemName)) {
                                            count = 1;
                                            inventory.setItemQuantity(itemQuantity);
                                            reff.child(snapshot.getKey()).setValue(inventory);

                                            Toast.makeText(AddNewItem.this, "Item Updated",
                                                    Toast.LENGTH_LONG).show();

                                        }



                                    }


                                    if (count == 0) {
                                        inventory.setItemName(itemName);
                                        inventory.setItemQuantity(itemQuantity);
                                        reff.push().setValue(inventory);
                                        Toast.makeText(AddNewItem.this, "Item Added", Toast.LENGTH_LONG).show();

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
package com.example.addnewinventoryitem;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderForm extends AppCompatActivity {


   EditText etDate, etQuantity;
   Button btnSubmit;
   FirebaseDatabase database;
   DatabaseReference reff;
   String date, itemName, selectedItem, spinnerItem, date2;
   Spinner spinner;
   Integer quantity;
   DatePickerDialog.OnDateSetListener dateSetListener;
   Integer count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        etDate = findViewById(R.id.etDate);
        etQuantity = findViewById(R.id.etQuantity);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinner = findViewById(R.id.spinner);

        database = FirebaseDatabase.getInstance();
        reff = database.getReference("NewOrders");

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(OrderForm.this,
                        android.R.style.Theme_Holo, dateSetListener, year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
              date2 = month + "/" + dayOfMonth + "/" + year;
               etDate.setText(date2);


            }
        };





      FirebaseDatabase.getInstance().getReference("Inventory")
              .addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(final DataSnapshot dataSnapshot) {

              final List<String> inventoryList = new ArrayList<String>();

              for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                  Inventory inventory = snapshot.getValue(Inventory.class);
                  if (inventory.getItemName()!=null){
                      inventoryList.add(inventory.getItemName());

                  }
              }

              Spinner spinnerProperty = (Spinner) findViewById(R.id.spinner);
              ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(OrderForm.this, android.R.layout.simple_spinner_item, inventoryList);
              addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              spinnerProperty.setAdapter(addressAdapter);


              spinnerProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      selectedItem = (String) parent.getItemAtPosition(position);



                      for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                          Inventory inventory = snapshot.getValue(Inventory.class);

                          if (inventory.getItemName().equals(selectedItem)) {
                              spinnerItem = inventory.itemName;

                              Toast.makeText(OrderForm.this, "Item " + spinnerItem
                                      , Toast.LENGTH_LONG).show();

                          }
                      }
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





        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = etDate.getText().toString();
                quantity = Integer.parseInt(etQuantity.getText().toString());



        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);


                    if(order.getDate().equals(date2)) {
                        count = 1;
                        Inventory item = new Inventory();
                        item.setItemName(spinnerItem);
                        item.setItemQuantity(quantity);
                        order.addItem(item);
                        reff.child(snapshot.getKey()).setValue(order);

                      //  Toast.makeText(AddNewItem.this, "Item Updated",
                        //        Toast.LENGTH_LONG).show();

                    }




                }

                if (count == 0) {
                    Inventory item = new Inventory();
                    item.setItemName(spinnerItem);
                    item.setItemQuantity(quantity);
                    Order order = new Order(date, item);
                    reff.push().setValue(order);
                }



                count = 0;




                            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




            }
        });
















    }
}

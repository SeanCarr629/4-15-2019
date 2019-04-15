package com.example.addnewinventoryitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class ProductAdapter extends ArrayAdapter<Inventory> {

    private  Context context;
    private ArrayList<Inventory> values;


   public ProductAdapter(Context context, ArrayList<Inventory> list)
   {
       super(context, R.layout.row_layout, list);
       this.context = context;
       this.values = list;

   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView tvProduct = (TextView) rowView.findViewById(R.id.tvProduct);
        TextView tvAmount = (TextView) rowView.findViewById(R.id.tvAmount);


        tvProduct.setText(values.get(position).getItemName());
        tvAmount.setText(values.get(position).getItemQuantity().toString());





       return rowView;
    }

    public void refreshEvents(ArrayList<Inventory> values) {
        this.values.clear();
        this.values.addAll(values);
        notifyDataSetChanged();
    }
}

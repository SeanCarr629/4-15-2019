package com.example.addnewinventoryitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {



    private Context context;
    private ArrayList<Order> values;


    public OrderAdapter(Context context, ArrayList<Order> list)
    {

        super(context, R.layout.row_layout_order,list);
        this.context = context;
        this.values = list;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout_order, parent, false);


        TextView tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        TextView tvProduct = (TextView) rowView.findViewById(R.id.tvProduct);
        TextView tvAmount = (TextView) rowView.findViewById(R.id.tvAmount);


     //   tvDate.setText(values.get(position).getDate());


           tvProduct.setText(values.get(position).getDate());








        return rowView;
    }

    public void refreshEvents(ArrayList<Order> values) {
        this.values.clear();
        this.values.addAll(values);
        notifyDataSetChanged();
    }
}








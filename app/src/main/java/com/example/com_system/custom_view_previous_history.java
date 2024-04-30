package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.security.PrivateKey;

public class custom_view_previous_history extends BaseAdapter {
    String[]hid,date,payment_date,payment_status,subcription_details,amount;
    private Context context;

    public custom_view_previous_history(Context applicationContext, String[] hid, String[] date, String[] payment_date, String[] payment_status, String[] subcription_details, String[] amount) {
        this.context = applicationContext;
        this.hid = hid;
        this.date = date;
        this.payment_date = payment_date;
        this.payment_status = payment_status;
        this.subcription_details = subcription_details;
        this.amount = amount;

    }


    @Override
    public int getCount() {
        return amount.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_previous_history,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView42);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView46);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView48);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView50);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(date[i]);
        tv2.setText(payment_date[i]);
        tv3.setText(payment_status[i]);
        tv4.setText(subcription_details[i]);
        tv5.setText(amount[i]);




        return gridView;
    }
}
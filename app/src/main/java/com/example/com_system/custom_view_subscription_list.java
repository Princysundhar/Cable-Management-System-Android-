package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_subscription_list extends BaseAdapter {
    public static  String[] subcription_id, type, amount, status, date, expiry_date, customer_info;
    private Context context;
    String url;
    public static int pos =0;
    String payment_status = "pending";

    public custom_view_subscription_list(Context applicationContext, String[] subcription_id, String[] type, String[] amount, String[] status, String[] date, String[] expiry_date, String[] customer_info) {
        this.context = applicationContext;
        this.subcription_id = subcription_id;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.expiry_date = expiry_date;
        this.customer_info = customer_info;
    }

    @Override
    public int getCount() {
        return customer_info.length;
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
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_subscription_list, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView32);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView34);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView36);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView38);


        Button b1 = (Button) gridView.findViewById(R.id.button8);
        Button b2 = (Button) gridView.findViewById(R.id.button7); // invoice
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("subcription_id",subcription_id[i]);
                ed.commit();
                Intent i = new Intent(context,invoice.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });
        if(status[i].equalsIgnoreCase("active")){
            b1.setEnabled(true);
        }
        else{

            b1.setEnabled(false);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("subcription_id",subcription_id[i]);
                ed.putString("amount",amount[i]);
                ed.commit();
                Intent i = new Intent(context,payment_mode.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);


        tv1.setText(type[i]);
        tv2.setText(amount[i]);
        tv3.setText(status[i]);
        tv4.setText(date[i]);
        tv5.setText(expiry_date[i]);
        tv6.setText(customer_info[i]);


        return gridView;
    }
}
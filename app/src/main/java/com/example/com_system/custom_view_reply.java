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

public class custom_view_reply extends BaseAdapter {
    String[] cid, complaint, date, reply, reply_date;
    private Context context;


    public custom_view_reply(Context applicationContext, String[] cid, String[] complaint, String[] date, String[] reply, String[] reply_date) {
        this.context = applicationContext;
        this.cid = cid;
        this.complaint = complaint;
        this.date = date;
        this.reply = reply;
        this.reply_date = reply_date;

    }

    @Override
    public int getCount() {
        return reply.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_reply, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView19);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView21);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView23);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView25);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(complaint[i]);
        tv2.setText(date[i]);
        tv3.setText(reply[i]);
        tv4.setText(reply_date[i]);


        return gridView;
    }
}
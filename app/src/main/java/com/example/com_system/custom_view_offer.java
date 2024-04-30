package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class custom_view_offer extends BaseAdapter {
    String[]offer_id,type,title,offer;
    private Context context;


    public custom_view_offer(Context applicationContext, String[] offer_id, String[] type, String[] title, String[] offer) {
        this.context = applicationContext;
        this.offer_id = offer_id;
        this.type = type;
        this.title = title;
        this.offer = offer;

    }

    @Override
    public int getCount() {
        return offer.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_offer,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView19);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView21);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView23);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(type[i]);
        tv2.setText(title[i]);
        tv3.setText(offer[i]);




        return gridView;

    }
}
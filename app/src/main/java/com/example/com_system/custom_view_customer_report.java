package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class custom_view_customer_report extends BaseAdapter {
    String[] subcription_id, type, amount, status, date, expiry_date, customer_info;
    private Context context;


    public custom_view_customer_report(Context applicationContext, String[] subcription_id, String[] type, String[] amount, String[] status, String[] date, String[] expiry_date, String[] customer_info) {
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
        return amount.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_report, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView32);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView34);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView36);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView38);

        Button b2 = (Button) gridView.findViewById(R.id.button7); // invoice
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("subcription_id",subcription_id[i]);
                ed.commit();
                Intent i = new Intent(context,invoice.class);
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
    public void openFile(String url) {

        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if (url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            //if you want you can also define the intent type for any other file
            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "/");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
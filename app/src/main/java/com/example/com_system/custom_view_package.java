package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaParser;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_package extends BaseAdapter {
    String[] package_id, type, name, amount;
    private Context context;

    public custom_view_package(Context applicationContext, String[] package_id, String[] type, String[] name, String[] amount) {
        this.context = applicationContext;
        this.package_id = package_id;
        this.type = type;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_package, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView19);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView21);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView23);
        Button b1 = (Button) gridView.findViewById(R.id.button6);
//        Button b2 = (Button) gridView.findViewById(R.id.button10);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                sh.getString("ipaddress","");
//                String url = sh.getString("url","") + "and_send_request";
//
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                // response
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        Toast.makeText(context, "Requested Semded", Toast.LENGTH_SHORT).show();
//                                        Intent i = new Intent(context,Customer_Home.class);
//                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        context.startActivity(i);
//
//                                    }
//
//                                    // }
//                                    else {
//                                        Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
//                                    }
//
//                                }    catch (Exception e) {
//                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("package_id",package_id[i]);
//
//                        params.put("amount",amount[i]);
//                        params.put("lid",sh.getString("lid",""));
//                        return params;
//                    }
//                };
//
//                int MY_SOCKET_TIMEOUT_MS=100000;
//
//                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);
//
//            }
//        });
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("package_id",package_id[i]);
                ed.commit();
                Intent i = new Intent(context,view_subscription_list.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(type[i]);
        tv2.setText(name[i]);
        tv3.setText(amount[i]);


        return gridView;
    }
}
package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class add_subscription_list extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    SharedPreferences sh;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription_list);
        e1 = findViewById(R.id.editTextTextPersonName6);
        e2 = findViewById(R.id.editTextNumber2);
        b1 = findViewById(R.id.button9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = e1.getText().toString();
                String amount = e2.getText().toString();
                int flag=0;
                if(type.equalsIgnoreCase("")){
                    e1.setError("Required");
                    flag++;
                }
                if(amount.equalsIgnoreCase("")){
                    e2.setError("Required");
                    flag++;
                }
                if(flag==0){
                    sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sh.getString("ipaddress","");
                    url = sh.getString("url","") + "/and_customer_add_subscription_list";

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    // response
                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                            Toast.makeText(add_subscription_list.this, "", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(),view_subscription_list.class);
                                            startActivity(i);

                                        }

                                        // }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                        }

                                    }    catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("type",type);
                            params.put("amount",amount);
                            params.put("lid",sh.getString("lid",""));
                            params.put("package_id",sh.getString("package_id",""));
                            return params;
                        }
                    };

                    int MY_SOCKET_TIMEOUT_MS=100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);
                }

            }
        });
    }
}
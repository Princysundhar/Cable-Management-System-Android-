package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6, e7,e8;
    Button b1;
    SharedPreferences sh;
    ImageView img;
    Spinner sp1;
    Bitmap bitmap = null;
    ProgressDialog pd;
    String[] area_id,area_name;
    String url,url1,ad;
    String number_pattern="[A-Z0-9]{11}";
    String MobilePattern = "[6-9][0-9]{9}";
    String PinPattern = "[6-9][0-9]{5}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String password_pattern="[A-Za-z0-9]{3,8}";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        e1 = findViewById(R.id.editTextTextPersonName2);
        e2 = findViewById(R.id.editTextTextPersonName3);
        e3 = findViewById(R.id.editTextTextPersonName4);
        e4 = findViewById(R.id.editTextNumber);
        e5 = findViewById(R.id.editTextTextEmailAddress2);
        e6 = findViewById(R.id.editTextPhone);
        e7 = findViewById(R.id.editTextTextPassword2);
        e8 = findViewById(R.id.editTextTextPersonName8);
        sp1 = findViewById(R.id.spinner);
        img = findViewById(R.id.imageView2);
        b1 = findViewById(R.id.button3);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url=sh.getString("url","")+"/and_view_area";

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url1=sh.getString("url","")+"/and_customer_register";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//view service code8
                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                area_id = new String[js.length()];
                                area_name = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    area_id[i] = u.getString("area_id");//dbcolumn name
                                    area_name[i] = u.getString("area_name");


                                }



                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, area_name);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp1.setAdapter(adapter);

                            }



                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
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

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();



                return params;
            }
        };


        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                String place = e2.getText().toString();
                String post = e3.getText().toString();
                String pin = e4.getText().toString();
                String email = e5.getText().toString();
                String contact = e6.getText().toString();
                String password = e7.getText().toString();
                String area = sp1.getSelectedItem().toString();
                String number = e8.getText().toString();
                int flag=0;
                if(name.equalsIgnoreCase("")){
                    e1.setError("Required");
                    flag++;
                }
                if(place.equalsIgnoreCase("")){
                    e2.setError("Required");
                    flag++;
                }
                if(post.equalsIgnoreCase("")){
                    e3.setError("Required");
                    flag++;
                }
                if(!pin.matches(PinPattern)){
                    e4.setError("Enter pin with valid pattern");
                    flag++;
                }
                if(!email.matches(emailPattern)){
                    e5.setError("Enter Email with valid pattern");
                    flag++;
                }
                if(!contact.matches(MobilePattern)){
                    e6.setError("Enter phone no with valid pattern");
                    flag++;
                }
                if(!password.matches(password_pattern)){
                    e7.setError("Enter Password with valid pattern");
                    flag++;
                }
                if(!number.matches(number_pattern)){
                    Toast.makeText(Register.this, "Use proper format:3 Uppercase letters & 8 digits", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                if(bitmap==null){
                    Toast.makeText(Register.this, "Choose Image", Toast.LENGTH_SHORT).show();
                }
                if(flag==0) {
                    uploadBitmap(name, place, post, pin, email, contact, password, area,number);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                img.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final String name, final String place, final String post, final String pin, final String email, final String contact, final String password,final String area,final String number) {


        pd = new ProgressDialog(Register.this);
        pd.setMessage("Registering....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url1,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            pd.dismiss();


                            JSONObject obj = new JSONObject(new String(response.data));

                            if (obj.getString("status").equals("ok")) {
                                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("name", name);//passing to python
                params.put("place", place);//passing to python
                params.put("post", post);
                params.put("pin", pin);
                params.put("email", email);
                params.put("contact", contact);
                params.put("password", password);
                params.put("area", area);//passing to python
                params.put("number", number);//passing to python

                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ad=area_id[pos];

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




    }



}

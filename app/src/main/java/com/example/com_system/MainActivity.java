package com.example.com_system;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipaddress = e1.getText().toString();
                int flag=0;
                if(ipaddress.equalsIgnoreCase("")){
                    e1.setError("Required");
                    flag++;
                }
                if(flag==0){
                    String url = "http://" + ipaddress +":8000";
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString("ipaddress",ipaddress);
                    ed.putString("url",url);
                    ed.commit();
                    Intent i= new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }
            }
        });
    }
}
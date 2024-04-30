package com.example.com_system;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.com_system.databinding.ActivityCustomerHomeBinding;
import com.squareup.picasso.Picasso;

public class Customer_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityCustomerHomeBinding binding;
    ImageView img;
    TextView tv1,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarCustomerHome.toolbar);
//        binding.appBarCustomerHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // navheader section

        View headerView = navigationView.getHeaderView(0);
        tv1 = headerView.findViewById(R.id.textView1);
        tv2 = headerView.findViewById(R.id.textView);
        img = headerView.findViewById(R.id.imageView);
//
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ipaddress", "");

        String url = "http://" + ip + ":8000" + sh.getString("photo","");    // For Image

        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);//circle

        tv1.setText(sh.getString("name",""));
        tv2.setText(sh.getString("email",""));


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_customer_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer__home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_customer_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_view_profile){
            Intent i = new Intent(getApplicationContext(),view_profile.class);
            startActivity(i);
        }
        if(id==R.id.nav_change_password){
            Intent i = new Intent(getApplicationContext(),change_password.class);
            startActivity(i);
        }
        if(id==R.id.nav_view_package){
            Intent i = new Intent(getApplicationContext(),view_package.class);
            startActivity(i);
        }
        if(id==R.id.nav_view_history){
            Intent i = new Intent(getApplicationContext(),view_previous_history.class);
            startActivity(i);
        }
        if(id==R.id.nav_offer){
            Intent i = new Intent(getApplicationContext(),view_offer.class);
            startActivity(i);
        }
        if(id==R.id.nav_view_reply){
            Intent i = new Intent(getApplicationContext(),view_reply.class);
            startActivity(i);
        }
//        if(id==R.id.nav_report){
//            Intent i = new Intent(getApplicationContext(),view_customer_report.class);
//            startActivity(i);
//        }
        if(id==R.id.nav_logout){
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.commit();
            ed.clear();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        return false;
    }
}
package com.midstatemusic.repairtag_v4.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info.editing = false;
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView title = findViewById(R.id.textViewTitle);
        TextView main = findViewById(R.id.textViewMain);

        String formattedTextTitle = "<body><h1>Welcome " + Info.employeeFirstName + " " + Info.employeeLastName + " " + getString(R.string.main_title) + "</h1></body>";
        String formattedTextMain = "<body><p><h4>" + getString(R.string.main_instructions) + "</h4></p><p>For Technical Assistance Contact: <br>Michael Farden</br>" +
                "<br>Phone: (315) 532-3602</br> <br>Email: mfarden@gmail.com</br> </p></body>";

        title.setText(Html.fromHtml(formattedTextTitle));
        main.setText(Html.fromHtml(formattedTextMain));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;
            case R.id.nav_create:
                Info.editing = false;
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                break;
            case R.id.nav_edit:
                Info.printing = false;
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.nav_print:
                Info.printing = true;
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.nav_settings:
                if (Info.employeeID.equals(Info.adminID)) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                }
                break;
            case R.id.nav_logout:
                startActivity(new Intent(MainActivity.this, PinActivity.class));
                break;
            default:
                break;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }, 50);
        return true;
    }
}

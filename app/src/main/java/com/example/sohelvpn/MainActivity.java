package com.example.sohelvpn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MaterialCardView button;
    ImageButton setting, naviOpenClose;
    NavigationView naviView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        setting = findViewById(R.id.setting);
        button = findViewById(R.id.moreSever);


        //navigation Drawer
        naviView = findViewById(R.id.navi_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        naviOpenClose = findViewById(R.id.naviOpenClose);
        drawerLayout.bringToFront();
        naviView.setCheckedItem(R.id.AllServer);
        naviView.setNavigationItemSelectedListener(this);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Setting_Activity.class));
            }
        });
        naviOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }
    public void next(View view) {
        startActivity(new Intent(MainActivity.this, ServerList_Activity.class));
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.AllServer) {
            startActivity(new Intent(MainActivity.this, ServerList_Activity.class));
        } else if (id == R.id.Setting) {
            startActivity(new Intent(MainActivity.this, Setting_Activity.class));
        } else if (id==R.id.About){
            startActivity(new Intent(MainActivity.this, Setting_Activity.class));
        } else if (id==R.id.Contact){
            startActivity(new Intent(MainActivity.this, Setting_Activity.class));
        }
        return true;
    }
}
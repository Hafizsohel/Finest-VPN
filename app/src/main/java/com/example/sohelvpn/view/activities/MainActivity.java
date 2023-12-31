package com.example.sohelvpn.view.activities;

import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sohelvpn.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MaterialCardView button;
    ImageButton setting, naviOpenClose,startVpnButton,stopVpnButton;
    NavigationView naviView;
    DrawerLayout drawerLayout;
    private boolean vpnStart = false;
    private boolean isServerSelected = false;


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

        startVpnButton = findViewById(R.id.startVpn);
        stopVpnButton = findViewById(R.id.stopVpn);

        //navigation Drawer
        naviView = findViewById(R.id.navi_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        naviOpenClose = findViewById(R.id.naviOpenClose);
        drawerLayout.bringToFront();
        naviView.setCheckedItem(R.id.AllServer);
        naviView.setNavigationItemSelectedListener(this);

        startVpnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVpnConnection();
            }
        });
        stopVpnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVpnConnection();
            }
        });

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
            startActivity(new Intent(MainActivity.this, AboutActivity.class));

        } else if (id==R.id.Contact){
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
        }
        return true;
    }

    private void startVpnConnection() {
        Intent vpnIntent = VpnService.prepare(MainActivity.this);
        if (vpnIntent != null) {
            startActivityForResult(vpnIntent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }
    }

    private void stopVpnConnection() {
        Intent vpnServiceIntent = new Intent(this, MyVpnService.class);
        stopService(vpnServiceIntent);
        startVpnButton.setVisibility(View.VISIBLE);
        stopVpnButton.setVisibility(View.GONE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent vpnServiceIntent = new Intent(this, MyVpnService.class);
            startService(vpnServiceIntent);

            startVpnButton.setVisibility(View.GONE);
            stopVpnButton.setVisibility(View.VISIBLE);
        }
    }
}
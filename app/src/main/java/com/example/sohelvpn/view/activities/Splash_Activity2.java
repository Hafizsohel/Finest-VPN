package com.example.sohelvpn.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sohelvpn.R;

public class Splash_Activity2 extends AppCompatActivity {

    Button NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        NextButton=findViewById(R.id.startedBtn);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash_Activity2.this,MainActivity.class));
                finish();
            }
        });
    }
}
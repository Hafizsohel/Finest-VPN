/*
package com.example.sohelvpn.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sohelvpn.R;



public class ContactActivity extends AppCompatActivity {

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        EditText editTextBody=findViewById(R.id.editTextBody);
        Button sendEmailButton = findViewById(R.id.sendEmailButton);
        backButton = findViewById(R.id.backBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subject = "Contact us";
                String body = editTextBody.getText().toString();

                if (body.trim().isEmpty()) {
                    Toast.makeText(ContactActivity.this, "Please write a body.", Toast.LENGTH_SHORT).show();
                } else {
                    // The code provided earlier for opening the email client.
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sohel.hafizurrahman@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject); // Add the subject to the intent
                    intent.putExtra(Intent.EXTRA_TEXT, body);

                    Intent chooser = Intent.createChooser(intent, "Send email ...");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        Log.d("EmailIntent", "Email app found. Starting intent.");
                        startActivity(chooser);
                    } else {
                        Log.e("EmailIntent", "No email app found to handle the intent.");
                    }

                }
            }
        });
    }
}*//*


package com.example.sohelvpn.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.sohelvpn.R;
import com.example.sohelvpn.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    ViewDataBinding binding;
    ImageView backButton;
    EditText editTextBody;

    */
/*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this, R.layout.activity_contact);

        EditText editTextBody = findViewById(R.id.editTextBody);
        Button sendEmailButton = findViewById(R.id.sendEmailButton);
        backButton = findViewById(R.id.backBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Contact us";
                String body = editTextBody.getText().toString();

                if (body.trim().isEmpty()) {
                    Toast.makeText(ContactActivity.this, "Please write a body.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@developer.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "Email body");

                    intent.setPackage("com.google.android.gm");

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(ContactActivity.this, "Gmail app is not installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }*//*


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);

        //backButton = binding.sendEmailButton;

        editTextBody=findViewById(R.id.editTextBody);
        Button sendEmailButton = findViewById(R.id.sendEmailButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Contact us";
                String body = binding.editTextBody.getText().toString();

                if (body.trim().isEmpty()) {
                    Toast.makeText(ContactActivity.this, "Please write a body.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@developer.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "Email body");

                    intent.setPackage("com.google.android.gm");

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(ContactActivity.this, "Gmail app is not installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}

*/

package com.example.sohelvpn.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sohelvpn.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    ActivityContactBinding binding;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backButton = binding.backBtn;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Contact us";
                String body = binding.editTextBody.getText().toString();

                if (body.trim().isEmpty()) {
                    Toast.makeText(ContactActivity.this, "Please write a body.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@developer.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    intent.putExtra(Intent.EXTRA_TEXT, body);


                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(ContactActivity.this, "No email app found to handle the intent.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}


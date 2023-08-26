package com.example.sohelvpn.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sohelvpn.Adapters.ServerListAdapter;
import com.example.sohelvpn.Model.ServerInfo;
import com.example.sohelvpn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServerList_Activity extends AppCompatActivity {
    RecyclerView serverListRCV;
    ArrayList<ServerInfo> allServers;
    ServerListAdapter serverListAdapter;
    ImageView backButton;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        serverListRCV = findViewById(R.id.ServerRecycleView);
        serverListRCV.setLayoutManager(new LinearLayoutManager(this));
        allServers = parseJasonData();
        serverListAdapter = new ServerListAdapter(this, allServers);

        serverListRCV.setAdapter(serverListAdapter);
        searchEditText = findViewById(R.id.searchEditText);
        View searchView = findViewById(R.id.searchView);
        backButton = findViewById(R.id.backButton);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                performSearch(s.toString());
            }
        });


      backButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        onBackPressed();
    }
    });
        }

    private ArrayList<ServerInfo> parseJasonData() {
        ArrayList<ServerInfo>vpnInfoList= new ArrayList<>();

        try {
            ArrayList<ServerInfo>ServerList= new ArrayList<>();
            InputStream inputStream=getAssets().open("server_list.json");
            int size=inputStream.available();
            byte[]buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonData=new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String flag=jsonObject.getString("flag");
                String country=jsonObject.getString("country");
                String ip=jsonObject.getString("ip");
                int port=jsonObject.getInt("port");
                int ping=jsonObject.getInt("ping");

             ServerInfo serverInfo= new ServerInfo(country,flag,ip,port,ping);
             vpnInfoList.add(serverInfo);
            }
        }catch (IOException |JSONException e){
            e.printStackTrace();
        }
        return vpnInfoList;
    }
    public void back(View view){
        finish();
    }

    private void performSearch(String query) {
        ArrayList<ServerInfo> filteredServers = new ArrayList<>();

        for (ServerInfo server : allServers) {
            String countryName = server.getCountry();

            if (countryName.toLowerCase().startsWith(query.toLowerCase())) {
                filteredServers.add(server);
            }
        }

        serverListAdapter.updateList(filteredServers);
    }

}
package com.example.sohelvpn.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    SearchView searchView;

    // Create a copy of the original server list to preserve it
    ArrayList<ServerInfo> originalServerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        serverListRCV = findViewById(R.id.ServerRecycleView);
        serverListRCV.setLayoutManager(new LinearLayoutManager(this));
        allServers = parseJsonData();

        // Make a copy of the original server list
        originalServerList = new ArrayList<>(allServers);

        serverListAdapter = new ServerListAdapter(this, allServers);
        serverListRCV.setAdapter(serverListAdapter);

        searchView = findViewById(R.id.search_view);
        backButton = findViewById(R.id.backButton);

        setupSearchView();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private ArrayList<ServerInfo> parseJsonData() {
        ArrayList<ServerInfo> vpnInfoList = new ArrayList<>();

        try {
            InputStream inputStream = getAssets().open("server_list.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonData = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String flag = jsonObject.getString("flag");
                String country = jsonObject.getString("country");
                String ip = jsonObject.getString("ip");
                int port = jsonObject.getInt("port");
                int ping = jsonObject.getInt("ping");

                ServerInfo serverInfo = new ServerInfo(country, flag, ip, port, ping);
                vpnInfoList.add(serverInfo);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return vpnInfoList;
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle query text change here
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String newText) {
        List<ServerInfo> filterList = new ArrayList<>();

        for (ServerInfo server : originalServerList) {
            String countryName = server.getCountry().toLowerCase();

            if (countryName.startsWith(newText.toLowerCase())) {
                filterList.add(server);
            }
        }

        serverListAdapter.updateList(filterList);
    }

    public void back(View view) {
        finish();
    }
}

package com.example.sohelvpn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.sohelvpn.Adapters.ServerListAdapter;
import com.example.sohelvpn.Model.ServerInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServerList_Activity extends AppCompatActivity {
    RecyclerView serverListRCV;
    ArrayList<ServerInfo> ServerList;
    ServerListAdapter serverListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);
        serverListRCV = findViewById(R.id.ServerRecycleView);
        serverListRCV.setLayoutManager(new LinearLayoutManager(this));
        ServerList = parseJasonData();
        serverListAdapter = new ServerListAdapter(this, ServerList);
        serverListRCV.setAdapter(serverListAdapter);
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
}
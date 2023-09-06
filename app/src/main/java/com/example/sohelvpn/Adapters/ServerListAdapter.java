package com.example.sohelvpn.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sohelvpn.Model.ServerInfo;
import com.example.sohelvpn.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<ServerInfo> serverList;

    public ServerListAdapter(Context context, ArrayList<ServerInfo> serverList) {
        this.context = context;
        this.serverList = serverList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.server_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView  ") int position) {
        ServerInfo model = serverList.get(position);

        holder.CountryName.setText(model.getCountry());
        Glide.with(context).load(model.getFlagUrl()).into(holder.CountryImage);
        holder.PingsImage.setImageResource(getSignalDrawable(model.getPings()));
    }

    private int getSignalDrawable(int servers) {
        if (servers <= 10) {
            return R.drawable.wifi4;
        } else if (servers <= 20) {
            return R.drawable.wifi3;
        } else if (servers <= 30) {
            return R.drawable.wifi2;
        } else {
            return R.drawable.wifi1;
        }
    }


    @Override
    public int getItemCount() {
    return serverList !=null? serverList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView  PingsImage;
        CircleImageView CountryImage;
        TextView CountryName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            CountryImage=itemView.findViewById(R.id.CountryIcon);
            PingsImage=itemView.findViewById(R.id.PingsImage);
            CountryName=itemView.findViewById(R.id.CountryName);
        }
    }
   /* public void updateList(ArrayList<ServerInfo> newList) {
        serverList.clear();
        serverList.addAll(newList);
        notifyDataSetChanged();
    }*/


    public void updateList(List<ServerInfo> newList) {
        serverList.clear();
        serverList.addAll(newList);
        notifyDataSetChanged();
    }
}

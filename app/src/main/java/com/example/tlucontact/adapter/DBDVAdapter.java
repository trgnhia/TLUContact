
package com.example.tlucontact.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;
import com.example.tlucontact.activity.DBDVActivity;
import com.example.tlucontact.entity.DBDV;

import java.util.ArrayList;

public class DBDVAdapter extends RecyclerView.Adapter<DBDVAdapter.DBDVViewHolder> {
    private final ArrayList<DBDV> dbdvs;

    public DBDVAdapter(ArrayList<DBDV> dbdvs) {
        this.dbdvs = dbdvs;
    }

    public static class DBDVViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDVSymbol;
        TextView tvDVName, tvDVAddress, tvDVId;

        public DBDVViewHolder(View itemView) {
            super(itemView);
            ivDVSymbol = itemView.findViewById(R.id.ivDVSymbol);
            tvDVName = itemView.findViewById(R.id.tvDVName);
            tvDVAddress = itemView.findViewById(R.id.tvDVAddress);
            tvDVId = itemView.findViewById(R.id.tvDVId);

            itemView.setOnClickListener(v -> {
                DBDV dbdv = (DBDV) v.getTag();
                Intent intent = new Intent(v.getContext(), DBDVActivity.class);
                intent.putExtra("ID", dbdv.getId());
                intent.putExtra("NAME", dbdv.getName());
                intent.putExtra("ADDRESS", dbdv.getAddress());
                intent.putExtra("PHONE", dbdv.getPhoneNumber());
                intent.putExtra("AVATAR", dbdv.getSymbolImage()); // String avatar name
                v.getContext().startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public DBDVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dbdv_item, parent, false);
        return new DBDVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DBDVViewHolder holder, int position) {
        DBDV dbdv = dbdvs.get(position);
        holder.itemView.setTag(dbdv);
        holder.tvDVName.setText(dbdv.getName());
        holder.tvDVAddress.setText(dbdv.getAddress());
        holder.tvDVId.setText(dbdv.getId());

        // Gán icon theo symbol
        int avatarRes = R.drawable.tlulogo; // fallback nếu không khớp

        switch (dbdv.getSymbolImage()) {
            case "computer":
                avatarRes = R.drawable.computer;
                break;
            case "cokhi":
                avatarRes = R.drawable.cokhi;
                break;
            case "law":
                avatarRes = R.drawable.law;
                break;
            case "water":
                avatarRes = R.drawable.water;
                break;
            case "xaydung":
                avatarRes = R.drawable.xaydung;
                break;
        }

        holder.ivDVSymbol.setImageResource(avatarRes);
    }

    @Override
    public int getItemCount() {
        return dbdvs.size();
    }
}

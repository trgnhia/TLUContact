
package com.example.tlucontact.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;
import com.example.tlucontact.adapter.DBDVAdapter;
import com.example.tlucontact.entity.DBDV;
import com.example.tlucontact.viewmodel.DBDVSearchViewModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DBDVFragment extends Fragment {
    RecyclerView recyclerView;
    DBDVAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DBDV> dbdvs;
    ArrayList<DBDV> foundDbdv;
    DBDVSearchViewModel dbdvSearchViewModel;

    public DBDVFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dbdv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvDBDV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dbdvs = new ArrayList<>();
        foundDbdv = new ArrayList<>();
        adapter = new DBDVAdapter(foundDbdv);
        recyclerView.setAdapter(adapter);

        dbdvSearchViewModel = new ViewModelProvider(requireActivity()).get(DBDVSearchViewModel.class);
        dbdvSearchViewModel.getSearchDBDV().observe(getViewLifecycleOwner(), this::filterData);

        insertSampleDbdvToFirestore();
        loadDataFromFirestore();
    }

    private void loadDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("dbdv").get().addOnSuccessListener(queryDocumentSnapshots -> {
            dbdvs.clear();
            for (DocumentSnapshot doc : queryDocumentSnapshots) {
                DBDV dv = doc.toObject(DBDV.class);
                dbdvs.add(dv);
            }
            foundDbdv.clear();
            foundDbdv.addAll(dbdvs);
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(getContext(), "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show());
    }
    private void insertSampleDbdvToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<DBDV> dbdvs = new ArrayList<>();
        dbdvs.add(new DBDV("DV001", "Khoa học máy tính", "Đại học Thủy Lợi ", "02438512301", "computer"));
        dbdvs.add(new DBDV("DV002", "Cơ khí", "Đại học Thủy Lợi ", "02438512302", "cokhi"));
        dbdvs.add(new DBDV("DV003", "Luật", "Đại học Thủy Lợi ", "02438512303", "law"));
        dbdvs.add(new DBDV("DV004", "Tài nguyên nước", "Đại học Thủy Lợi ", "02438512304", "water"));
        dbdvs.add(new DBDV("DV005", "Xây dựng", "Đại học Thủy Lợi ", "02438512305", "xaydung"));
        for (DBDV dv : dbdvs) {
            db.collection("dbdv").document(dv.getId()).set(dv);
        }

        Toast.makeText(getContext(), "Đã thêm dữ liệu mẫu đơn vị", Toast.LENGTH_SHORT).show();
    }
    private void filterData(String query) {
        foundDbdv.clear();
        if (query.isEmpty()) {
            foundDbdv.addAll(dbdvs);
        } else {
            boolean found = false;
            for (DBDV item : dbdvs) {
                if (item.getId().toLowerCase().contains(query.toLowerCase())) {
                    foundDbdv.add(item);
                    found = true;
                }
            }
            if (!found) {
                Toast.makeText(getContext(), "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
            }
        }
        adapter.notifyDataSetChanged();
    }
}

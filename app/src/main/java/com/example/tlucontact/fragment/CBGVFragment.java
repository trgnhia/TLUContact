
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
import com.example.tlucontact.adapter.CBGVAdapter;
import com.example.tlucontact.entity.CBGV;
import com.example.tlucontact.viewmodel.CBGVSearchViewModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CBGVFragment extends Fragment {
    RecyclerView recyclerView;
    CBGVAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CBGV> cbgvs;
    ArrayList<CBGV> foundcbgv;
    CBGVSearchViewModel cbgvSearchViewModel;

    public CBGVFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cbgv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvCBGV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        cbgvs = new ArrayList<>();
        foundcbgv = new ArrayList<>();
        adapter = new CBGVAdapter(foundcbgv);
        recyclerView.setAdapter(adapter);

        cbgvSearchViewModel = new ViewModelProvider(requireActivity()).get(CBGVSearchViewModel.class);
        cbgvSearchViewModel.getSearchCBGV().observe(getViewLifecycleOwner(), this::filterData);
        insertSampleDataToFirestore();
        loadDataFromFirestore();
    }

    private void insertSampleDataToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<CBGV> cbgvs = new ArrayList<>();
        cbgvs.add(new CBGV("male", "email1@example.com", "GV001", "Nguyễn Văn A", "0987654321", "Giáo viên", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("female", "email2@example.com", "GV002", "Trần Thị B", "0971234567", "Giáo viên", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("male", "email3@example.com", "GV003", "Lê Văn C", "0967890123", "Hiệu trưởng", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("female", "email4@example.com", "GV004", "Phạm Thị D", "0912345678", "Phó hiệu trưởng", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("male", "email5@example.com", "GV005", "Hoàng Văn E", "0923456789", "Tổ trưởng bộ môn", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("female", "email6@example.com", "GV006", "Đặng Thị F", "0934567890", "Giáo viên", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("male", "email7@example.com", "GV007", "Bùi Văn G", "0945678901", "Giáo viên", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("female", "email8@example.com", "GV008", "Ngô Thị H", "0956789012", "Trợ giảng", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("male", "email9@example.com", "GV009", "Dương Văn I", "0967890123", "Giáo viên", "Đại học Thủy Lợi"));
        cbgvs.add(new CBGV("female", "email10@example.com", "GV010", "Vũ Thị K", "0978901234", "Giáo viên", "Đại học Thủy Lợi"));

        for (CBGV gv : cbgvs) {
            db.collection("cbgv").document(gv.getId()).set(gv);
        }
    }

    private void loadDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("cbgv").get().addOnSuccessListener(queryDocumentSnapshots -> {
            cbgvs.clear();
            for (DocumentSnapshot doc : queryDocumentSnapshots) {
                CBGV gv = doc.toObject(CBGV.class);
                cbgvs.add(gv);
            }
            foundcbgv.clear();
            foundcbgv.addAll(cbgvs);
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(getContext(), "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show());
    }

    private void filterData(String query) {
        foundcbgv.clear();
        if (query.isEmpty()) {
            foundcbgv.addAll(cbgvs);
        } else {
            boolean found = false;
            for (CBGV item : cbgvs) {
                if (item.getId().toLowerCase().contains(query.toLowerCase())) {
                    foundcbgv.add(item);
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

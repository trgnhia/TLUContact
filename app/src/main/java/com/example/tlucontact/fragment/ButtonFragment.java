package com.example.tlucontact.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tlucontact.viewmodel.CBGVSearchViewModel;
import com.example.tlucontact.viewmodel.DBDVSearchViewModel;
import com.example.tlucontact.viewmodel.FragmentCurrentViewModel;
import com.example.tlucontact.R;

public class ButtonFragment extends Fragment {

    ImageView btnDBDV, btnCBGV;
    EditText etSearch;
    ImageView btnSort, btnRefesh;
    TextView tvState;

    private Btnclick btnclick;
    FragmentCurrentViewModel viewModel;
    CBGVSearchViewModel cbgvSearchViewModel;
    DBDVSearchViewModel dbdvSearchViewModel;

    public interface Btnclick {
        void onBtnclick(int fragmentId);
    }

    public ButtonFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_button, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(FragmentCurrentViewModel.class);
        cbgvSearchViewModel = new ViewModelProvider(requireActivity()).get(CBGVSearchViewModel.class);
        dbdvSearchViewModel = new ViewModelProvider(requireActivity()).get(DBDVSearchViewModel.class);

        viewModel.setCurrentFragmentId(R.id.btnDBDV);

        btnCBGV = view.findViewById(R.id.btnCBGV);
        btnDBDV = view.findViewById(R.id.btnDBDV);
        tvState = view.findViewById(R.id.tvState);
        etSearch = view.findViewById(R.id.etSearch);
        btnSort = view.findViewById(R.id.btnSort);
        btnRefesh = view.findViewById(R.id.btnRefresh);

        btnCBGV.setOnClickListener(v -> {
            btnclick.onBtnclick(R.id.btnCBGV);
            tvState.setText("Danh bạ giảng viên");
            viewModel.setCurrentFragmentId(R.id.btnCBGV);
            updateSelectedButton(R.id.btnCBGV);
        });

        btnDBDV.setOnClickListener(v -> {
            btnclick.onBtnclick(R.id.btnDBDV);
            tvState.setText("Danh bạ đơn vị");
            viewModel.setCurrentFragmentId(R.id.btnDBDV);
            updateSelectedButton(R.id.btnDBDV);
        });

        btnSort.setOnClickListener(v -> {
            String searchText = etSearch.getText().toString().toUpperCase();
            int currentFragmentId = viewModel.getCurrentFragmentId().getValue();
            if (currentFragmentId == R.id.btnCBGV) {
                cbgvSearchViewModel.setSearchCBGV(searchText);
            } else if (currentFragmentId == R.id.btnDBDV) {
                dbdvSearchViewModel.setSearchDBDV(searchText);
            }
        });

        btnRefesh.setOnClickListener(v -> {
            int currentFragmentId = viewModel.getCurrentFragmentId().getValue();
            if (currentFragmentId == R.id.btnCBGV) {
                cbgvSearchViewModel.setSearchCBGV("");
            } else if (currentFragmentId == R.id.btnDBDV) {
                dbdvSearchViewModel.setSearchDBDV("");
            }
            etSearch.setText("");
        });


    }
    private void updateSelectedButton(int selectedId) {
        if (selectedId == R.id.btnCBGV) {
            btnCBGV.setBackgroundResource(R.drawable.bg_icon_selected);
            btnCBGV.setColorFilter(getResources().getColor(android.R.color.white));

            btnDBDV.setBackgroundResource(R.drawable.bg_icon_unselected);
            btnDBDV.setColorFilter(getResources().getColor(android.R.color.black));
        } else {
            btnDBDV.setBackgroundResource(R.drawable.bg_icon_selected);
            btnDBDV.setColorFilter(getResources().getColor(android.R.color.white));

            btnCBGV.setBackgroundResource(R.drawable.bg_icon_unselected);
            btnCBGV.setColorFilter(getResources().getColor(android.R.color.black));
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        btnclick = (Btnclick) context;
    }
}

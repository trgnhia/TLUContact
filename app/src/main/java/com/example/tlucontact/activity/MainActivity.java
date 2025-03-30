package com.example.tlucontact.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tlucontact.fragment.ButtonFragment;
import com.example.tlucontact.fragment.CBGVFragment;
import com.example.tlucontact.fragment.DBDVFragment;
import com.example.tlucontact.R;

public class MainActivity   extends AppCompatActivity  implements ButtonFragment.Btnclick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //tạo toolbar cho mainactivity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    // triển khai interface Btnclick để xử lý sự kiện click ; giúp hiển thị fragment đuocjw chọn
    @Override
    public void onBtnclick(int fragmentId) {
        Fragment fragment = null;
        if(fragmentId == R.id.btnCBGV){
            fragment = new CBGVFragment();
        }
        else if(fragmentId == R.id.btnDBDV){
            fragment = new DBDVFragment();
        }
        assert fragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragment, fragment).commit();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.MainFragment, fragment);
        transaction.commit();

    }
}
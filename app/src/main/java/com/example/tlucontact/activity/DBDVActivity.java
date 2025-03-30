package com.example.tlucontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tlucontact.R;

// nhận dữ liệu từ Item đươch chọn và gửi lên Activity
public class DBDVActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbdvactivity);

        Toolbar toolbar = findViewById(R.id.toolbar_dbdv);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiện nút back
            getSupportActionBar().setTitle("Danh bạ đơn vị"); // Đặt tiêu đề
        }

        TextView tvId = findViewById(R.id.txt_id_dbdv);
        ImageView ivAvatar = findViewById(R.id.img_avatar_dbdv);
        TextView tvName = findViewById(R.id.txt_name_dbdv);
        TextView tvAddress = findViewById(R.id.txt_address_dbdv);
        TextView tvPhone = findViewById(R.id.txt_phone_dbdv);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String address = intent.getStringExtra("ADDRESS");
        String phone = intent.getStringExtra("PHONE");
        int avatar = intent.getIntExtra("AVATAR", R.drawable.computer);

        // Hiển thị dữ liệu
        ivAvatar.setImageResource(avatar);
        tvId.setText("ID: " + id);
        tvName.setText("Tên đơn vị: " + name);
        tvAddress.setText("Địa chỉ: " + address);
        tvPhone.setText("Số điện thoại: " + phone);
        ImageView btnCall = findViewById(R.id.btnCall);
        ImageView btnMap = findViewById(R.id.btnMap);

// Mở ứng dụng gọi điện
        btnCall.setOnClickListener(v -> {
            if (phone != null && !phone.isEmpty()) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phone));
                startActivity(dialIntent);
            }
        });

// Mở bản đồ
        btnMap.setOnClickListener(v -> {
            if (address != null && !address.isEmpty()) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps"); // Mở bằng Google Maps
                startActivity(mapIntent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Đóng Activity khi bấm back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

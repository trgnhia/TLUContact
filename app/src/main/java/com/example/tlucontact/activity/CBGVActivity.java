package com.example.tlucontact.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tlucontact.R;

// nhận dữ liệu từ Item đươch chọn và gửi lên Activity
public class CBGVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbgvactivity);

        // Ánh xạ Toolbar và xử lý nút quay lại
        Toolbar toolbar = findViewById(R.id.toolbar_cbgv);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiện nút back
            getSupportActionBar().setTitle("Cán Bộ Giảng Viên"); // Đặt tiêu đề
        }

        // Ánh xạ các thành phần UI
        ImageView ivAvatar = findViewById(R.id.img_avatar_cbgv);
        TextView tvId = findViewById(R.id.txt_id_cbgv);
        TextView tvName = findViewById(R.id.txt_name_cbgv);
        TextView tvPosition = findViewById(R.id.txt_position_cbgv);
        TextView tvWorkUnit = findViewById(R.id.txt_work_unit_cbgv);
        TextView tvEmail = findViewById(R.id.txt_email_cbgv);
        TextView tvPhone = findViewById(R.id.txt_phone_cbgv);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String email = intent.getStringExtra("EMAIL");
        String phone = intent.getStringExtra("PHONE");
        String position = intent.getStringExtra("POSITION");
        String workUnit = intent.getStringExtra("WORD_UNIT");
        int avatar = intent.getIntExtra("AVATAR", R.drawable.male);

        // Hiển thị dữ liệu lên giao diện
        ivAvatar.setImageResource(avatar);
        tvId.setText("ID: " + id);
        tvName.setText("Họ và tên: " + name);
        tvPosition.setText("Chức vụ: " + position);
        tvWorkUnit.setText("Đơn vị công tác: " + workUnit);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Số điện thoại: " + phone);
        ImageView btnCall = findViewById(R.id.btnCallGV);
        ImageView btnEmail = findViewById(R.id.btnEmailGV);
        ImageView btnMap = findViewById(R.id.btnMapGV);

// Gọi điện
        btnCall.setOnClickListener(v -> {
            if (phone != null && !phone.isEmpty()) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phone));
                startActivity(dialIntent);
            }
        });

// Gửi email
        btnEmail.setOnClickListener(v -> {
            if (email != null && !email.isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Liên hệ từ TLUContact");
                startActivity(Intent.createChooser(emailIntent, "Chọn ứng dụng email"));
            }
        });

// Xem vị trí đơn vị công tác trên bản đồ
        btnMap.setOnClickListener(v -> {
            if (workUnit != null && !workUnit.isEmpty()) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(workUnit));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }

    // Xử lý nút quay lại trên Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Đóng Activity khi bấm back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

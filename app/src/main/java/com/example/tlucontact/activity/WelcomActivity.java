package com.example.tlucontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontact.R;

public class WelcomActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        Button btnContinue = findViewById(R.id.btnLogin);
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // đóng Welcome để không quay lại khi ấn back
        });
    }
}
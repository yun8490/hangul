package com.example.study11_17_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import java.util.ArrayList;

public class subActivity extends AppCompatActivity {
    private TextView tv_sub;
    private TextView tv_splitted;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_sub = findViewById(R.id.tv_sub);
        tv_splitted = findViewById(R.id.tv_splitted);

        Intent intent = getIntent();
        String str = intent.getStringExtra("word");
        String result = intent.getStringExtra("result");

        tv_sub.setText(str);
        if (TextUtils.isEmpty(result)) {
            // 오류 처리: 분리된 결과가 없을 경우
            tv_splitted.setText("자음과 모음 분리 결과가 없습니다.");
        } else {
            tv_sub.setText(str);
            tv_splitted.setText("자음과 모음 분리 결과: " + result);
        }
    }
}
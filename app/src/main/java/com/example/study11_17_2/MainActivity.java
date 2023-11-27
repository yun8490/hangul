package com.example.study11_17_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText ed_move;
    private Button btn_move;
    private String str;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed_move = findViewById(R.id.ed_move);

        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = ed_move.getText().toString();
                String splitWord = splitKoeranString(word);

                Intent intent = new Intent(MainActivity.this, subActivity.class);

                intent.putExtra("word", word);
                intent.putExtra("result", splitWord);
                startActivity(intent);
            }
        });
    }
    private String splitKoeranString(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char comVal = (char) (input.charAt(i)-0xAC00);

            if (comVal >= 0 && comVal <= 11172) {
                char uniVal = (char)comVal;
                char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);
                char jung = (char) ((((uniVal - (uniVal % 28)) / 28) % 21) + 0x1161);
                char jong = (char) ((uniVal % 28) + 0x11a7);

                if (cho != 4519) {
                    result.append(cho);
                }
                if (jung != 4519) {
                    result.append(jung);
                }
                if (jong != 4519) {
                    result.append(jong);
                }
            } else {
                comVal = (char) (comVal + 0xAC00);
                result.append(comVal);
            }
        }
        return result.toString();
    }
}
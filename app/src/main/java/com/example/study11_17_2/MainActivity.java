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

    public static final String [] CHOSUNG = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ","ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    public static final String [] JUNGSUNG = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    public static final String [] JONGSUNG = {"", "ㄱ", "ㄲ", "ᆪ", "ᆫ", "ᆬ", "ᆭ", "ㄷ",
            "ㄹ", "ᆰ", "ᆱ", "ᆲ", "ᆳ", "ᆴ", "ᆵ", "ᆶ", "ㅁ", "ㅂ", "ᆹ", "ᆺ", "ᆻ", "ᆼ",
            "ᆽ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};


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

        String result = "";
        for(int i = 0; i < input.length(); i++) {
            int codePoint = Character.codePointAt(input, i);
            if (codePoint >= 0xAC00 && codePoint <= 0xD79D) {
                int startValue = codePoint - 0xAC00;
                int jong = startValue % 28;
                int jung = ((startValue - jong) / 28) % 21;
                int cho = (((startValue - jong) / 28) - jung) / 21;
                result += CHOSUNG[cho] +
                        JUNGSUNG[jung] +
                        JONGSUNG[jong];
            }
            else {
                return "";
            }
        }
        return result.toString();
    }
}
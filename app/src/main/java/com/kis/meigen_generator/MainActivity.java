package com.kis.meigen_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kis.meigen_generator.api.DataMeigen;

public class MainActivity extends AppCompatActivity {

    //レイアウト要素
    Button generateButton;
    TextView meigenView;

    DataMeigen dataMeigen = new DataMeigen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        generateButton = findViewById(R.id.generate_button);
        meigenView = findViewById(R.id.meigenView);

        // 名言作成ボタンクリック時の処理
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMeigen.getDataMeigen(meigenView);
            }
        });

    }

    // 初期設定
    private void init() {
        setTheme(R.style.Theme_Meigen_generator);
        setContentView(R.layout.activity_main);
        setTitle("名言ジェネレーター");
    }
}
package com.example.android.tsarasyafiera_1202150275_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_list, btn_pencarigambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_list = findViewById(R.id.listmahasiswa);
        btn_pencarigambar = findViewById(R.id.carigambar);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity.this, ListNamaMahasiswa.class);
                startActivity(x);
            }
        });

        btn_pencarigambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(MainActivity.this, PencariGambar.class);
                startActivity(y);
            }
        });
    }
}

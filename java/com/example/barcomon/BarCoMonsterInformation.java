package com.example.barcomon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BarCoMonsterInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bar_co_monster_information);
    }
}

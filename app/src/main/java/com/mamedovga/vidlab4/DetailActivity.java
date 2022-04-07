package com.mamedovga.vidlab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String name = "" + intent.getStringExtra("name");
        String company = "" + intent.getStringExtra("company");
        String screen = "" + intent.getStringExtra("screen") + "\"";
        String ram = "" + intent.getStringExtra("ram") + " ГБ";
        String ssd  = "" + intent.getStringExtra("ssd")+ " ГБ";
        String system  = "" + intent.getStringExtra("system");

        TextView nameText = findViewById(R.id.name);
        nameText.setText(name);
        TextView companyText = findViewById(R.id.company);
        companyText.setText(company);
        TextView screenText = findViewById(R.id.screen);
        screenText.setText(screen);
        TextView ramText = findViewById(R.id.ram);
        ramText.setText(ram);
        TextView ssdText = findViewById(R.id.ssd);
        ssdText.setText(ssd);
        TextView systemText = findViewById(R.id.system);
        systemText.setText(system);
    }
}
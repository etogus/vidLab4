package com.mamedovga.vidlab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddMonoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mono);

        EditText inputName = findViewById(R.id.input_name);
        EditText inputCompany = findViewById(R.id.input_company);
        EditText inputScreen = findViewById(R.id.input_screen);
        EditText inputRAM = findViewById(R.id.input_ram);
        EditText inputSSD= findViewById(R.id.input_ssd);
        EditText inputSystem = findViewById(R.id.input_system);
        MaterialButton saveBtn = findViewById(R.id.save_button);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                String company = inputCompany.getText().toString();
                String screen = inputScreen.getText().toString();
                String ram = inputRAM.getText().toString();
                String ssd = inputSSD.getText().toString();
                String system = inputSystem.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Mono mono = realm.createObject(Mono.class);
                mono.setName(name);
                mono.setCompany(company);
                mono.setScreen(screen);
                mono.setRam(ram);
                mono.setSsd(ssd);
                mono.setSystem(system);
                mono.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Сохранено",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
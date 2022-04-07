package com.mamedovga.vidlab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addMonoButton = findViewById(R.id.addnewmonobutton);

        addMonoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMonoActivity.class));
            }
        });
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Mono> monoList = realm.where(Mono.class).findAllSorted("createdTime", Sort.DESCENDING);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(this,monoList);
        recyclerView.setAdapter(myAdapter);

        monoList.addChangeListener(new RealmChangeListener<RealmResults<Mono>>() {
            @Override
            public void onChange(RealmResults<Mono> monos) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }
}
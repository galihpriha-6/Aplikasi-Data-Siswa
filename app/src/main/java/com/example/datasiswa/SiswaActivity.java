package com.example.datasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SiswaActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    SiswaAdapter siswaAdapter;
    List<SiswaModel> siswaModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//Setup Realm
        RealmConfiguration configuration = new
                RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        siswaModels = new ArrayList<>();

        siswaModels = realmHelper.getAllSiswa();
        show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        siswaAdapter.notifyDataSetChanged();
        show();
    }
    private void show() {
        siswaAdapter = new SiswaAdapter(this, siswaModels);
        recyclerView.setAdapter(siswaAdapter);
    }
}
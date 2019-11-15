package com.example.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSimpan, btnTampil;
    EditText nis, nama, email, notel, alamat;
    String sNama, sEmail, sAlamat;
    Long sNis, sNotel;
    Realm realm;
    RealmHelper realmHelper;
    SiswaModel siswaModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasiakan
        btnTampil = findViewById(R.id.btnTampil);
        btnSimpan = findViewById(R.id.btnSimpan);
        nis = findViewById(R.id.etNis);
        nama = findViewById(R.id.etNama);
        email = findViewById(R.id.etEmail);
        notel = findViewById(R.id.etNotel);
        alamat = findViewById(R.id.etAlamat);

        //Set up Realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            if (!nis.getText().toString().isEmpty() && !nama.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !notel.getText().toString().isEmpty() && !alamat.getText().toString().isEmpty()){
                sNis = Long.parseLong(nis.getText().toString());
                sNama = nama.getText().toString();
                sEmail = email.getText().toString();
                sNotel = Long.parseLong(notel.getText().toString());
                sAlamat = alamat.getText().toString();

                siswaModel = new SiswaModel();
                siswaModel.setNis(sNis);
                siswaModel.setNama(sNama);
                siswaModel.setEmail(sEmail);
                siswaModel.setNotel(sNotel);
                siswaModel.setAlamat(sAlamat);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(siswaModel);

                Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                nis.setText("");
                nama.setText("");
                email.setText("");
                notel.setText("");
                alamat.setText("");
            }else {
                Toast.makeText(MainActivity.this, "Inputan kosong !, Tolong di isi broh....", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
            Intent intent = new Intent(MainActivity.this, SiswaActivity.class);
            startActivity(intent);
        }
    }
}

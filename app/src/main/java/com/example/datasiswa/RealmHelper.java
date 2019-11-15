package com.example.datasiswa;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    //untuk menyimpan data
    public void save(final SiswaModel siswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database sudah terbuat broh");
                    Number currentIdNum = realm.where(SiswaModel.class).max("id");

                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    siswaModel.setId(nextId);
                    SiswaModel model = realm.copyToRealm(siswaModel);
                }else {
                    Log.e("Error", "Database tidak ada :'(");
                }
            }
        });
    }
    //untuk memanggil semua data
    public List<SiswaModel> getAllSiswa(){
        RealmResults<SiswaModel> results = realm.where(SiswaModel.class).findAll();
        return results;
    }

    //untuk mengupdate data
    public void update(final Integer id, final Long nis, final String nama, final String email, final Long notel, final String alamat){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SiswaModel model = realm.where(SiswaModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNis(nis);
                model.setNama(nama);
                model.setEmail(email);
                model.setNotel(notel);
                model.setAlamat(alamat);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("Pesan", "OnSuccess update sukses yay");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }
    //untuk menghapus data
    public void delete(Integer id){
        final RealmResults<SiswaModel> model = realm.where(SiswaModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}

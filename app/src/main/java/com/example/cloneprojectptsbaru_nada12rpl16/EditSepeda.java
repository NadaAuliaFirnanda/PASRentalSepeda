package com.example.cloneprojectptsbaru_nada12rpl16;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.cloneprojectptsbaru_nada12rpl16.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class EditSepeda extends AppCompatActivity  implements IPickResult {
    ImageView imageView;
    private Bitmap mSelectedImage;
    private String mSelectedImagePath;
    File mSelectedFileBanner;
    EditText mKodesepeda, mMerk, mWarna, mHargasewa, mJenis;
    Button save;
    String sId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sepeda);
        mKodesepeda = findViewById(R.id.updatekodesepeda);
        mMerk = findViewById(R.id.updatemerk);
        mJenis = findViewById(R.id.updatejenis);
        mWarna = findViewById(R.id.updatewarna);
        mHargasewa = findViewById(R.id.updatehargasewa);
        imageView = findViewById(R.id.updateimg);
        save = findViewById(R.id.simpan1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup()).show(EditSepeda.this);
                new PickSetup().setCameraButtonText("Gallery");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postitem();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("id") != null) {
            mKodesepeda.setText(bundle.getString("kodesepeda"));
            mMerk.setText(bundle.getString("merk"));
            mWarna.setText(bundle.getString("warna"));
            mHargasewa.setText(bundle.getString("hargasewa"));
            mJenis.setText(bundle.getString("jenis"));
            sId = bundle.getString("id");
        }

    }

//    public void postitem() {
//        final String kodesepeda = mKodesepeda.getText().toString();
//        final String merk = mMerk.getText().toString();
//        final String warna = mHargasewa.getText().toString();
//        final String hargasewa = mHargasewa.getText().toString();
//        final String jenis = mJenis.getText().toString();
//        HashMap<String, String> body = new HashMap<>();
//        body.put("kodesepeda", kodesepeda);
//        body.put("merk", merk);
//        body.put("jenis", jenis);
//        body.put("warna", warna);
//        body.put("hargasewa", hargasewa);
//
//        AndroidNetworking.upload("http://localhost/pts/Gambar.php")
//                .addMultipartFile("gambar", mSelectedFileBanner)
//                .addMultipartParameter(body)
//                .setPriority(Priority.HIGH)
////                .setOkHttpClient(((RS) getApplication()).getOkHttpClient())
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(EditSepeda.this, "Y", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                        Toast.makeText(EditSepeda.this, "G", Toast.LENGTH_SHORT).show();
//                    }
//
////                    @Override
////                    public void onError(ANError anError) {
////                        Toast.makeText(AddItemActivity.this, Config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
////                        Log.d("HBB", "onError: " + anError.getErrorBody());
////                        Log.d("HBB", "onError: " + anError.getLocalizedMessage());
////                        Log.d("HBB", "onError: " + anError.getErrorDetail());
////                        Log.d("HBB", "onError: " + anError.getResponse());
////                        Log.d("HBB", "onError: " + anError.getErrorCode());
////                    }
//                });
//    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));
                mSelectedImagePath = fileku.getAbsolutePath();
                mSelectedFileBanner = new File(mSelectedImagePath);
                mSelectedImage = r.getBitmap();
                imageView.setImageBitmap(mSelectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(EditSepeda.this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void postitem() {
        final String kodesepeda = mKodesepeda.getText().toString();
        final String merk = mMerk.getText().toString();
        final String warna = mHargasewa.getText().toString();
        final String hargasewa = mHargasewa.getText().toString();
        final String jenis = mJenis.getText().toString();
        HashMap<String, String> body = new HashMap<>();
        body.put("id", sId);
//        body.put("merk", merk);
//        body.put("jenis", jenis);
//        body.put("warna", warna);
//        body.put("hargasewa", hargasewa);


        AndroidNetworking.upload( BaseUrl.url+"image.php")
                .addMultipartFile("gambar",mSelectedFileBanner)
                .addMultipartParameter(body)
                .setPriority(Priority.MEDIUM)
//                .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respon = response.optString("respon");
                        if (respon.equalsIgnoreCase("true")) {
//                            finish();
//                            new SweetAlertDialog(AdminMPAddActivity.this)
//                                    .setTitleText(message)
//                                    .show();
                            Toast.makeText(EditSepeda.this, "Berhasil BOSSSS", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(EditSepeda.this, "GAGAL BOSSSS", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("VN", "onError: " + anError.getErrorBody());
                        Log.d("VN", "onError: " + anError.getLocalizedMessage());
                        Log.d("VN", "onError: " + anError.getErrorDetail());
                        Log.d("VN", "onError: " + anError.getResponse());
                        Log.d("VN", "onError: " + anError.getErrorCode());
                        Toast.makeText(EditSepeda.this, "OnError BOSSSS", Toast.LENGTH_LONG).show();
                    }
                });
//        AndroidNetworking.upload(BaseUrl.url+"Gambar.php")
//                .addMultipartFile("gambar", mSelectedFileBanner)
//                .addMultipartParameter(body)
//                .setPriority(Priority.HIGH)
//                .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(EditSepeda.this, "Y", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        anError.getErrorDetail();
//                        anError.getErrorBody();
//                        anError.getErrorCode();
//                        Log.d("VN", "onResponse: " + anError.getErrorDetail());
//                        Log.d("VN", "onResponse: " + anError.getErrorBody());
//                        Log.d("VN", "onResponse: " + anError.getErrorCode());
//
//                        Toast.makeText(EditSepeda.this, "G", Toast.LENGTH_SHORT).show();
//                    }
//
////                    @Override
////                    public void onError(ANError anError) {
////                        Toast.makeText(AddItemActivity.this, Config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
////                        Log.d("HBB", "onError: " + anError.getErrorBody());
////                        Log.d("HBB", "onError: " + anError.getLocalizedMessage());
////                        Log.d("HBB", "onError: " + anError.getErrorDetail());
////                        Log.d("HBB", "onError: " + anError.getResponse());
////                        Log.d("HBB", "onError: " + anError.getErrorCode());
////                    }
//                });
    }

}
package com.kaito.giuaky.phancong;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaito.giuaky.R;

public class InsertActivity extends AppCompatActivity {

    boolean isSave = false;

    EditText et_soPhieu, et_maXe, et_maTuyen, et_ngay, et_xuatPhat;
    Button btn_Them;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setControl();
        setEvent();
    }

    public void setControl() {
        et_soPhieu = findViewById(R.id.et_soPhieu);
        et_maXe = findViewById(R.id.et_maXe);
        et_maTuyen = findViewById(R.id.et_maTuyen);
        et_ngay = findViewById(R.id.et_ngay);
        et_xuatPhat = findViewById(R.id.et_xuatPhat);
        btn_Them = findViewById(R.id.btn_Them);
    }

    public void setEvent() {
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_soPhieu.length() > 0 || et_maXe.length() > 0 || et_maTuyen.length() > 0 || et_ngay.length() > 0 || et_xuatPhat.length() > 0) {
                    PhanCong phanCong = getPhanCong();
                    insert(phanCong);
                    isSave = true;
                    Snackbar.make(v, "Thêm thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    //Toast.makeText(InsertActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InsertActivity.this, "Có thông tin chưa nhập! Kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public PhanCong getPhanCong() {
        PhanCong phanCong = new PhanCong();
        phanCong.setSoPhieu(et_soPhieu.getText().toString());
        phanCong.setMaXe(et_maXe.getText().toString());
        phanCong.setMaTuyen(et_maTuyen.getText().toString());
        phanCong.setNgay(et_ngay.getText().toString());
        phanCong.setXuatPhat(et_xuatPhat.getText().toString());

        return phanCong;
    }

    public void insert(PhanCong phanCong) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.insert(phanCong);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("xxx","OnDestroy");
        if (isSave == false) {
            Toast.makeText(InsertActivity.this, "Chưa lưu lại!", Toast.LENGTH_SHORT).show();

        }
    }
}
package com.kaito.giuaky.phancong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.kaito.giuaky.R;

public class UpdateDeleteActivity extends AppCompatActivity {

    static String SO_PHIEU = "soPhieu", MA_XE = "maXa", MA_TUYEN = "maTuyen", NGAY ="ngay", XUAT_PHAT = "xuatPhat";
    boolean isSave = false;

    EditText et_soPhieu, et_maXe, et_maTuyen, et_ngay, et_xuatPhat;
    Button btn_Sua, btn_Xoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        setControl();
        setEvent();
    }

    public void setControl() {
        et_soPhieu = findViewById(R.id.et_soPhieu);
        et_maXe = findViewById(R.id.et_maXe);
        et_maTuyen = findViewById(R.id.et_maTuyen);
        et_ngay = findViewById(R.id.et_ngay);
        et_xuatPhat = findViewById(R.id.et_xuatPhat);
        btn_Sua = findViewById(R.id.btn_Sua);
        btn_Xoa = findViewById(R.id.btn_Xoa);
    }

    public void setEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            et_soPhieu.setText(bundle.getString(SO_PHIEU));
            et_maXe.setText(bundle.getString(MA_XE));
            et_maTuyen.setText(bundle.getString(MA_TUYEN));
            et_ngay.setText(bundle.getString(NGAY));
            et_xuatPhat.setText(bundle.getString(XUAT_PHAT));
        }

        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_soPhieu.length() > 0 || et_maXe.length() > 0 || et_maTuyen.length() > 0 || et_ngay.length() > 0 || et_xuatPhat.length() > 0) {

                    PhanCong phanCong = getPhanCong();
                    Log.e("update", "" + phanCong.getSoPhieu());
                    update(phanCong);
                    isSave = true;
                    Snackbar.make(v, "Sửa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    //Toast.makeText(UpdateDeleteActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdateDeleteActivity.this, "Có thông tin chưa nhập! Kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogDelete(v);
                //Snackbar.make(v, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                //Toast.makeText(UpdateDeleteActivity.this, "Đã xóa " + phanCong.getSoPhieu(), Toast.LENGTH_SHORT).show();
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

    public void update(PhanCong phanCong) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.update(phanCong);
    }

    public void delete(String soPhieu) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.delete(soPhieu);
    }

    public void openAlertDialogDelete(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa?")
                .setMessage("Bạn có chắc chắn xóa số phiếu này không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhanCong phanCong = getPhanCong();
                        delete(phanCong.getSoPhieu());
                        Snackbar.make(view, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void openAlertDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận lưu?")
                .setMessage("Nhưng thay đổi bạn đã nhập chưa được lưu. Bạn có muốn lưu lại không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhanCong phanCong = getPhanCong();
                        update(phanCong);
                        Snackbar.make(view, "Lưu thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        View v = new View(this);
//        if (isSave == false) {
//            openAlertDialog(v);
//
//        }
//    }

}
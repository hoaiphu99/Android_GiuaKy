package com.kaito.giuaky;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kaito.giuaky.phancong.DatabaseConnection;
import com.kaito.giuaky.phancong.InsertActivity;
import com.kaito.giuaky.phancong.PhanCong;
import com.kaito.giuaky.phancong.PhanCongAdapter;
import com.kaito.giuaky.phancong.UpdateDeleteActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String SO_PHIEU = "soPhieu", MA_XE = "maXa", MA_TUYEN = "maTuyen", NGAY ="ngay", XUAT_PHAT = "xuatPhat";
    ListView list_DSPC;
    ArrayList<PhanCong> data = new ArrayList<>();
    PhanCongAdapter adapter = null;
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
        loadData();
        adapter.notifyDataSetChanged();
    }

    public void setControl() {
        list_DSPC = findViewById(R.id.list_DSPC);
        toolbar = findViewById(R.id.toolbar);
        //fab = findViewById(R.id.fab);
    }

    public void setEvent() {
        //init();
        loadData();
        setSupportActionBar(toolbar);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        adapter = new PhanCongAdapter(this, R.layout.list_phancong_item, data);
        list_DSPC.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list_DSPC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhanCong phanCong = data.get(position);

                Intent intent = new Intent(MainActivity.this, UpdateDeleteActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(SO_PHIEU, phanCong.getSoPhieu());
                bundle.putString(MA_XE, phanCong.getMaXe());
                bundle.putString(MA_TUYEN, phanCong.getMaTuyen());
                bundle.putString(NGAY, phanCong.getNgay());
                bundle.putString(XUAT_PHAT, phanCong.getXuatPhat());

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_insert) {
            Intent intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings) {
            Log.e("xxx", "Vao setting");
            View v = new View(this);
            openAlertDialog(v);
            return true;
        }
        if (id == R.id.action_refresh) {
            Log.e("xxx", "Vao refresh");
            loadData();
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        DatabaseConnection db = new DatabaseConnection(this);
        data.clear();
        db.getPhanCong(data);
        //adapter.notifyDataSetChanged();
    }

    public void openAlertDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Opps...")
                .setMessage("Chức năng này chưa thêm! Vui lòng thử lại sau!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("xxx", "OnResume");
        loadData();
        adapter.notifyDataSetChanged();
    }

    public void init() {
        PhanCong phanCong1 = new PhanCong("P01", "X01", "T01", "1/1/2021", "HCM");
        PhanCong phanCong2 = new PhanCong("P02", "X02", "T02", "1/1/2021", "HN");
        PhanCong phanCong3 = new PhanCong("P03", "X03", "T03", "1/1/2021", "DN");

        data.add(phanCong1);
        data.add(phanCong2);
        data.add(phanCong3);
    }
}
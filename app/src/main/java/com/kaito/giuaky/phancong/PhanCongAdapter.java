package com.kaito.giuaky.phancong;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kaito.giuaky.R;

import java.util.ArrayList;
import java.util.List;

public class PhanCongAdapter extends ArrayAdapter<PhanCong> {

    Context context;
    int resource;
    ArrayList<PhanCong> data = null;

    public PhanCongAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PhanCong> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class PhanCongHolder {
        ImageView imgDelete;
        TextView tv_soPhieu, tv_maXe, tv_maTuyen, tv_ngay, tv_xuatPhat;

        public PhanCongHolder() {
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PhanCongHolder holder = null;
        if(row != null) {
            holder = (PhanCongHolder) row.getTag();
        }

        else {
            holder = new PhanCongHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_phancong_item, parent, false);

            holder.imgDelete = row.findViewById(R.id.imgDelete);
            holder.tv_soPhieu = row.findViewById(R.id.tv_soPhieu);
            holder.tv_maTuyen = row.findViewById(R.id.tv_maTuyen);
            holder.tv_ngay = row.findViewById(R.id.tv_ngay);
            row.setTag(holder);
        }

        final PhanCong phanCong = data.get(position);
        holder.tv_soPhieu.setText("Số phiếu: " + phanCong.getSoPhieu());
        holder.tv_maTuyen.setText("Mã tuyến: " + phanCong.getMaTuyen());
        holder.tv_ngay.setText("Ngày: " + phanCong.getNgay());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseConnection db = new DatabaseConnection(context);
                db.delete(phanCong.getSoPhieu());
            }
        });

        return row;
    }
}

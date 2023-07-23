package md18202.nhom2.duan1application.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import md18202.nhom2.duan1application.Adapters.GioHangAdapter;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.R;

public class GioHangActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GioHangDAO gioHangDAO;
    GioHangAdapter gioHangAdapter;
    List<GioHang> listGioHang;
    SharedPreferences sharedPreferences;
    int getNguoiDung_id;
    public TextView tvTotal;
    ImageView imgBack;
    Button btnMua_hang;
    int total =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recycler_view_gio_hang);
        tvTotal = findViewById(R.id.tvTotal);
        imgBack = findViewById(R.id.imgBack);
        btnMua_hang = findViewById(R.id.btnMua_hang);
        gioHangDAO = new GioHangDAO(GioHangActivity.this);
        sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
        getNguoiDung_id = sharedPreferences.getInt("nguoiDung_id", 0);

        getDsGioHang();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                startActivity(intent);

            }
        });

    }
/////////////////////////////////////////////////////////////////////////////////////////
    public void tongTien(List<GioHang> listGioHang){
        total = 0;
        for(GioHang gioHang: listGioHang){
            total += gioHang.getGiaSanPham();
        }
        tvTotal.setText(""+total);
    }

    @Override
    public void onBackPressed() {finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDsGioHang();
    }

    public void getDsGioHang(){
        listGioHang = gioHangDAO.getDsGioHang(getNguoiDung_id);
        gioHangAdapter = new GioHangAdapter(listGioHang, this);
        recyclerView.setAdapter(gioHangAdapter);
        tongTien(listGioHang);
    }
}
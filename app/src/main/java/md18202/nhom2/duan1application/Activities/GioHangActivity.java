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
import android.widget.Toast;

import java.io.Serializable;
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
    List<GioHang> listMuaHang;
    SharedPreferences sharedPreferences;
    int getNguoiDung_id;
    public TextView tvTotal, tvThong_bao;
    ImageView imgBack;
    public Button btnMua_hang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recycler_view_gio_hang);
        tvTotal = findViewById(R.id.tvTotal);
        tvThong_bao = findViewById(R.id.tvThong_bao);
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

        muaHang(btnMua_hang);

    }
/////////////////////////////////////////////////////////////////////////////////////////

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
        if(listGioHang.size() == 0){
            tvThong_bao.setVisibility(View.VISIBLE);
        }
        tongTien(listGioHang);
        gioHangAdapter = new GioHangAdapter(listGioHang, this);
        recyclerView.setAdapter(gioHangAdapter);
    }

    public int tongTien(List<GioHang> listMuaHang){
        int price = 0;
        for(GioHang gioHang1: listMuaHang){
            if(gioHang1.getTrangThaiMua()==1){
                price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
            }
        }
        tvTotal.setText(""+price);
        return price;
    }

    public void muaHang(Button btnMua_hang){
       btnMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMuaHang = gioHangDAO.getDsMuaHang(getNguoiDung_id, 1);
                if(listMuaHang.size() == 0){
                    return;
                }
                Intent intent = new Intent(GioHangActivity.this, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
                bundle.putInt("tongTien", tongTien(listMuaHang));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
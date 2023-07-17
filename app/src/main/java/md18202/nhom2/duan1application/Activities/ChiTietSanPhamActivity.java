package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import md18202.nhom2.duan1application.Adapters.BinhLuanAdapter;
import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet;
    RecyclerView recyclerView_binh_luan;
    Button btnChon_mua;
    SanPham sanPham;
    ImageView imgYeuThich_frameSPChiTiet2;
    BinhLuanDAO binhLuanDAO;
    List<BinhLuan> listBinhLuan;
    BinhLuanAdapter binhLuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        imgYeuThich_frameSPChiTiet2 = findViewById(R.id.imgYeuThich_frameSPChiTiet2);
        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
        btnChon_mua = findViewById(R.id.btnChon_mua);
        recyclerView_binh_luan = findViewById(R.id.recycler_view_binh_luan);
        binhLuanDAO = new BinhLuanDAO(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");

        String srcImg = sanPham.getAnhSanPham();
        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
        Picasso.get().load(resourceId).into(imgAnh_sanpham_chitiet);
        tvTen_sanpham_chitiet.setText(sanPham.getTenSanPham());
        tvGia_sanpham_chitiet.setText("" + sanPham.getGiaSanPham() + " VND");
        getDsBinhLuan(sanPham.getSanPham_id());

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Chức năng yêu thích
        if (sanPham != null) {
            Toast.makeText(this, "Id sản phẩm: " + sanPham.getSanPham_id() + " -IsYeuThich: " + sanPham.getIsYeuThich(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }
        int sanPham_id = sanPham.getSanPham_id();
        int isYeuThich = sanPham.getIsYeuThich();
        sanPhamYeuThich(sanPham_id, isYeuThich, imgYeuThich_frameSPChiTiet2);
    }

    public void sanPhamYeuThich(int sanPham_id, int isYeuThich, ImageView imgIsYeuThich) {
        if (isYeuThich == 1) {
            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
        }
        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
                if (isYeuThich == 1) {
                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
                    sanPhamDAO.changeIsYeuThich(sanPham_id, 0);
                } else {
                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
                    sanPhamDAO.changeIsYeuThich(sanPham_id, 1);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getDsBinhLuan(int sanPhamId) {
        listBinhLuan = binhLuanDAO.getDsBinhLuan(sanPhamId);
        binhLuanAdapter = new BinhLuanAdapter(listBinhLuan, ChiTietSanPhamActivity.this);
        recyclerView_binh_luan.setAdapter(binhLuanAdapter);
    }
}
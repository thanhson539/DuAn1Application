package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.Timer;

import md18202.nhom2.duan1application.Adapters.BinhLuanAdapter;
import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.Models.NguoiDung;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet;
    RecyclerView recyclerView_binh_luan;
    Button btnChon_mua;
    EditText edDialog_binh_luan;
    SanPham sanPham;
    ImageView imgYeuThich_frameSPChiTiet2;
    BinhLuanDAO binhLuanDAO;
    List<BinhLuan> listBinhLuan;
    BinhLuanAdapter binhLuanAdapter;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        imgYeuThich_frameSPChiTiet2 = findViewById(R.id.imgYeuThich_frameSPChiTiet2);
        edDialog_binh_luan = findViewById(R.id.edDialog_binh_luan);
        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
        btnChon_mua = findViewById(R.id.btnChon_mua);
        recyclerView_binh_luan = findViewById(R.id.recycler_view_binh_luan);
        binhLuanDAO = new BinhLuanDAO(this);

        //lay san pham tu ben adapter san pham 2, khi click vao san pham se lay san pham do va truyen qua chi tiet san pham
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");

        //set anh cho san pham
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
//        sanPhamYeuThich(imgYeuThich_frameSPChiTiet2);
        binhLuanDialog(edDialog_binh_luan);
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

    public void binhLuan(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_binh_luan);
        EditText edBinh_luan = dialog.findViewById(R.id.edBinh_luan);
        ImageView imgBinh_luan = dialog.findViewById(R.id.imgBinh_luan);

        //day binh luan cua nguoi dung vao database
        imgBinh_luan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nguoiDung_id lay tu home
                sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
                int getNguoiDung_id = sharedPreferences.getInt("nguoiDung_id", 0);
                //sanPham_id lay tu sanPham
                int sanPham_id = sanPham.getSanPham_id();
                //noiDung lay tu edBinh_luan
                if(edBinh_luan.getText().length() == 0){
                    Toast.makeText(ChiTietSanPhamActivity.this, "Binh luan trong tron a", Toast.LENGTH_SHORT).show();
                    return;
                }
                String binh_luan = edBinh_luan.getText().toString();
                //thoiGian lay tu thoi gian thuc new Date(), new Time()
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd-MM-yyyy");
                String currentTime = format.format(calendar.getTime());
                imgBinh_luan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BinhLuan binhLuan = new BinhLuan();
                        binhLuan.setNguoiDung_id(getNguoiDung_id);
                        binhLuan.setSanPham_id(sanPham_id);
                        binhLuan.setNoiDung(binh_luan);
                        binhLuan.setThoiGian(currentTime);
                        if(binhLuanDAO.themBinhLuan(binhLuan) > 0){
                            getDsBinhLuan(sanPham_id);
                            dialog.cancel();
                            Toast.makeText(ChiTietSanPhamActivity.this, "Da binh luan", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ChiTietSanPhamActivity.this, "Them binh luan khong thanh cong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        dialog.show();
    }

    public void binhLuanDialog(EditText edDialog_binh_luan){
        edDialog_binh_luan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binhLuan();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getDsBinhLuan(int sanPhamId) {
        listBinhLuan = binhLuanDAO.getDsBinhLuan(sanPhamId);
        Collections.reverse(listBinhLuan);
        binhLuanAdapter = new BinhLuanAdapter(listBinhLuan, ChiTietSanPhamActivity.this);
        recyclerView_binh_luan.setAdapter(binhLuanAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDsBinhLuan(sanPham.getSanPham_id());
    }
}
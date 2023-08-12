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

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import md18202.nhom2.duan1application.Adapters.BinhLuanAdapter;
import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.DAO.SanPhamYeuThichDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.Models.SanPhamYeuThich;
import md18202.nhom2.duan1application.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack, imgGio_hang, imgYeu_thich, imgHome, imgThong_bao;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet, tvSo_luong;
    RecyclerView recyclerView_binh_luan;
    Button btnChon_mua, btnThem_vao_gio_hang;
    EditText edDialog_binh_luan;
    SanPham sanPham;
    ImageView imgYeuThich_frameSPChiTiet2;
    BinhLuanDAO binhLuanDAO;
    List<BinhLuan> listBinhLuan;
    BinhLuanAdapter binhLuanAdapter;
    SharedPreferences sharedPreferences;
    int getNguoiDung_id;
    int loaiTaiKhoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        imgYeuThich_frameSPChiTiet2 = findViewById(R.id.imgYeuThich_frameSPChiTiet2);
        imgGio_hang = findViewById(R.id.imgGio_hang);
        imgYeu_thich = findViewById(R.id.imgYeu_thich);
        imgHome = findViewById(R.id.imgHome);
        imgThong_bao = findViewById(R.id.imgThong_bao);
        edDialog_binh_luan = findViewById(R.id.edDialog_binh_luan);
        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
        tvSo_luong = findViewById(R.id.tvSo_luong);
        btnChon_mua = findViewById(R.id.btnChon_mua);
        btnThem_vao_gio_hang = findViewById(R.id.btnThem_vao_gio_hang);
        recyclerView_binh_luan = findViewById(R.id.recycler_view_binh_luan);
        binhLuanDAO = new BinhLuanDAO(this);
        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        getNguoiDung_id = sharedPreferences.getInt("nguoiDung_id", 0);
        loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan", -1);


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
        tvSo_luong.setText("" + sanPham.getSoLuongConLai());

        getDsBinhLuan(sanPham.getSanPham_id());

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Chức năng bình luận
        binhLuanDialog(edDialog_binh_luan);

        // Chức năng yêu thích
        int sanPham_id = sanPham.getSanPham_id();

        if (validate(sanPham_id) < 0) {
            imgYeuThich_frameSPChiTiet2.setImageResource(R.drawable.frame4_trai_tim);
        }
        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamYeuThich(sanPham_id, getNguoiDung_id, imgYeuThich_frameSPChiTiet2);
            }
        });


        imgGio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietSanPhamActivity.this, MainActivity.class));
            }
        });

        imgYeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, YeuThichAtivivty.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("sanPham", sanPham);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                finish();
            }
        });

        imgThong_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChiTietSanPhamActivity.this, "Chưa tồn tại màn hình thông báo", Toast.LENGTH_SHORT).show();
            }
        });

        if(loaiTaiKhoan != 1){
            btnThem_vao_gio_hang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chonMua(getNguoiDung_id, sanPham_id);
                }
            });

            if(sanPham.getSoLuongConLai()>0){
                btnChon_mua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogMuaNgay(sanPham);
                    }
                });
            }
        }


    }

    public void sanPhamYeuThich(int sanPham_id, int nguoiDung_id, ImageView imageView) {
        SanPhamYeuThich spyt = new SanPhamYeuThich();
        SanPhamYeuThichDAO sanPhamYeuThichDAO = new SanPhamYeuThichDAO(getApplicationContext());
        spyt.setSanPham_id(sanPham_id);
        spyt.setNguoiDung_id(nguoiDung_id);
        if (validate(sanPham_id) < 0) {
            if (sanPhamYeuThichDAO.boYeuThichSanPham(sanPham_id, nguoiDung_id) > 0) {
                imageView.setImageResource(R.drawable.frame4_trai_tim2);
            }
        } else {
            if (sanPhamYeuThichDAO.yeuThichSanPham(spyt) > 0) {
                imageView.setImageResource(R.drawable.frame4_trai_tim);
            }
        }


//        SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
//        int isYeuThich = sanPhamDAO.getTrangThaiYeuThichBySanPhamId(sanPham_id);
//        if (isYeuThich == 1) {
//            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
//        } else {
//            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
//        }
//        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
//                if (isYeuThich == 1) {
//                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
//                    sanPhamDAO.changeIsYeuThich(sanPham_id, 0);
//                } else {
//                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
//                    sanPhamDAO.changeIsYeuThich(sanPham_id, 1);
//                }
//            }
//        });

    }

    public void binhLuan() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_binh_luan);
        EditText edBinh_luan = dialog.findViewById(R.id.edBinh_luan);
        ImageView imgBinh_luan = dialog.findViewById(R.id.imgBinh_luan);

        //day binh luan cua nguoi dung vao database
        imgBinh_luan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nguoiDung_id lay tu home

                //sanPham_id lay tu sanPham
                int sanPham_id = sanPham.getSanPham_id();
                //noiDung lay tu edBinh_luan
                if (edBinh_luan.getText().length() == 0) {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Binh luan trong tron a", Toast.LENGTH_SHORT).show();
                    return;
                }
                String binh_luan = edBinh_luan.getText().toString();
                //thoiGian lay tu thoi gian thuc new Date(), new Time()
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd-MM-yyyy");
                String currentTime = format.format(calendar.getTime());

                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setNguoiDung_id(getNguoiDung_id);
                binhLuan.setSanPham_id(sanPham_id);
                binhLuan.setNoiDung(binh_luan);
                binhLuan.setThoiGian(currentTime);
                if (binhLuanDAO.themBinhLuan(binhLuan) > 0) {
                    getDsBinhLuan(sanPham_id);
                    dialog.cancel();
                    Toast.makeText(ChiTietSanPhamActivity.this, "Da binh luan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Them binh luan khong thanh cong", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.show();
    }

    public void binhLuanDialog(EditText edDialog_binh_luan) {
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

    public void chonMua(int nguoiDung_id, int sanPham_id) {
        SanPhamDAO sanPhamDAO = new SanPhamDAO(ChiTietSanPhamActivity.this);
        SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
        if(sanPham.getSoLuongConLai() == 0){
            Toast.makeText(ChiTietSanPhamActivity.this, "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            return;
        }
        GioHangDAO gioHangDAO = new GioHangDAO(ChiTietSanPhamActivity.this);
        GioHang gioHang = new GioHang();
        gioHang.setNguoiDung_id(nguoiDung_id);
        gioHang.setSanPham_id(sanPham_id);
        gioHang.setSoLuong(1);
        gioHang.setTrangThaiMua(0);
        if (gioHangDAO.themVaoGioHang(gioHang) > 0) {
            Toast.makeText(this, "Da them vao gio hang", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Mặt hàng này đã tồn tại trong giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }

    public int validate(int sanPham_id) {
        int check = 1;
        SanPhamYeuThichDAO spytd = new SanPhamYeuThichDAO(getApplicationContext());
        ArrayList<SanPham> list1 = spytd.getSanPhamYeuThich(getNguoiDung_id);
        for (SanPham sp : list1) {
            if (sp.getSanPham_id() == sanPham_id) {
                check = -1;
            }
        }
        return check;
    }

    public void dialogMuaNgay(SanPham sanPham){
        Dialog dialog = new Dialog(ChiTietSanPhamActivity.this);
        dialog.setContentView(R.layout.dialog_mua_ngay);
        TextView tvGia = dialog.findViewById(R.id.tvGia);
        ImageView imgAnh = dialog.findViewById(R.id.imgAnh);
        TextView tvKho = dialog.findViewById(R.id.tvKho);
        TextView tvSoluongmua = dialog.findViewById(R.id.tvSo_luong_mua);
        Button btnMua = dialog.findViewById(R.id.btnMua);
        ImageView imgMinus = dialog.findViewById(R.id.imgMinus);
        ImageView imgPlus = dialog.findViewById(R.id.imgPlus);

        String srcImg = sanPham.getAnhSanPham();
        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
        Picasso.get().load(resourceId).into(imgAnh);

        tvGia.setText(""+sanPham.getGiaSanPham());
        tvKho.setText("Kho: "+sanPham.getSoLuongConLai());

        GioHangDAO gioHangDAO = new GioHangDAO(ChiTietSanPhamActivity.this);
        GioHang gioHang = new GioHang();
        gioHang.setTrangThaiMua(1);
        gioHang.setSanPham_id(sanPham.getSanPham_id());
        gioHang.setNguoiDung_id(getNguoiDung_id);
        gioHangDAO.themVaoGioHang(gioHang);

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvSoluongmua.getText().toString().matches("1")){
                    return;
                }
                tvSoluongmua.setText(""+(Integer.parseInt(tvSoluongmua.getText().toString())-1));
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);

                int price = gioHang.getSoLuong() * sanPham.getGiaSanPham();
                tvGia.setText(""+price);
            }
        });

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvSoluongmua.getText().toString()) == sanPham.getSoLuongConLai()
                        || sanPham.getSoLuongConLai() == 0){
                    return;
                }
                tvSoluongmua.setText(""+(Integer.parseInt(tvSoluongmua.getText().toString())+1));
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);

                int price = gioHang.getSoLuong() * sanPham.getGiaSanPham();
                tvGia.setText(""+price);
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<GioHang> listMuaHang = gioHangDAO.getDsMuaHang(getNguoiDung_id, 1);
                int tongTien = (sanPham.getGiaSanPham() * Integer.parseInt(tvSoluongmua.getText().toString()));
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));
                Intent intent = new Intent(ChiTietSanPhamActivity.this, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
                bundle.putInt("tongTien", tongTien);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}
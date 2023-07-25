package md18202.nhom2.duan1application.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import md18202.nhom2.duan1application.DAO.HoaDonDAO;
import md18202.nhom2.duan1application.Models.HoaDon;
import md18202.nhom2.duan1application.R;

public class XacNhanThanhToanActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvNguoi_nhan_hang, tvSdt_nguoi_nhan, tvGia_tam_tinh, tvThanh_tien, tvDia_chi_nhan_hang;
    Button btnXac_nhan_dat_hang;
    HoaDonDAO hoaDonDAO;
    HoaDon hoaDon;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);
        imgBack = findViewById(R.id.imgBack);
        tvNguoi_nhan_hang = findViewById(R.id.tvNguoi_nhan_hang);
        tvSdt_nguoi_nhan = findViewById(R.id.tvSdt_nguoi_nhan);
        tvGia_tam_tinh = findViewById(R.id.tvGia_tam_tinh);
        tvThanh_tien = findViewById(R.id.tvThanh_tien);
        tvDia_chi_nhan_hang = findViewById(R.id.tvDia_chi_nhan_hang);
        btnXac_nhan_dat_hang = findViewById(R.id.btnXac_nhan_dat_hang);
        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id",-1);

        hoaDonDAO = new HoaDonDAO(this);

        Intent intent = getIntent();
        int tongTien = intent.getIntExtra("tongTien", 0);
        String diaChi = intent.getStringExtra("diaChi");
        String tenNguoiNhan = intent.getStringExtra("nguoiNhan");
        String sdt = intent.getStringExtra("sdt");

        tvNguoi_nhan_hang.setText(tenNguoiNhan);
        tvSdt_nguoi_nhan.setText(sdt);
        tvThanh_tien.setText(""+tongTien);
        tvGia_tam_tinh.setText(""+tongTien);
        tvDia_chi_nhan_hang.setText(diaChi);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXac_nhan_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String currentTime = format.format(calendar.getTime());

                hoaDon = new HoaDon();
                hoaDon.setNguoiDung_id(nguoiDung_id);
                hoaDon.setThoiGian(currentTime);
                hoaDon.setTongTien(tongTien);
                hoaDon.setDiaChi(diaChi);
                if(hoaDonDAO.themHoaDon(hoaDon) > 0){
                    Toast.makeText(XacNhanThanhToanActivity.this, "Đã thêm đơn hàng", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(XacNhanThanhToanActivity.this, "Thêm đơn hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

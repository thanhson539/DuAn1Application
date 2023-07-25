package md18202.nhom2.duan1application.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import md18202.nhom2.duan1application.R;

public class XacNhanThanhToanActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvNguoi_nhan_hang, tvSdt_nguoi_nhan, tvGia_tam_tinh, tvThanh_tien, tvDia_chi_nhan_hang;
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

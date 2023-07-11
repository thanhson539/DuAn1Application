package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet;
    Button btnChon_mua;
    SanPham sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
        btnChon_mua = findViewById(R.id.btnChon_mua);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");

        String srcImg = sanPham.getAnhSanPham();
        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
        Picasso.get().load(resourceId).into(imgAnh_sanpham_chitiet);
        tvTen_sanpham_chitiet.setText(sanPham.getTenSanPham());
        tvGia_sanpham_chitiet.setText(""+sanPham.getGiaSanPham());
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
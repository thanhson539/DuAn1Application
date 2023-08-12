package md18202.nhom2.duan1application.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.R;

public class PhuongThucThanhToanActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton_1;
    Button btnTiep_tuc;
    TextView tvTam_tinh, tvThanh_tien;
    ImageView imgBack;
    int tongTien, hinhThucThanhToan;
    String diaChi, tenNguoiNhan, sdt;
    List<GioHang> listGioHang;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        radioGroup = findViewById(R.id.radio_group);
        radioButton_1 = findViewById(R.id.radio_button_1);
        btnTiep_tuc = findViewById(R.id.btnTiep_tuc);
        tvTam_tinh = findViewById(R.id.tvTam_tinh);
        tvThanh_tien = findViewById(R.id.tvThanh_tien);
        imgBack = findViewById(R.id.imgBack);

        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);

        Intent intent = getIntent();
        tongTien = intent.getIntExtra("tongTien", 0);
        diaChi = intent.getStringExtra("diaChi");
        tenNguoiNhan = intent.getStringExtra("nguoiNhan");
        sdt = intent.getStringExtra("sdt");
        listGioHang = (List<GioHang>) intent.getSerializableExtra("listGioHang");

        tvTam_tinh.setText(""+tongTien);
        tvThanh_tien.setText(""+tongTien);

        radioButton_1.setChecked(true);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedText = selectedRadioButton.getText().toString();
        Toast.makeText(this, ""+selectedText, Toast.LENGTH_SHORT).show();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_1){
                    hinhThucThanhToan = 0;
//                    Toast.makeText(PhuongThucThanhToanActivity.this, "Thanh toán tiền mặt", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_button_2) {
                    hinhThucThanhToan = 1;
//                    Toast.makeText(PhuongThucThanhToanActivity.this, "Thẻ ATM", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_button_3) {
                    hinhThucThanhToan = 2;
//                    Toast.makeText(PhuongThucThanhToanActivity.this, "Thêm thẻ tín dụng/Ghi nợ", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_button_4) {
                    hinhThucThanhToan = 3;
//                    Toast.makeText(PhuongThucThanhToanActivity.this, "Ví điện tử", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTiep_tuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhuongThucThanhToanActivity.this, XacNhanThanhToanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("diaChi", diaChi);
                bundle.putInt("tongTien", tongTien);
                bundle.putInt("hinhThucThanhToan", hinhThucThanhToan);
                bundle.putString("sdt", sdt);
                bundle.putString("nguoiNhan", tenNguoiNhan);
                bundle.putSerializable("listGioHang", (Serializable) listGioHang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

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

    }
}

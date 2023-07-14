package md18202.nhom2.duan1application.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import md18202.nhom2.duan1application.R;

public class ChiTietNguoiDung extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView txtNameUct,txtsdtct,txtemailUct,txttkUct,txtloaitkUct;
    Button btnLogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_dung);
        //Ánh Xạ
        txtNameUct = findViewById(R.id.txtNameUct);
        txtsdtct = findViewById(R.id.sdtUct);
        txtemailUct = findViewById(R.id.emailUct);
        txttkUct = findViewById(R.id.taikhoanUct);
        txtloaitkUct = findViewById(R.id.loaitkUct);
        btnLogout = findViewById(R.id.btnLogout);
        //Chức năng hiển thị thông tin User
        sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
        String hotenct = sharedPreferences.getString("hoTen", "");
        String sdtUct = sharedPreferences.getString("sdt", "");
        String emailUct = sharedPreferences.getString("email","");
        String taikhoanUct = sharedPreferences.getString("taikhoan","");
        Integer loaitkUct = sharedPreferences.getInt("loaitaikhoan",0);
        txtNameUct.setText(hotenct);
        txtsdtct.setText(sdtUct);
        txtemailUct.setText(emailUct);
        txttkUct.setText(taikhoanUct);
        if(loaitkUct == 1){
            txtloaitkUct.setText("admin");
        }else {
            txtloaitkUct.setText("User");
        }

        //Chức năng Logout

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nút đăng xuất được nhấn
                // Xóa dữ liệu SharedPreferences hoặc thực hiện các thao tác đăng xuất khác

                SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(ChiTietNguoiDung.this, ManHinhChaoActivity.class);
                startActivity(intent);
                finish(); // Đóng màn hình ChiTietNguoiDungActivity
            }
        });
    }

}

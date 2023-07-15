package md18202.nhom2.duan1application.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.R;

public class ChiTietNguoiDung extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView txtNameUct,txtsdtct,txtemailUct,txttkUct,txtloaitkUct;
    Button btnLogout,btnChangePass;
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
        btnChangePass = findViewById(R.id.btnDoiMatKhau);
        //Chức năng hiển thị thông tin User
        sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
        String hotenct = sharedPreferences.getString("hoTen", "");
        String sdtUct = sharedPreferences.getString("sdt", "");
        String emailUct = sharedPreferences.getString("email","");
        String taikhoanUct = sharedPreferences.getString("taikhoan","");
        Integer loaitkUct = sharedPreferences.getInt("loaitaikhoan",0);
        txtNameUct.setText("Họ Và Tên:        "+hotenct);
        txtsdtct.setText("Số Điện Thoại:      "+sdtUct);
        txtemailUct.setText("Email:        "+emailUct);
        txttkUct.setText("Tài Khoản:        "+taikhoanUct);
        if(loaitkUct == 1){
            txtloaitkUct.setText("Vai Trò:        Admin");
        }else {
            txtloaitkUct.setText("Vai Trò:        Người Dùng");
        }
        //Chứ Năng Đổi Mật Khẩu
        btnChangePass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                showDialogChangePass();
            }
        });

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
    //Đổi Mật Khẩu
    private void showDialogChangePass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau,null);
        EditText EdtOldPass = view.findViewById(R.id.edtOldPass);
        EditText EdtNewPass = view.findViewById(R.id.edtNewPass);
        EditText EdtReNewPass = view.findViewById(R.id.edtReNewPass);

        builder.setView(view);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Đổi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String oldPass = EdtOldPass.getText().toString();
                String newPass = EdtNewPass.getText().toString();
                String reNewPass = EdtReNewPass.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
                    String taikhoan = sharedPreferences.getString("taikhoan", "");
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(ChiTietNguoiDung.this);

                    boolean check = nguoiDungDAO.doiMatKhau(taikhoan, oldPass, newPass);
                    if (check){
                        Toast.makeText(ChiTietNguoiDung.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChiTietNguoiDung.this,ManHinhChaoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {Toast.makeText(ChiTietNguoiDung.this,"Đổi Mật Khẩu Không Thành Công",Toast.LENGTH_SHORT).show();}
                }else {
                    Toast.makeText(ChiTietNguoiDung.this, "Nhập Mật Khẩu Mới Không Trùng", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}

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
    Button btnLogout,btnChangePass,btnChangeUserinfo;
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
        btnChangeUserinfo = findViewById(R.id.btnThongTin);
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
        //Chức Năng Đổi Mật Khẩu
        btnChangePass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                showDialogChangePass();
            }
        });
        //Chức năng Đổi thông tin người dùng

        btnChangeUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogChangeUserInfo();
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
    //Dialog đổi mật khẩu
    private void showDialogChangeUserInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialong_doithongtinnguoidung,null);
        EditText edthoTen = view.findViewById(R.id.edtCUserName);
        EditText edtEmail = view.findViewById(R.id.edtCUserEmail);
        EditText edtSdt = view.findViewById(R.id.edtCUserSdt);
        EditText checkpass = view.findViewById(R.id.edtCheckUserPass);

        builder.setView(view);
        //Hiển thị thông tin User Dialog
        sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
        String hotenUD = sharedPreferences.getString("hoTen", "");
        String sdtUD = sharedPreferences.getString("sdt", "");
        String emailUD = sharedPreferences.getString("email","");
        edthoTen.setText(hotenUD);
        edtSdt.setText(sdtUD);
        edtEmail.setText(emailUD);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNegativeButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            String hoten = edthoTen.getText().toString();
            String email = edtEmail.getText().toString();
            String sdt = edtSdt.getText().toString();
            String matkhau = checkpass.getText().toString();
            SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
            String taikhoan = sharedPreferences.getString("taikhoan","");
            String matkhaucheck = sharedPreferences.getString("matkhau","");
            if (matkhau.equals(matkhaucheck)){
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(ChiTietNguoiDung.this);
                boolean check = nguoiDungDAO.doiThongTinNguoiDung(taikhoan,hoten,email,sdt);
                if (check){
                    Toast.makeText(ChiTietNguoiDung.this,"Đổi Thông Tin Thành Công",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("hoTen", hoten);
                    editor.putString("email", email);
                    editor.putString("sdt", sdt);
                    editor.apply();
                    recreate();
                }else {Toast.makeText(ChiTietNguoiDung.this,"Đổi thông tin Không Thành Công",Toast.LENGTH_SHORT).show();}

            }else {Toast.makeText(ChiTietNguoiDung.this, "Nhập Mật Khẩu Không Đúng", Toast.LENGTH_LONG).show();}
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
                        Intent intent = new Intent(ChiTietNguoiDung.this,DangNhapActivity.class);
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

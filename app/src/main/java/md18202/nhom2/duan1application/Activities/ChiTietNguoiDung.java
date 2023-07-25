package md18202.nhom2.duan1application.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.R;

public class ChiTietNguoiDung extends AppCompatActivity {
    private ImageView imgAvatar_chiTiet, imgEdit_chiTiet;
    private TextView tvName_chiTiet, tvPhoneNumber_chiTiet, tvEmail_chiTiet, tvDoiMatKhau_chiTiet, tvLoaiTaiKhoan_chiTiet;
    private Button btnLogout_chiTiet;

    private Uri selectedImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_dung);

        //Anh xa
        imgAvatar_chiTiet = findViewById(R.id.imgAvatar_chiTiet);
        imgEdit_chiTiet = findViewById(R.id.imgEdit_chiTiet);
        tvName_chiTiet = findViewById(R.id.tvName_chiTiet);
        tvPhoneNumber_chiTiet = findViewById(R.id.tvPhoneNumber_chiTiet);
        tvEmail_chiTiet = findViewById(R.id.tvEmail_chiTiet);
        tvDoiMatKhau_chiTiet = findViewById(R.id.tvDoiMatKhau_chiTiet);
        tvLoaiTaiKhoan_chiTiet = findViewById(R.id.tvLoaiTaiKhoan_chiTiet);
        btnLogout_chiTiet = findViewById(R.id.btnLogout_chiTiet);

        //Get data nguoi dung tu sharePre da luu khi dang nhap
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        String imgSrc = sharedPreferences.getString("imgSrc", "");
        String hoTen = sharedPreferences.getString("hoTen", "");
        String soDienThoai = sharedPreferences.getString("soDienThoai", "");
        String email = sharedPreferences.getString("email", "");
        int loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan", -1);

        //Set data cho cac widget
        boolean isUri = imgSrc.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgSrc)).into(imgAvatar_chiTiet);
        } else {
            int idResource = this.getResources().getIdentifier(imgSrc, "drawable", this.getPackageName());
            imgAvatar_chiTiet.setImageResource(idResource);
        }
        tvName_chiTiet.setText(hoTen);
        tvPhoneNumber_chiTiet.setText(soDienThoai);
        tvEmail_chiTiet.setText(email);
        if (loaiTaiKhoan == 0) {
            tvLoaiTaiKhoan_chiTiet.setText("Người dùng");
        } else {
            tvLoaiTaiKhoan_chiTiet.setText("Admin");
        }

        //Đổi mật khẩu
        tvDoiMatKhau_chiTiet.setPaintFlags(tvDoiMatKhau_chiTiet.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvDoiMatKhau_chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                doiMatKhau(nguoiDung_id);
            }
        });

        //Đổi thông tin người dùng
        imgEdit_chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here
            }
        });

        //Dang xuat
        btnLogout_chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nút đăng xuất được nhấn
                // Xóa dữ liệu SharedPreferences hoặc thực hiện các thao tác đăng xuất khác
                SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(ChiTietNguoiDung.this, DangNhapActivity.class);
                startActivity(intent);
                finish(); // Đóng màn hình ChiTietNguoiDungActivity
            }
        });

        //Cập nhật thông tin người dùng
        imgEdit_chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNguoiDunng(imgEdit_chiTiet);
            }
        });
    }

    public void doiMatKhau(int nguoiDung_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietNguoiDung.this)
                .setNegativeButton("Cập nhật", null)
                .setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText edtOldPass_changePass = view.findViewById(R.id.edtOldPass_changePass);
        EditText edtNewPass_changePass = view.findViewById(R.id.edtNewPass_changePass);
        EditText edtRepeatNewPass_changePass = view.findViewById(R.id.edtRepeatNewPass_changePass);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEFE0")));
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass_changePass.getText().toString();
                String newPass = edtNewPass_changePass.getText().toString();
                String reNewPass = edtRepeatNewPass_changePass.getText().toString();
                if (checkChangePass(oldPass, newPass, reNewPass)) {
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getApplicationContext());
                    boolean check = nguoiDungDAO.doiMatKhau(nguoiDung_id, reNewPass);
                    if (check) {
                        Toast.makeText(ChiTietNguoiDung.this, "Thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public boolean checkChangePass(String oldPass, String newPass, String reNewPass) {
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        String matKhau = sharedPreferences.getString("matKhau", "");
        if (oldPass.equalsIgnoreCase(matKhau)) {
            if (newPass.equalsIgnoreCase(reNewPass)) {
                return true;
            } else {
                Toast.makeText(this, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Mật khẩu cũ không chính xác!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    public void updateNguoiDunng(ImageView imgEdit_chiTiet) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietNguoiDung.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cap_nhat_nguoi_dung, null);
        ImageView imgNewAvt_update = view.findViewById(R.id.imgNewAvt_update);
        EditText edtNewName_update = view.findViewById(R.id.edtNewName_update);
        EditText edtNewPhome_update = view.findViewById(R.id.edtNewPhome_update);
        EditText edtNewEmail_update = view.findViewById(R.id.edtNewEmail_update);
        TextView tvXoaTaiKhoan_dialog_update = view.findViewById(R.id.tvXoaTaiKhoan_dialog_update);
        Button btnCapNhat_dialog_update = view.findViewById(R.id.btnCapNhat_dialog_update);
        builder.setView(view);

        //Get data nguoi dung tu sharePre da luu khi dang nhap
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        String imgSrc = sharedPreferences.getString("imgSrc", "");
        String hoTen = sharedPreferences.getString("hoTen", "");
        String soDienThoai = sharedPreferences.getString("soDienThoai", "");
        String email = sharedPreferences.getString("email", "");
        int loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan", -1);

        //Set data cho cac widget
        boolean isUri = imgSrc.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgSrc)).into(imgNewAvt_update);
        } else {
            int idResource = this.getResources().getIdentifier(imgSrc, "drawable", this.getPackageName());
            imgNewAvt_update.setImageResource(idResource);
        }
        edtNewName_update.setText(hoTen);
        edtNewPhome_update.setText(soDienThoai);
        edtNewEmail_update.setText(email);

        //Get link Uri mới khi người dùng chọn ảnh mới
        imgNewAvt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //Hàm này ko dùng được cho dialog
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
//            imgNewAvt.setImageURI(selectedImageUri);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
}

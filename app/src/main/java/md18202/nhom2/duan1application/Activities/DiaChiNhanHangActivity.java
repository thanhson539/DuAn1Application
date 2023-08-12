package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.R;

public class DiaChiNhanHangActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvWord_limit;
    EditText edTen_nguoi_nhan, edSo_dien_thoai, edDia_chi, edTinh_thanh_pho, edQuan_huyen, edXa_phuong;
    Button btnThanh_toan;
    int tinh = 0;
    int huyen = 0;
    int xa = 0;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    String tenNguoiDung, soDienThoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang);
        imgBack = findViewById(R.id.imgBack);
        tvWord_limit = findViewById(R.id.tvLimit_word);
        edTen_nguoi_nhan = findViewById(R.id.edTen_nguoi_nhan);
        edSo_dien_thoai = findViewById(R.id.edSo_dien_thoai);
        edDia_chi = findViewById(R.id.edDia_chi_nhan_hang);
        edTinh_thanh_pho = findViewById(R.id.edTinh_thanh_pho);
        edQuan_huyen = findViewById(R.id.edQuan_huyen);
        edXa_phuong = findViewById(R.id.edPhuong_xa);
        btnThanh_toan = findViewById(R.id.btnThanh_toan);

        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDUng_id", -1);
        tenNguoiDung = sharedPreferences.getString("hoTen","");
        soDienThoai = sharedPreferences.getString("soDienThoai","");

        edTen_nguoi_nhan.setEnabled(false);
        edSo_dien_thoai.setEnabled(false);
        edTen_nguoi_nhan.setText(tenNguoiDung);
        edSo_dien_thoai.setText(soDienThoai);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        edTen_nguoi_nhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==50){
                    Toast.makeText(DiaChiNhanHangActivity.this, "Nhap ten ngan thoi", Toast.LENGTH_SHORT).show();
                }
                tvWord_limit.setText(""+s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edTinh_thanh_pho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTinhThanhPho();
            }
        });

        edQuan_huyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuanHuyen();
            }
        });

        edXa_phuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showXaPhuong();
            }
        });

        btnThanh_toan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate() > 0){
                    String diaChi = edDia_chi.getText().toString() +" - "+edXa_phuong.getText().toString()
                    +" - "+edQuan_huyen.getText().toString()+" - "+edTinh_thanh_pho.getText().toString();
                    Intent getIntent = getIntent();
                    int tongTien = getIntent.getIntExtra("tongTien", 0);
                    List<GioHang> listGioHang = (List<GioHang>) getIntent.getSerializableExtra("listGioHang");
                    Intent intent = new Intent(DiaChiNhanHangActivity.this, PhuongThucThanhToanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("diaChi", diaChi);
                    bundle.putInt("tongTien", tongTien);
                    bundle.putString("sdt", edSo_dien_thoai.getText().toString());
                    bundle.putString("nguoiNhan", edTen_nguoi_nhan.getText().toString());
                    bundle.putSerializable("listGioHang", (Serializable) listGioHang);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void showTinhThanhPho(){
        String thanhPho[] = {"Hòa Bình","Phú Thọ","Thái Bình","BẮc Giang","Nam Định"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tỉnh/Thành Phố")
                .setSingleChoiceItems(thanhPho, tinh, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edTinh_thanh_pho.setText(""+thanhPho[which]);
                        tinh = which;
                        dialog.dismiss();
                        edQuan_huyen.setText("");
                        edXa_phuong.setText("");
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showQuanHuyen() {
        String[] quanHuyen = new String[0];
        if (tinh == 0) {
            quanHuyen = new String[]{"Đà Bắc","Kim Bôi","Lạc Sơn","Kỳ Sơn","Lương Sơn"};
        }else if(tinh == 1){
            quanHuyen = new String[]{"Đoan Hùng","Hạ Hòa","Thanh Sơn","Thanh Thủy","Lâm Thao"};
        } else if (tinh == 2) {
            quanHuyen = new String[]{"Đông Hưng","Hưng Hà","Thái Thụy","Tiền Hải","Vũ Thư"};
        } else if (tinh == 3) {
            quanHuyen = new String[]{"Hiệp Hoà","Lục Nam","Lạng Giang","Lục Ngạn","Sơn Động"};
        } else if (tinh == 4) {
            quanHuyen = new String[]{"Giao Thủy","Hải Hậu","Mỹ Lộc","Nam Trực","Vụ Bản"};
        }
        String[] finalQuanHuyen = quanHuyen;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quận/Huyện")
                .setSingleChoiceItems(quanHuyen, huyen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edQuan_huyen.setText("" + finalQuanHuyen[which]);
                        huyen = which;
                        dialog.dismiss();
                        edXa_phuong.setText("");
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showXaPhuong(){
        String[] xaPhuong = new String[0];
        if (tinh == 0) {
            //Hòa Bình
            if(huyen==0){
                //Đà Băc
                xaPhuong = new String[]{"Thị Trấn Đà Bắc","Tu Lý","Cao Sơn","Đồng Chum","Toàn Sơn"};
            } else if (huyen == 1) {
                xaPhuong = new String[]{"Thị trấn Bo","Đú Sáng","Bình Sơn","Bắc Sơn","Nật Sơn"};
            } else if (huyen == 2) {
                xaPhuong = new String[]{"Ân Nghĩa","Mỹ Thành","Văn Sơn","Tân Lập","Yên Phú"};
            } else if (huyen == 3) {
                xaPhuong = new String[]{"Thị trấn Kỳ Sơn","Hợp Thịnh","Phúc Tiến","Mông Hóa","Độc Lập"};
            } else if (huyen == 4) {
                xaPhuong = new String[]{"Thị trấn Lương Sơn","Lâm Sơn","Hòa Sơn","Trường Sơn","Tân Vinh"};
            }

        }else if(tinh == 1){
            //Phú Thọ
            if(huyen==0){
                //Đoan Hùng
                xaPhuong = new String[]{"Thị trấn Đoan Hùng","Đông Khê","Nghinh Xuyên","Hùng Quan","Bằng Luân"};
            } else if (huyen == 1) {
                xaPhuong = new String[]{"Thị trấn Hạ Hoà","Đại Phạm","Hậu Bổng","Đan Hà","Hà Lương"};
            } else if (huyen == 2) {
                xaPhuong = new String[]{"Thị trấn Thanh Sơn","Sơn Hùng","Địch Quả","Giáp Lai","Thục Luyện"};
            } else if (huyen == 3) {
                xaPhuong = new String[]{"Đào Xá","Thạch Đồng","Xuân Lộc","Tân Phương","Sơn Thủy"};
            } else if (huyen == 4) {
                xaPhuong = new String[]{"Thị trấn Lâm Thao","Tiên Kiên","Hùng Sơn","Xuân Lũng","Xuân Huy"};
            }
        } else if (tinh == 2) {
            //Thái Bình
            if(huyen==0){
                xaPhuong = new String[]{"Thị trấn Đông Hưng","Đô Lương","Đông Phương","Liên Giang","An Châu"};
            } else if (huyen == 1) {
                xaPhuong = new String[]{"Thị trấn Hưng Hà","Điệp Nông","Tân Lễ","Cộng Hòa","Dân Chủ"};
            } else if (huyen == 2) {
                xaPhuong = new String[]{"Thị trấn Diêm Điền","Thụy Tân","Thụy Trường","Hồng Quỳnh","Thụy Dũng"};
            } else if (huyen == 3) {
                xaPhuong = new String[]{"Thị trấn Tiền Hải","Đông Hải","Đông Trà","Đông Long","Đông Quí"};
            } else if (huyen == 4) {
                xaPhuong = new String[]{"Thị trấn Vũ Thư","Hồng Lý","Đồng Thanh","Xuân Hòa","Hiệp Hòa"};
            }
        } else if (tinh == 3) {
            //Bắc Giang
            if(huyen==0){
                xaPhuong = new String[]{"Thị trấn Thắng","Đồng Tân","Thanh Vân","Hoàng Lương","Hoàng Vân"};
            } else if (huyen == 1) {
                xaPhuong = new String[]{"Thị trấn Đồi Ngô","Thị trấn Lục Nam","Đông Hưng","Tam Dị","Bảo Sơn"};
            } else if (huyen == 2) {
                xaPhuong = new String[]{"Thị trấn Kép","Thị trấn Vôi","Nghĩa Hòa","Nghĩa Hưng","Quang Thịnh"};
            } else if (huyen == 3) {
                xaPhuong = new String[]{"Thị trấn Chũ","Cấm Sơn","Tân Sơn","Phong Minh"," Phong Vân"};
            } else if (huyen == 4) {
                xaPhuong = new String[]{"Thị trấn An Châu","Thị trấn Thanh Sơn","Thạch Sơn","Vân Sơn","Hữu Sản"};
            }
        } else if (tinh == 4) {
            //Nam Định
            if(huyen==0){
                xaPhuong = new String[]{"Thị trấn Ngô Đồng","Thị trấn Quất Lâm","Giao Hương","Hồng Thuận","Giao Thiện"};
            } else if (huyen == 1) {
                xaPhuong = new String[]{"Thị trấn Yên Định","Thị trấn Cồn","Thị trấn Thịnh Long","Hải Nam","Hải Trung"};
            } else if (huyen == 2) {
                xaPhuong = new String[]{"Thị trấn Mỹ Lộc","Mỹ Hà","Mỹ Tiến","Mỹ Thắng","Mỹ Trung"};
            } else if (huyen == 3) {
                xaPhuong = new String[]{"Thị trấn Nam Giang","Nam Mỹ","Điền Xá","Nghĩa An","Nam Thắng"};
            } else if (huyen == 4) {
                xaPhuong = new String[]{"Thị trấn Gôi","Minh Thuận","Hiển Khánh","Tân Khánh","Hợp Hưng"};
            }
        }
        String[] finalXaPhuong = xaPhuong;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xã/Phường")
                .setSingleChoiceItems(xaPhuong, xa, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edXa_phuong.setText(""+ finalXaPhuong[which]);
                        xa = which;
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int validate(){
        int check =1;
        if(edTen_nguoi_nhan.getText().length() == 0 || edSo_dien_thoai.getText().length() ==0
        || edDia_chi.getText().length() == 0|| edTinh_thanh_pho.getText().length()==0
        || edQuan_huyen.getText().length() ==0 || edXa_phuong.getText().length() == 0){
            check = -1;
            Toast.makeText(this, "Hãy nhập đầy đủ địa chỉ", Toast.LENGTH_SHORT).show();
        }
        return check;
    }

}
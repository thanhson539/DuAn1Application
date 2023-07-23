package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import md18202.nhom2.duan1application.R;

public class DiaChiNhanHangActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvWord_limit;
    EditText edTen_nguoi_nhan, edSo_dien_thoai, edDia_chi, edTinh_thanh_pho, edQuan_huyen, edXa_phuong;
    Button btnThanh_toan;
    int tinh = 0;
    int huyen = 0;

    int xa = 0;

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
        builder.setTitle("Tỉnh/Thành Phố")
                .setSingleChoiceItems(quanHuyen, huyen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edQuan_huyen.setText("" + finalQuanHuyen[which]);
                        huyen = which;
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showXaPhuong(){
        String thanhPho[] = {"Hòa Bình","Phú Thọ","Thái Bình","BẮc Giang","Nam Định"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tỉnh/Thành Phố")
                .setSingleChoiceItems(thanhPho, tinh, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edTinh_thanh_pho.setText(""+thanhPho[which]);
                        tinh = which;
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
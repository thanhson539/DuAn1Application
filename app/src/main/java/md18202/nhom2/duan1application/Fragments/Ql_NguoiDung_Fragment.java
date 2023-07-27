package md18202.nhom2.duan1application.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Activities.DangKyTaiKhoanActivity;
import md18202.nhom2.duan1application.Activities.DangNhapActivity;
import md18202.nhom2.duan1application.Adapters.NguoiDungAdapter;
import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.Models.NguoiDung;
import md18202.nhom2.duan1application.R;

public class Ql_NguoiDung_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private NguoiDungDAO nguoiDungDAO;

    private ArrayList<NguoiDung> list;


    private FloatingActionButton floatbtnAddNguoiDung;

    public Ql_NguoiDung_Fragment() {
        // Required empty public constructor
    }


    public static Ql_NguoiDung_Fragment newInstance() {
        Ql_NguoiDung_Fragment fragment = new Ql_NguoiDung_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rycView_NguoiDung);
        floatbtnAddNguoiDung = view.findViewById(R.id.floatbtnAddNguoiDung);

        floatbtnAddNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogAddNguoiDung();
            }
        });


        nguoiDungDAO = new NguoiDungDAO(getContext());
        loadListNguoiDung(recyclerView);
    }

    private void loadListNguoiDung(RecyclerView recyclerView) {
        list = nguoiDungDAO.getDsNguoiDung();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NguoiDungAdapter adapter = new NguoiDungAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

    private void ShowDialogAddNguoiDung() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tk_qlndung,null);


        builder.setView(view);

        EditText  edtAddname = view.findViewById(R.id.edtName_Add_qlnd);
        EditText  edtAddemail = view.findViewById(R.id.edtEmail_U_qlnd);
        EditText  edtAddPhone = view.findViewById(R.id.edtPhoneNumber_U_qlnd);
        EditText  edtAddUserName = view.findViewById(R.id.edtUsername_U_qlnd);
        EditText  edtAddPass = view.findViewById(R.id.edtPassword_U_qlnd);

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String imgSrc = "avatar_thanh_son";
                String edtAddName = edtAddname.getText().toString();
                String edtAddEmail = edtAddemail.getText().toString();
                String edtAddphone = edtAddPhone.getText().toString();
                String edtAdduserName = edtAddUserName.getText().toString();
                String edtAddpass = edtAddPass.getText().toString();

                if (validateForm(imgSrc, edtAddName, edtAddphone, edtAddEmail, edtAdduserName, edtAddpass)) {
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getContext());
                    NguoiDung nguoiDung = new NguoiDung(imgSrc, edtAddName, edtAddphone, edtAddEmail, edtAdduserName, edtAddpass, 0, 0);
                    if(nguoiDungDAO.themTaiKhoanQL(nguoiDung)){

                        Toast.makeText(getContext(), "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        // Xóa danh sách người dùng hiện tại
                        list.clear();
                        // Cập nhật danh sách người dùng sau khi thêm mới
                        list.addAll(nguoiDungDAO.getDsNguoiDung());
                        // Thông báo cho adapter rằng dữ liệu đã thay đổi
                        recyclerView.getAdapter().notifyDataSetChanged();


                    }else {
                        Toast.makeText(getContext(), "Thêm tài khoản thất bại, hãy kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
    public boolean validateForm(String selectedImageUri, String name, String phoneNumber, String email, String username, String password) {
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getContext());
        boolean checkTonTai = nguoiDungDAO.checkTaiKhoanTonTai(username);
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com")) {
            Toast.makeText(getContext(), "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(getContext(), "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Vui lòng thêm ảnh của bạn", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkTonTai) {
            Toast.makeText(getContext(), "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}

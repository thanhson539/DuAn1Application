package md18202.nhom2.duan1application.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;


public class SanPham_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private SanPhamDAO sanPhamDAO;
    private FloatingActionButton floatbtnSanpham;

    public SanPham_Fragment() {
        // Required empty public constructor
    }


    public static SanPham_Fragment newInstance() {
        SanPham_Fragment fragment = new SanPham_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san_pham_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rycView_SanPham);
        floatbtnSanpham = view.findViewById(R.id.floatbtnSanPham);

        floatbtnSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFloatButton();
            }
        });


        sanPhamDAO = new SanPhamDAO(getContext());
        loatDate(recyclerView);
    }

    public void loatDate(RecyclerView recyclerView) {
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPhamADM();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }



    public void showDialogFloatButton(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
        LayoutInflater inflater  =getLayoutInflater();
        View view  = inflater.inflate(R.layout.dialog_floatbutton_sanpham, null);
        builder.setView(view);
        Spinner spnLoaiSanPham  =view.findViewById(R.id.spnSanPham);
        EditText edThemAnh = view.findViewById(R.id.edSanPhamThemAnh);
        EditText edThemTen = view.findViewById(R.id.edSanPhamThemTenSP);
        EditText edThemGia = view.findViewById(R.id.edSanPhamThemGiaSP);
        EditText edThemMoTa = view.findViewById(R.id.edSanPhamMoTaLoaiSP);
        EditText edThemSoLuong = view.findViewById(R.id.edSanPhamThemSoLuongSP);



        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Thêm Mới", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog  =builder.create();
        dialog.show();
    }
}
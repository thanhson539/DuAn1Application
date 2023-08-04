package md18202.nhom2.duan1application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter1;
import md18202.nhom2.duan1application.DAO.HoaDonDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.DAO.SanPhamYeuThichDAO;
import md18202.nhom2.duan1application.Models.HoaDon;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.Models.SanPhamYeuThich;
import md18202.nhom2.duan1application.R;


public class SanPhamYeuThichFragment extends Fragment {
    private RecyclerView ryc_sanPhamYeuThich;
    private SanPhamYeuThichDAO sanPhamDAO;
    SanPhamAdapter1 sanPhamAdapter1;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san_pham_yeu_thich2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ryc_sanPhamYeuThich = view.findViewById(R.id.ryc_sanPhamYeuThich);
        sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        sanPhamDAO = new SanPhamYeuThichDAO(getContext());
        ArrayList<SanPham> list;
        list = sanPhamDAO.getSanPhamYeuThich(nguoiDung_id);
        sanPhamAdapter1 = new SanPhamAdapter1(getContext(), list);

        ryc_sanPhamYeuThich.setAdapter(sanPhamAdapter1);
    }
}
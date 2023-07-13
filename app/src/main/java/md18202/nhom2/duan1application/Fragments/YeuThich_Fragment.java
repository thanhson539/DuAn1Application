package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter1;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class YeuThich_Fragment extends Fragment {
    private RecyclerView ryc_sanPhamYeuThich;
    private SanPhamDAO sanPhamDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham_yeu_thich, container, false);
        ryc_sanPhamYeuThich = view.findViewById(R.id.ryc_sanPhamYeuThich);
        sanPhamDAO = new SanPhamDAO(getContext());
        loadData(ryc_sanPhamYeuThich);
        return view;
    }

    public void loadData(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPhamYeuThich();
        SanPhamAdapter1 adapter1 = new SanPhamAdapter1(getContext(), list);
        recyclerView.setAdapter(adapter1);
    }
}

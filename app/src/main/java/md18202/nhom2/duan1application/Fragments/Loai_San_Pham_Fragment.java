package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.LoaiSanPhamAdapter;
import md18202.nhom2.duan1application.Adapters.SanPhamAdapter2;
import md18202.nhom2.duan1application.DAO.LoaiSanPhamDAO;
import md18202.nhom2.duan1application.Models.LoaiSanPham;
import md18202.nhom2.duan1application.R;


public class Loai_San_Pham_Fragment extends Fragment {


private RecyclerView recyclerView;
private LoaiSanPhamDAO sanPhamDAO;
    public Loai_San_Pham_Fragment() {
        // Required empty public constructor
    }


    public static Loai_San_Pham_Fragment newInstance() {
        Loai_San_Pham_Fragment fragment = new Loai_San_Pham_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_loai__san__pham_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_LoaiSanPham);

        loatDate(recyclerView);
    }
    public void loatDate(RecyclerView recyclerView){
        sanPhamDAO  = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list = sanPhamDAO.getDsLoaiSanPham();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        LoaiSanPhamAdapter loaiSanPhamAdapter = new LoaiSanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(loaiSanPhamAdapter);
    }
}
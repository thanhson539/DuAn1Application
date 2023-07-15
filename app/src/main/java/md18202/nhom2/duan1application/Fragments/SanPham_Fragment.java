package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;


public class SanPham_Fragment extends Fragment {

 private RecyclerView recyclerView;
 private SanPhamDAO sanPhamDAO;

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
        recyclerView  =view.findViewById(R.id.rycView_SanPham);

        sanPhamDAO  = new SanPhamDAO(getContext());
        loatDate(recyclerView);
    }
    public void loatDate(RecyclerView recyclerView){
        ArrayList<SanPham>list = sanPhamDAO.getDsSanPham();
        RecyclerView.LayoutManager layoutManager  =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SanPhamAdapter adapter  =new SanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }
}
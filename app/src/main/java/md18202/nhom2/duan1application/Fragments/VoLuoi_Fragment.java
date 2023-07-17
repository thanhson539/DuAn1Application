package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter2;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class VoLuoi_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private SanPhamDAO sanPhamDAO;
    public VoLuoi_Fragment() {
        // Required empty public constructor
    }



    public static VoLuoi_Fragment newInstance() {
        VoLuoi_Fragment fragment = new VoLuoi_Fragment();

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
        return inflater.inflate(R.layout.fragment_vo_luoi_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView_voluoi);
        sanPhamDAO = new SanPhamDAO(getContext());
        loadDataGridLayout(recyclerView);
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getDsVoLuoi();
        SanPhamAdapter2 adapter = new SanPhamAdapter2(getContext(), list);
        recyclerView.setAdapter(adapter);
    }
}
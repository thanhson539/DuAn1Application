package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter1;
import md18202.nhom2.duan1application.Adapters.SanPhamAdapter2;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Model.SanPham;
import md18202.nhom2.duan1application.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView_frame4;
    private SanPhamDAO sanPhamDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_frame4 = view.findViewById(R.id.recyclerView_frame4);
        sanPhamDAO = new SanPhamDAO(getContext());

        //load Data
//        loadData(recyclerView_frame4);
        loadDataGridLayout(recyclerView_frame4);
        return view;
    }

    public void loadData(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPham();
        SanPhamAdapter1 adapter = new SanPhamAdapter1(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPham();
        SanPhamAdapter2 adapter = new SanPhamAdapter2(getContext(), list);
        recyclerView.setAdapter(adapter);
    }
}

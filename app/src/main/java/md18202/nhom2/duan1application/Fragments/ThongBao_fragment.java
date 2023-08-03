package md18202.nhom2.duan1application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.ThongBaoAdapter;
import md18202.nhom2.duan1application.DAO.ThongBaoDAO;
import md18202.nhom2.duan1application.Models.ThongBao;
import md18202.nhom2.duan1application.R;

public class ThongBao_fragment extends Fragment {
    private RecyclerView ryc_thongBao;
    private ThongBaoDAO thongBaoDAO;
    private ArrayList<ThongBao> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        ryc_thongBao = view.findViewById(R.id.ryc_thongBao);
        list = new ArrayList<>();
        loadData(ryc_thongBao, list);
        return view;
    }

    public void loadData(RecyclerView recyclerView, ArrayList list) {
        thongBaoDAO = new ThongBaoDAO(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        list.clear();
        list = thongBaoDAO.getDsThongBaoByNguoiDung_id(nguoiDung_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ThongBaoAdapter adapter = new ThongBaoAdapter(getContext(), list);
        ryc_thongBao.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_thongBao, list);
    }
}

package md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag;

import android.content.Context;
import android.content.SharedPreferences;
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

import md18202.nhom2.duan1application.Adapters.DonHangAdapter;
import md18202.nhom2.duan1application.DAO.HoaDonChiTietDAO;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.R;

public class DaGiao_Fragment extends Fragment {
    private RecyclerView ryc_daGiao;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_da_giao, container, false);
        ryc_daGiao = view.findViewById(R.id.ryc_daGiao);
        loadData(ryc_daGiao);
        return view;
    }
    private void loadData(RecyclerView ryc_daGiao){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        ArrayList<HoaDonChiTiet> list = hoaDonChiTietDAO.getDonHangByHDCT(3,nguoiDung_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ryc_daGiao.setLayoutManager(linearLayoutManager);
        DonHangAdapter adapter = new DonHangAdapter(getContext(),list);
        ryc_daGiao.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_daGiao);
    }
}

package md18202.nhom2.duan1application.childFrag_of_DonHangFrag;

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

public class DangGiao_Fragment extends Fragment {
    private RecyclerView ryc_dangGiao;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_giao, container, false);
        ryc_dangGiao = view.findViewById(R.id.ryc_dangGiao);
        loadData(ryc_dangGiao);
        return view;
    }
    private void loadData(RecyclerView ryc_dangGiao){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        ArrayList<HoaDonChiTiet> list = hoaDonChiTietDAO.getDonHangByHDCT(2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ryc_dangGiao.setLayoutManager(linearLayoutManager);
        DonHangAdapter adapter = new DonHangAdapter(getContext(),list);
        ryc_dangGiao.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_dangGiao);
    }
}

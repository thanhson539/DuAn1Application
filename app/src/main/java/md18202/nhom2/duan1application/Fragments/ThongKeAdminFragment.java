package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter1;
import md18202.nhom2.duan1application.DAO.ThongKeDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;


public class ThongKeAdminFragment extends Fragment {
    SanPhamAdapter1 sanPhamAdapter1, sanPhamAdapter2;
    ThongKeDAO thongKeDAO;
    RecyclerView recycler_sp_banchay, recycler_sp_daban;
    TextView tvTong_tien, tvDonHang_thanhcong, tvDonHang_dahuy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    public void init(View view){
        recycler_sp_banchay = view.findViewById(R.id.recycler_sp_banchay);
        recycler_sp_daban = view.findViewById(R.id.recycler_sp_daban);
        tvTong_tien = view.findViewById(R.id.tvTong_tien);
        tvDonHang_thanhcong = view.findViewById(R.id.tvDonHang_thanhcong);
        tvDonHang_dahuy = view.findViewById(R.id.tvDonHang_dahuy);
        thongKeDAO = new ThongKeDAO(getContext());
        List<SanPham> listSp;
        List<SanPham> listSp2;
        listSp = thongKeDAO.getSanPhamBanChay();

        sanPhamAdapter1 = new SanPhamAdapter1(getContext(), (ArrayList<SanPham>) listSp);
        recycler_sp_banchay.setAdapter(sanPhamAdapter1);

        listSp2 = thongKeDAO.getSanPhamDaBan();
        sanPhamAdapter2 = new SanPhamAdapter1(getContext(), (ArrayList<SanPham>) listSp2);
        recycler_sp_daban.setAdapter(sanPhamAdapter2);

        tvTong_tien.setText(thongKeDAO.tongTien()+" vnd");
        tvDonHang_thanhcong.setText(""+thongKeDAO.donHangThanhCong());
    }
}
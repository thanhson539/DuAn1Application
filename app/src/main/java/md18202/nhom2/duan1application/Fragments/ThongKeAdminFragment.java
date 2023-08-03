package md18202.nhom2.duan1application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
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
    BarChart barChart;
    ArrayList list;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    int year;
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
        barChart = view.findViewById(R.id.barChart);
        thongKeDAO = new ThongKeDAO(getContext());
        sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);

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
        tvDonHang_dahuy.setText(""+thongKeDAO.donHangDaHuy());
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        getData();
        BarDataSet barDataSet = new BarDataSet(list,"Năm "+year);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        barChart.getDescription().setEnabled(true);
    }

    private void getData(){
        list = new ArrayList();
        list.add(new BarEntry(1f, thongKeDAO.getDataInMonthForAdmin( 1, year)));
        list.add(new BarEntry(2f, thongKeDAO.getDataInMonthForAdmin( 2, year)));
        list.add(new BarEntry(3f, thongKeDAO.getDataInMonthForAdmin( 3, year)));
        list.add(new BarEntry(4f, thongKeDAO.getDataInMonthForAdmin( 4, year)));
        list.add(new BarEntry(5f, thongKeDAO.getDataInMonthForAdmin( 5, year)));
        list.add(new BarEntry(6f, thongKeDAO.getDataInMonthForAdmin( 6, year)));
        list.add(new BarEntry(7f, thongKeDAO.getDataInMonthForAdmin( 7, year)));
        list.add(new BarEntry(8f, thongKeDAO.getDataInMonthForAdmin( 8, year)));
        list.add(new BarEntry(9f, thongKeDAO.getDataInMonthForAdmin( 9, year)));
        list.add(new BarEntry(10f, thongKeDAO.getDataInMonthForAdmin( 10, year)));
        list.add(new BarEntry(11f, thongKeDAO.getDataInMonthForAdmin( 11, year)));
        list.add(new BarEntry(12f, thongKeDAO.getDataInMonthForAdmin( 12, year)));
    }
}
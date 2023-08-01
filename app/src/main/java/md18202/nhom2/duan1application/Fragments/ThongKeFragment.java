package md18202.nhom2.duan1application.Fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import md18202.nhom2.duan1application.DAO.ThongKeDAO;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.R;


public class ThongKeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    TextView tvTong_tien;
    ThongKeDAO thongKeDAO;
    BarChart barChart;
    ArrayList list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTong_tien = view.findViewById(R.id.tvTong_tien);
        barChart = view.findViewById(R.id.barChart);
        thongKeDAO = new ThongKeDAO(getContext());
        tvTong_tien.setText(""+thongKeDAO.getSoTienDaMua()+" vnd");
        sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", getContext().MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);

        getData();
        BarDataSet barDataSet = new BarDataSet(list,"Hàng tháng");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        barChart.getDescription().setEnabled(true);

    }

    private void getData(){
        list = new ArrayList();
        list.add(new BarEntry(1f, thongKeDAO.getDataInMonth(nguoiDung_id, 1)));
        list.add(new BarEntry(2f, thongKeDAO.getDataInMonth(nguoiDung_id, 2)));
        list.add(new BarEntry(3f, thongKeDAO.getDataInMonth(nguoiDung_id, 3)));
        list.add(new BarEntry(4f, thongKeDAO.getDataInMonth(nguoiDung_id, 4)));
        list.add(new BarEntry(5f, thongKeDAO.getDataInMonth(nguoiDung_id, 5)));
        list.add(new BarEntry(6f, thongKeDAO.getDataInMonth(nguoiDung_id, 6)));
        list.add(new BarEntry(7f, thongKeDAO.getDataInMonth(nguoiDung_id, 7)));
        list.add(new BarEntry(8f, thongKeDAO.getDataInMonth(nguoiDung_id, 8)));
        list.add(new BarEntry(9f, thongKeDAO.getDataInMonth(nguoiDung_id, 9)));
        list.add(new BarEntry(10f, thongKeDAO.getDataInMonth(nguoiDung_id, 10)));
        list.add(new BarEntry(11f, thongKeDAO.getDataInMonth(nguoiDung_id, 11)));
        list.add(new BarEntry(12f, thongKeDAO.getDataInMonth(nguoiDung_id, 12)));
    }
}
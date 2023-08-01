package md18202.nhom2.duan1application.Fragments;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import md18202.nhom2.duan1application.DAO.ThongKeDAO;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.R;


public class ThongKeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    TextView tvTong_tien, tvSo_tien_trong_khoang, tvTu_ngay, tvDen_ngay;
    ThongKeDAO thongKeDAO;
    BarChart barChart;
    ArrayList list;
    int mYear, mMonth, mDay;
    EditText edTu_ngay, edDen_ngay;
    LinearLayout layout;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    boolean check;
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
        tvTu_ngay = view.findViewById(R.id.tvTu_ngay);
        tvDen_ngay = view.findViewById(R.id.tvDen_ngay);
        tvSo_tien_trong_khoang = view.findViewById(R.id.tvSo_tien_trong_khoang);
        edTu_ngay = view.findViewById(R.id.edTu_ngay);
        edDen_ngay = view.findViewById(R.id.edDen_ngay);
        layout = view.findViewById(R.id.linear);
        barChart = view.findViewById(R.id.barChart);
        thongKeDAO = new ThongKeDAO(getContext());
        sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", getContext().MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        tvTong_tien.setText(""+thongKeDAO.getSoTienDaMua(nguoiDung_id)+" vnd");

        muaHangTrongKhoang();

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

    private void muaHangTrongKhoang(){
        edTu_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, tuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        edDen_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, denNgay, mYear, mMonth, mDay);
                d.show();
            }
        });



    }

    DatePickerDialog.OnDateSetListener tuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mDay = dayOfMonth;
            mMonth = month;
            mYear = year;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edTu_ngay.setText(sdf.format(c.getTime()));
            check = true;
            if(edTu_ngay.getText().length() != 0 || edDen_ngay.getText().length() != 0){
                layout.setVisibility(View.VISIBLE);
                tvTu_ngay.setText(edTu_ngay.getText().toString());
                tvDen_ngay.setText(edDen_ngay.getText().toString());
                int tienTrongkhoang = thongKeDAO.tuNgayDenNgay(edTu_ngay.getText().toString(), edDen_ngay.getText().toString());
                if(tienTrongkhoang > 0){
                    tvSo_tien_trong_khoang.setText(""+tienTrongkhoang+" vnd");
                }else{
                    tvSo_tien_trong_khoang.setText("0_0");
                }
            }
        }
    };

    DatePickerDialog.OnDateSetListener denNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mDay = dayOfMonth;
            mMonth = month;
            mYear = year;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edDen_ngay.setText(sdf.format(c.getTime()));
            if(check){
                if(edTu_ngay.getText().length() != 0 || edDen_ngay.getText().length() != 0){
                    layout.setVisibility(View.VISIBLE);
                    tvTu_ngay.setText(edTu_ngay.getText().toString());
                    tvDen_ngay.setText(edDen_ngay.getText().toString());
                    int tienTrongkhoang = thongKeDAO.tuNgayDenNgay(edTu_ngay.getText().toString(), edDen_ngay.getText().toString());
                    if(tienTrongkhoang > 0){
                        tvSo_tien_trong_khoang.setText(""+tienTrongkhoang+" vnd");
                    }else{
                        tvSo_tien_trong_khoang.setText("0_0");
                    }
                }
            }else{
                edDen_ngay.setText("");
            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        muaHangTrongKhoang();
    }
}
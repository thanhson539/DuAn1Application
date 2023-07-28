package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.QL_BinhLuan_Adapter;
import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.R;


public class QuanLy_BL_Fragment extends Fragment {

    RecyclerView recyclerView;

    public QuanLy_BL_Fragment() {
        // Required empty public constructor
    }


    public static QuanLy_BL_Fragment newInstance(String param1, String param2) {
        QuanLy_BL_Fragment fragment = new QuanLy_BL_Fragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly__b_l_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView  = view.findViewById(R.id.recycQL_BinhLuan);
        loatDate();

    }


    public void loatDate(){
        BinhLuanDAO binhLuanDAO  =new BinhLuanDAO(getContext());
        ArrayList<BinhLuan> list  = binhLuanDAO.getDSBinhLuanCach2();
        QL_BinhLuan_Adapter adapter   =new QL_BinhLuan_Adapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loatDate();
    }
}
package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Adapters.NguoiDungAdapter;
import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.Models.NguoiDung;
import md18202.nhom2.duan1application.R;

public class Ql_NguoiDung_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private NguoiDungDAO nguoiDungDAO;

    private FloatingActionButton floatbtnAddNguoiDung;

    public Ql_NguoiDung_Fragment() {
        // Required empty public constructor
    }


    public static Ql_NguoiDung_Fragment newInstance() {
        Ql_NguoiDung_Fragment fragment = new Ql_NguoiDung_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rycView_NguoiDung);
        floatbtnAddNguoiDung = view.findViewById(R.id.floatbtnAddNguoiDung);

        floatbtnAddNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogAddNguoiDung();
            }
        });

        nguoiDungDAO = new NguoiDungDAO(getContext());
        loadListNguoiDung(recyclerView);
    }

    private void loadListNguoiDung(RecyclerView recyclerView) {
        ArrayList<NguoiDung> list = nguoiDungDAO.getDsNguoiDungQL();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NguoiDungAdapter adapter = new NguoiDungAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

    private void ShowDialogAddNguoiDung() {
    }
}

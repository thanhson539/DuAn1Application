package md18202.nhom2.duan1application.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter1;
import md18202.nhom2.duan1application.Adapters.SanPhamAdapter2;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView_frame4;
    private SanPhamDAO sanPhamDAO;
    ArrayList<SanPham> listtemporary;
    ArrayList<SanPham> oriList;
    private TextInputEditText edTimkiem;
    private SanPhamAdapter2 searchResultsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_frame4 = view.findViewById(R.id.recyclerView_frame4);
        sanPhamDAO = new SanPhamDAO(getContext());
        edTimkiem = view.findViewById(R.id.edTimKiem);


        oriList = sanPhamDAO.getDsSanPham();

        // Set up your RecyclerView and adapter

        ImageView searchButton = view.findViewById(R.id.imgTimKiem);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick(v);
            }
        });


        //load Data
//        loadData(recyclerView_frame4);
        loadDataGridLayout(recyclerView_frame4);
        return view;
    }

    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPham();
        SanPhamAdapter2 adapter = new SanPhamAdapter2(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    // tìm kiếm nhé

    public void onSearchButtonClick(View view) {
        String searchText = edTimkiem.getText().toString().toLowerCase();

        ArrayList<SanPham> searchResults = new ArrayList<>();
        oriList = sanPhamDAO.getDsSanPham();
        for (SanPham sanPham : oriList) {
            int giaSanPham = sanPham.getGiaSanPham();
            String tenSanPham = sanPham.getTenSanPham().toLowerCase();
            String normalizedMaSach = normalizeString(tenSanPham);
//            if (giaSanPham == Integer.parseInt(searchText) || ten.contains(searchText)) {
//                searchResults.add(sanPham);
//            }

            if (isNumeric(searchText)) {
                // Tìm kiếm theo giá sản phẩm
                if (giaSanPham <= Integer.parseInt(searchText)) {
                    searchResults.add(sanPham);
                }
            } else {
                // Tìm kiếm theo tên sản phẩm
                if (normalizedMaSach.toLowerCase().replace("đ", "d").contains(searchText.toLowerCase())) {
                    searchResults.add(sanPham);
                }
            }


        }

        SanPhamAdapter2 phieuMuonAdapter = new SanPhamAdapter2(getContext(), searchResults);
        // Gán adapter mới cho RecyclerView hoặc ListView của bạn
        recyclerView_frame4.setAdapter(phieuMuonAdapter);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    // loại bỏ dấu và chữ hoa
        private String normalizeString(String input) {
        //ó ý nghĩa là chuẩn hóa chuỗi input và loại bỏ các dấu diacritic trong chuỗi đó.
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("").toLowerCase();
    }

}

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.timkiem, menu);
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem menuItem = menu.findItem(R.id.idmenu);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setIconified(true);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//
//        // list lưu tạm
//        listtemporary = sanPhamDAO.getDsSanPham();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                oriList = new ArrayList<>();
//                for (SanPham sanPham : listtemporary) {
//                    String maSach = String.valueOf(sanPham.getGiaSanPham());
//                    String normalizedMaSach = normalizeString(maSach);
//
//                    if (normalizedMaSach.toLowerCase().contains(newText.toLowerCase()) ||
//                            normalizedMaSach.toLowerCase().replace("đ", "d").contains(newText.toLowerCase())) {
//                        oriList.add(sanPham);
//                    }
//                }
//
//
//                // Tạo adapter mới với danh sách tìm kiếm
//                SanPhamAdapter2 phieuMuonAdapter = new SanPhamAdapter2(getContext(), oriList);
//                // Gán adapter mới cho RecyclerView hoặc ListView của bạn
//                recyclerView_frame4.setAdapter(phieuMuonAdapter);
//                return true;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//

//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        super.onCreate(savedInstanceState);
//    }


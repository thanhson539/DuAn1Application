package md18202.nhom2.duan1application.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import md18202.nhom2.duan1application.Adapters.TabLayoutDonHangAdapter;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.ChoXacNhan_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DaGiao_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DaXacNhan_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DangGiao_Fragment;
import md18202.nhom2.duan1application.R;

public class DonHang_Fragment extends Fragment {
    private TabLayout tabLayout_donhang;
    private ViewPager2 viewPager_donhang;
    private TabLayoutDonHangAdapter TabLayoutDonHangAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        tabLayout_donhang = view.findViewById(R.id.tabLayout_donhang);
        viewPager_donhang = view.findViewById(R.id.viewPager_donhang);
        TabLayoutDonHangAdapter = new TabLayoutDonHangAdapter(getActivity());
        viewPager_donhang.setAdapter(TabLayoutDonHangAdapter);

        new TabLayoutMediator(tabLayout_donhang, viewPager_donhang, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đã xác nhận");
                        break;
                    case 2:
                        tab.setText("Đang giao");
                        break;
                    case 3:
                        tab.setText("Đã giao");
                        break;

                }
            }
        }).attach();

        return view;
    }
}

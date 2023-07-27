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

import md18202.nhom2.duan1application.R;

public class DonHang_Fragment extends Fragment {
    private TabLayout tabLayout_donhang;
    private ViewPager2 viewPager_donhang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        tabLayout_donhang = view.findViewById(R.id.tabLayout_donhang);
        viewPager_donhang = view.findViewById(R.id.viewPager_donhang);

        return view;
    }
}

package md18202.nhom2.duan1application.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.ChoXacNhan_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DaGiao_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DaXacNhan_Fragment;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DangGiao_Fragment;

public class TabLayoutDonHangAdapter extends FragmentStateAdapter {

    public TabLayoutDonHangAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChoXacNhan_Fragment();
            case 1:
                return new DaXacNhan_Fragment();
            case 2:
                return new DangGiao_Fragment();
            case 3:
                return new DaGiao_Fragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

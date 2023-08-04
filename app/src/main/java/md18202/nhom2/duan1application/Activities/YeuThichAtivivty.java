package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import md18202.nhom2.duan1application.Fragments.SanPhamYeuThichFragment;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class YeuThichAtivivty extends AppCompatActivity {

    SanPham sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich_ativivty);
        SanPhamYeuThichFragment fragment = new SanPhamYeuThichFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(YeuThichAtivivty.this, ChiTietSanPhamActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("sanPham", sanPham);
        intent1.putExtras(bundle1);
        startActivity(intent1);
        finish();
    }
}
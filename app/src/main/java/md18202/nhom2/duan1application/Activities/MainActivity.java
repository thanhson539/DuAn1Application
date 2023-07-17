package md18202.nhom2.duan1application.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import md18202.nhom2.duan1application.Fragments.HomeFragment;
import md18202.nhom2.duan1application.Fragments.Loai_San_Pham_Fragment;
import md18202.nhom2.duan1application.Fragments.SanPham_Fragment;
import md18202.nhom2.duan1application.Fragments.VoBasic_Fragment;
import md18202.nhom2.duan1application.Fragments.VoCoCao_Fragment;
import md18202.nhom2.duan1application.Fragments.VoCoThap_Fragment;
import md18202.nhom2.duan1application.Fragments.VoCoTrung_Fragment;
import md18202.nhom2.duan1application.Fragments.VoHoaTiet_Fragment;
import md18202.nhom2.duan1application.Fragments.VoLuoi_Fragment;
import md18202.nhom2.duan1application.Fragments.YeuThich_Fragment;
import md18202.nhom2.duan1application.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout_frame4;
    private Toolbar toolbar_frame4;
    private FrameLayout frameLayout_frame4;
    private NavigationView navigationView_frame4;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private TextView txtNameNav, txtsdtU , txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        drawerLayout_frame4 = findViewById(R.id.drawerLayout_frame4);
        toolbar_frame4 = findViewById(R.id.toolbar_frame4);
        frameLayout_frame4 = findViewById(R.id.frameLayout_frame4);
        navigationView_frame4 = findViewById(R.id.navigationView_frame4);
        View headerLayout = navigationView_frame4.getHeaderView(0);
        txtNameNav = headerLayout.findViewById(R.id.txtNameU);
        txtsdtU = headerLayout.findViewById(R.id.sdtU);
        txtEmail = headerLayout.findViewById(R.id.txtEmail);
        //Xử lý cho toolbar
        setSupportActionBar(toolbar_frame4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.header_menu);

        //set fragmentHome mặc định
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();

        //Action của navigationView
        setActionForNavigationView(navigationView_frame4);
        //CHuyển qua chi tiết người dùng
        headerLayout.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChiTietNguoiDung.class);
                startActivity(intent);
                drawerLayout_frame4.closeDrawer(GravityCompat.START);
            }
        });
    }

    public void setActionForNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuId = item.getItemId();
                fragmentManager = getSupportFragmentManager();
                if (menuId == R.id.menuTrangChu) {
                    fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoTrung) {
                    fragment = new VoCoTrung_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoCao) {
                    fragment = new VoCoCao_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoNgan) {
                    fragment = new VoCoThap_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuYeuThich) {
                    fragment = new YeuThich_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuLoaiSanPham) {
                    fragment  = new Loai_San_Pham_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuSanPham) {
                    fragment = new SanPham_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoLuoi) {
                    fragment = new VoLuoi_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoBasic) {
                    fragment = new VoBasic_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoHoaTiet) {
                    fragment = new VoHoaTiet_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                }

                drawerLayout_frame4.closeDrawer(GravityCompat.START);
                toolbar_frame4.setTitle(item.getTitle());
                return false;
            }
        });
        //Hiển thị thông tin sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG",MODE_PRIVATE);
        String hoten = sharedPreferences.getString("hoTen","");
        String sdtU = sharedPreferences.getString("sdt","");
        String email= sharedPreferences.getString("email","");
        txtNameNav.setText("Hi!"+hoten);
        txtsdtU.setText(sdtU);
        txtEmail.setText(email);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout_frame4.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
package md18202.nhom2.duan1application.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import md18202.nhom2.duan1application.Fragments.HomeFragment;
import md18202.nhom2.duan1application.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout_frame4;
    private Toolbar toolbar_frame4;
    private FrameLayout frameLayout_frame4;
    private NavigationView navigationView_frame4;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        drawerLayout_frame4 = findViewById(R.id.drawerLayout_frame4);
        toolbar_frame4 = findViewById(R.id.toolbar_frame4);
        frameLayout_frame4 = findViewById(R.id.frameLayout_frame4);
        navigationView_frame4 = findViewById(R.id.navigationView_frame4);

        //Xử lý cho toolbar
        setSupportActionBar(toolbar_frame4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.header_menu);

        //set fragmentHome mac dinh
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4,fragment).commit();

        //Action của navigationView
        setActionForNavigationView(navigationView_frame4);
    }
    public void setActionForNavigationView(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuId = item.getItemId();
                fragmentManager = getSupportFragmentManager();
                if (menuId == R.id.menuTrangChu){
                    fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                    drawerLayout_frame4.closeDrawer(GravityCompat.START);
                    toolbar_frame4.setTitle(item.getTitle());
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout_frame4.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
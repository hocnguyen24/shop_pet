package com.example.pet_pet;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;

import com.example.pet_pet.databinding.ActivityMainBinding;
import com.example.pet_pet.fragment.ThongKe;
import com.example.pet_pet.fragment.frgGianHang;
import com.example.pet_pet.fragment.frgGioHang;
import com.example.pet_pet.fragment.frgNapTien;
import com.example.pet_pet.fragment.frgQuanLyDonHang;
import com.example.pet_pet.fragment.frgQuanLyLoaiSanPham;
import com.example.pet_pet.fragment.frgQuanLyNapTien;
import com.example.pet_pet.fragment.frgQuanLyNguoiDung;
import com.example.pet_pet.fragment.frgQuanLySanPham;
import com.example.pet_pet.fragment.frgTrangChu;
import com.example.pet_pet.fragment.frg_Ve_Chung_Toi;
import com.example.pet_pet.fragment.frg_lich_su_don_hang;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //    Menu mMenu;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        navigationView = binding.navigationViewMain;

        binding.navigationViewMain.getHeaderView(0);
        Toolbar toolbar = binding.toolbarMain;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("PetShop");


        handleBottomNavigationItemSelected();
        if (savedInstanceState == null) {
            replaceFragment(new frgTrangChu());
            getSupportActionBar().setTitle("Trang chủ");
        }


        binding.btnProFile.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, Profile.class)));

        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.action_qlnguoidung) {
                replaceFragment(new frgQuanLyNguoiDung());
            } else if (item.getItemId() == R.id.action_qlsanpham) {
                replaceFragment(new frgQuanLySanPham());
            } else if (item.getItemId() == R.id.action_qlLoaisanpham) {
                replaceFragment(new frgQuanLyLoaiSanPham());
            } else if (item.getItemId() == R.id.action_qlDonHang) {
                replaceFragment(new frgQuanLyDonHang());
            } else if (item.getItemId() == R.id.action_qlNapTien) {
                replaceFragment(new frgQuanLyNapTien());
            } else if (item.getItemId() == R.id.action_qlThongKe) {
                replaceFragment(new ThongKe());
            } else if (item.getItemId() == R.id.action_qlVeChungToi) {
                replaceFragment(new frg_Ve_Chung_Toi());
            } else if (item.getItemId() == R.id.action_LichSu) {
                replaceFragment(new frg_lich_su_don_hang());
            } else if (item.getItemId() == R.id.action_qlDangXuat) {
                Intent intent = new Intent(MainActivity.this, man_hinh_dang_nhap.class);
                startActivity(intent);

            }
            getSupportActionBar().setTitle(item.getTitle());
            binding.drawerLayoutMain.closeDrawer(GravityCompat.START);

            return false;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        String loaiTaiKhoan = sharedPreferences.getString("loaitaikhoan", "");
        int sotien = sharedPreferences.getInt("sotien", 0);
        Menu menu = navigationView.getMenu();
        if (!loaiTaiKhoan.equals("admin")) {

            menu.findItem(R.id.action_qlnguoidung).setVisible(false);
            menu.findItem(R.id.action_qlsanpham).setVisible(false);
            menu.findItem(R.id.action_qlLoaisanpham).setVisible(false);
            menu.findItem(R.id.action_qlDonHang).setVisible(false);
            menu.findItem(R.id.action_qlNapTien).setVisible(false);
            menu.findItem(R.id.action_qlThongKe).setVisible(false);
        }else {
            menu.findItem(R.id.action_LichSu).setVisible(false);
        }
        String urlAnh = sharedPreferences.getString("anhtaikhoan", "");
        Picasso.get().load(urlAnh).into(binding.btnProFile);
        binding.txtSoTien.setText(String.valueOf(sotien));

//        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
//        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//    if (fragmentManager.getBackStackEntryCount() > 0) {
//        fragmentManager.popBackStack();
//    } else {
//        MainActivity.super.getOnBackPressedDispatcher().onBackPressed();
//    }
//            }
//        });
//        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
//                    // Toolbar đã hoàn toàn ẩn
//                    toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator()).start();
//                } else if (verticalOffset == 0) {
//                    // Toolbar hiện đang hiển thị hoàn toàn
//                    toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                } else {
//                    // Toolbar đang ẩn/hiện giữa đường trung bình
//                }
//            }
//        });
//        View rootView = findViewById(android.R.id.content);
//        rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            private int previousHeight = rootView.getHeight();
//            @Override
//            public boolean onPreDraw() {
//                int currentHeight = rootView.getHeight();
//
//                if (currentHeight < previousHeight) {
//                    // Keyboard is shown
//                    binding.navBottomMain.setVisibility(View.GONE);
//                } else if (currentHeight > previousHeight) {
//                    // Keyboard is hidden
//                    binding.navBottomMain.setVisibility(View.VISIBLE);
//                }
//
//                previousHeight = currentHeight;
//                return true;
//            }
//        });
//        rootView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // Keyboard is shown
//                    binding.navBottomMain.setVisibility(View.GONE);
//                } else {
//                    // Keyboard is hidden
//                    binding.navBottomMain.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }
//    @NonNull
//    @Override
//    public ViewModelStore getViewModelStore() {
//        return viewModelStore;
//    }
//@Override
//public void onBackPressed() {
//    FragmentManager fragmentManager = getSupportFragmentManager();
//    if (fragmentManager.getBackStackEntryCount() > 0) {
//        fragmentManager.popBackStack();
//    } else {
//        super.onBackPressed();
//    }
//}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayoutMain.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    //    private void hideKeyboard(@NonNull View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
    private void handleBottomNavigationItemSelected() {

        binding.navBottomMain.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_bot_home) {
                replaceFragment(new frgTrangChu());
            } else if (item.getItemId() == R.id.nav_bot_sanpham) {
                replaceFragment(new frgGianHang());

            } else if (item.getItemId() == R.id.nav_bot_giohang) {
                replaceFragment(new frgGioHang());

            } else if (item.getItemId() == R.id.nav_bot_naptien) {
                replaceFragment(new frgNapTien());
            }
            getSupportActionBar().setTitle(item.getTitle());
            return true;
        });
    }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, fragment).commit();
    }


}
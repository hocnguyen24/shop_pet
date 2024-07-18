package com.example.pet_pet.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pet_pet.Dao.GioHangDao;
import com.example.pet_pet.Dao.LoaiSanPhamDao;
import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Interface.OnButtonLoaiSP;
import com.example.pet_pet.Model.GioHang;
import com.example.pet_pet.Model.LoaiSanPham;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_chon_loai_san_pham;
import com.example.pet_pet.adapter.adapter_gian_hang;
import com.example.pet_pet.adapter.adapter_gio_hang;
import com.example.pet_pet.databinding.DialogBottomsheetSapxepBinding;
import com.example.pet_pet.databinding.FragmentFrgGianHangBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;




public class frgGianHang extends Fragment {

    public frgGianHang() {
        // Required empty public constructor
    }

    FragmentFrgGianHangBinding binding;
    View gView;
    private ArrayList<SanPham> list = new ArrayList<>();
    private ArrayList<LoaiSanPham> listLoaiSP = new ArrayList<>();
    SanPhamDao spDao;
    adapter_gian_hang adapterGianHang;

    private adapter_gio_hang gioHangAdapter;
    private LoaiSanPhamDao loaiSanPhamDao;
    private GioHangDao gioHangDao;
    adapter_chon_loai_san_pham adapterChonLoaiSanPham;
    private ArrayList<GioHang> gioHangArrayList = new ArrayList<>();
    // Thiết lập listener


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgGianHangBinding.inflate(inflater, container, false);
        gView = binding.getRoot();

        spDao = new SanPhamDao(getActivity());
        gioHangDao = new GioHangDao(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rcvGianHang.setLayoutManager(layoutManager);
        list = spDao.getsanphamall();

        loaiSanPhamDao = new LoaiSanPhamDao(getContext());
        listLoaiSP = loaiSanPhamDao.getalltheloai();
        adapterGianHang = new adapter_gian_hang(getActivity(), list);
        binding.rcvGianHang.setAdapter(adapterGianHang);
        gioHangAdapter = new adapter_gio_hang(getActivity(), gioHangArrayList);
        binding.rcvLoaiSanPham.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        adapterChonLoaiSanPham = new adapter_chon_loai_san_pham(listLoaiSP, getContext());
        binding.rcvLoaiSanPham.setAdapter(adapterChonLoaiSanPham);
        binding.btnTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterGianHang.clearData(); // Xóa tất cả dữ liệu hiện tại trong adapter
                list.clear(); // Xóa tất cả dữ liệu hiện tại trong danh sách
                list.addAll(spDao.getsanphamall()); // Thêm tất cả phần tử mới vào danh sách
                adapterGianHang.notifyDataSetChanged();
//                list = spDao.getsanphamall();
//                adapterGianHang.setData(list);
//                adapterGianHang.notifyDataSetChanged();

            }
        });

        adapterChonLoaiSanPham.setOnItemClickListener(new OnButtonLoaiSP() {
            @Override
            public void OnButtonLoaiSP(LoaiSanPham loaiSanPham) {
                int maLoaiSanPham = loaiSanPham.getMaloaisp();
                if (maLoaiSanPham != -1) {
//                    list = spDao.getSanPhaByMaLoaiSanPham(maLoaiSanPham);
//                    adapterGianHang.setData(list);
//                    adapterGianHang.notifyDataSetChanged();
                    adapterGianHang.clearData(); // Xóa tất cả dữ liệu hiện tại trong adapter
                    list.clear(); // Xóa tất cả dữ liệu hiện tại trong danh sách
                    list.addAll(spDao.getSanPhaByMaLoaiSanPham(maLoaiSanPham)); // Thêm tất cả phần tử mới vào danh sách
                    adapterGianHang.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Bạn đã chọn: "+ loaiSanPham.getTenloaisp(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSapXep();
            }
        });
        adapterGianHang.setOnAddToCartClickListener(sanPham -> themVaoGio(sanPham));

        adapterGianHang.setOnItemClickListener(position -> {
            // truyền mã đơn hàng được click để qua màn hình đơn hàng chi tiết gọi phương thức lấy ra đơn chi tiết bằng mã đơn hàng này
            Bundle bundle = new Bundle();
            bundle.putInt("maSanPham", list.get(position).getMasanpham());
            bundle.putString("tenLoaiSP", list.get(position).getTenloaisanpham());
            frgSanPhamChiTiet frgSanPhamChiTiet = new frgSanPhamChiTiet();
            frgSanPhamChiTiet.setArguments(bundle);

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.frameLayoutMain, frgSanPhamChiTiet);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        // Inflate the layout for this fragment
        return gView;
    }

    //Phương thức để lấy ra tồn kho của sản phẩm bằng mã của sản phẩm đấy
    private int getSoLuongSp(int maSanPham) {
        for (SanPham sanPham : list) {
            if (sanPham.getMasanpham() == maSanPham) {
                return sanPham.getSoluong();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy sản phẩm
    }

    //Phương thức thêm vào giỏ
    private void themVaoGio(SanPham sanPham) {

        // Lấy ra nhưng thông tin cần thiết của người dùng để thực hiện công việc
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("mataikhoan", 0);
        int maSanPham = sanPham.getMasanpham();
        int slSanPham = getSoLuongSp(maSanPham);

        //Gọi phương thức lấy ra danh sách giỏ hàng của từng người dùng
        gioHangArrayList = gioHangDao.getDanhSachGioHangByMaNguoiDung(mand);

        boolean isProductInCart = false;

        // duyệt qua danh sách giỏ hàng
        for (GioHang gioHang : gioHangArrayList) {

            //nếu sản phẩm được thêm đã có trong giỏ hàng thì thực hiện thêm số lượng
            if (gioHang.getMaSanPham() == maSanPham) {
                isProductInCart = true;
                // nếu số lượng trong giỏ hàng của sản phẩm được thêm ít hơn số lượng sản phẩm tồn kho thì cho phép +1 vào giỏ hàng
                if (gioHang.getSoLuongMua() < slSanPham) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    gioHangDao.updateGioHang(gioHang);
                    Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        // nếu sản phẩm được thêm vào giỏ chưa có trong giỏ thì thực hiện thêm giỏ hàng
        if (!isProductInCart) {
            if (slSanPham > 0) {
                gioHangDao.insertGioHang(new GioHang(maSanPham, mand, 1));
                Snackbar.make(getView(), "Đã thêm vào giỏ thành công", Snackbar.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showDialogSapXep() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DialogBottomsheetSapxepBinding layoutBinding = DialogBottomsheetSapxepBinding.inflate(getLayoutInflater());
        dialog.setContentView(layoutBinding.getRoot());

        layoutBinding.rbAZProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sanPham, SanPham t1) {
                        return sanPham.getTensanpham().compareToIgnoreCase(t1.getTensanpham());
                    }
                });
                adapterGianHang.notifyDataSetChanged();
            }
        });

        layoutBinding.rbZAProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sanPham, SanPham t1) {
                        return t1.getTensanpham().compareToIgnoreCase(sanPham.getTensanpham());
                    }
                });
                adapterGianHang.notifyDataSetChanged();
            }
        });
        layoutBinding.rbGiaTangDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sanPham, SanPham t1) {
                        return Integer.compare(sanPham.getGia(), t1.getGia());
                    }
                });
                adapterGianHang.notifyDataSetChanged();
            }
        });
        layoutBinding.rbGiaGiamDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sanPham, SanPham t1) {
                        return Integer.compare(t1.getGia(), sanPham.getGia());
                    }
                });
                adapterGianHang.notifyDataSetChanged();
            }
        });
        layoutBinding.rdSoluotBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sanPham, SanPham t1) {
                        return Integer.compare(t1.getSoLuotBanRa(), sanPham.getSoLuotBanRa());
                    }
                });
                adapterGianHang.notifyDataSetChanged();
            }
        });
//        layoutBinding.radioGroupProduct.setOnCheckedChangeListener((new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i == R.id.rbAZProduct) {
//                    Toast.makeText(getContext(), "Sắp xếp từ A - Z", Toast.LENGTH_SHORT).show();
//                    Collections.sort(list, new Comparator<SanPham>() {
//                        @Override
//                        public int compare(SanPham sanPham, SanPham t1) {
//                            return sanPham.getTensanpham().compareToIgnoreCase(t1.getTensanpham());
//                        }
//                    });
//                    adapterGianHang.notifyDataSetChanged();
//
//                } else if (i == R.id.rbZAProduct) {
//                    Toast.makeText(getContext(), "Sắp xếp từ Z - A", Toast.LENGTH_SHORT).show();
//                    Collections.sort(list, new Comparator<SanPham>() {
//                        @Override
//                        public int compare(SanPham sanPham, SanPham t1) {
//                            return t1.getTensanpham().compareToIgnoreCase(sanPham.getTensanpham());
//                        }
//                    });
//                    adapterGianHang.notifyDataSetChanged();
//
//                } else if (i == R.id.rbGiaTangDan) {
//                    Toast.makeText(getContext(), "Sắp xếp giá tăng dần", Toast.LENGTH_SHORT).show();
//                    Collections.sort(list, new Comparator<SanPham>() {
//                        @Override
//                        public int compare(SanPham sanPham, SanPham t1) {
//                            return Integer.compare(sanPham.getGia(), t1.getGia());
//                        }
//                    });
//                    adapterGianHang.notifyDataSetChanged();
//                } else if (i == R.id.rbGiaGiamDan) {
//                    Toast.makeText(getContext(), "Sắp xếp giá giảm dần", Toast.LENGTH_SHORT).show();
//                    Collections.sort(list, new Comparator<SanPham>() {
//                        @Override
//                        public int compare(SanPham sanPham, SanPham t1) {
//                            return Integer.compare(t1.getGia(), sanPham.getGia());
//                        }
//                    });
//                    adapterGianHang.notifyDataSetChanged();
//                } else if (i == R.id.rdSoluotBan) {
//                    Toast.makeText(getContext(), "Sắp xếp theo số lượt bán giảm dần", Toast.LENGTH_SHORT).show();
//                    Collections.sort(list, new Comparator<SanPham>() {
//                        @Override
//                        public int compare(SanPham sanPham, SanPham t1) {
//                            return Integer.compare(t1.getSoLuotBanRa(), sanPham.getSoLuotBanRa());
//                        }
//                    });
//                    adapterGianHang.notifyDataSetChanged();
//                }
//
//            }
//        }));

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


}
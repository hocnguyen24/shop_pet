package com.example.pet_pet.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pet_pet.Dao.DonHangChiTietDao;
import com.example.pet_pet.Dao.DonHangDao;
import com.example.pet_pet.Dao.GioHangDao;
import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Model.DonHang;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.Model.GioHang;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_don_hang;
import com.example.pet_pet.adapter.adapter_gio_hang;
import com.example.pet_pet.adapter.swipe;
import com.example.pet_pet.databinding.DialogConfilmThanhToanBinding;
import com.example.pet_pet.databinding.FragmentFrgGioHangBinding;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




public class frgGioHang extends Fragment implements adapter_gio_hang.TotalPriceListener {

    private ArrayList<GioHang> list = new ArrayList<>();
    private adapter_gio_hang gioHangAdapter;
    FragmentFrgGioHangBinding binding;
    View gView;

    GioHangDao gioHangDao;

    private DonHangDao donHangDao;

    private adapter_don_hang adapterDonHang;
    private frgQuanLyDonHang frgQuanLyDonHang;
    private ArrayList<DonHang> listDonHang = new ArrayList<>();

    private DonHangChiTietDao chiTietDao;
    private ArrayList<SanPham> sanPhams = new ArrayList<>();
    private SanPhamDao sanPhamDao;



    public frgGioHang() {
    }

    private void displayCart(ArrayList<GioHang> cartList) {
        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
            //
        if (gioHangAdapter == null) {
            gioHangAdapter = new adapter_gio_hang(getContext(), cartList);
            rcv.setAdapter(gioHangAdapter);
        } else {
            gioHangAdapter.updateCartList(cartList);
            gioHangAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgGioHangBinding.inflate(inflater, container, false);
        gView = binding.getRoot();

        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        gioHangAdapter = new adapter_gio_hang(getContext(), list);
        rcv.setAdapter(gioHangAdapter);
        gioHangDao = new GioHangDao(getActivity());
        ItemTouchHelper sw = new ItemTouchHelper(new swipe(gioHangAdapter));
        sw.attachToRecyclerView(rcv);
        gioHangAdapter.setTotalPriceListener(this);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("mataikhoan", 0);
        chiTietDao = new DonHangChiTietDao(getContext());
        donHangDao = new DonHangDao(getContext());

        sanPhamDao = new SanPhamDao(getContext());

        list = gioHangDao.getDanhSachGioHangByMaNguoiDung(mand);
        displayCart(list);

        binding.btnThanhToan.setOnClickListener(view -> {
            showDialogThanhToan();

        });


        return gView;
    }




    @Override
    public void onTotalPriceUpdated(int totalAmount) {
        if (binding != null && binding.txtTongTienThanhToan != null) {
            binding.txtTongTienThanhToan.setText(String.valueOf(totalAmount));
        }
    }

    //Code phương thức thanh toán
    private void showDialogThanhToan() {
        //Mở dialog xác nhận
        LayoutInflater layoutInflater = getLayoutInflater();
        DialogConfilmThanhToanBinding thanhToanBinding = DialogConfilmThanhToanBinding.inflate(layoutInflater);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(thanhToanBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();

        //Thực hiện chức năng thanh toán ấn nút
        thanhToanBinding.btnThanhToan.setOnClickListener(view -> {

            //kiểm tra số lượng cuả từng sản phẩm được chọn khi click thanh toán
            for (GioHang gioHang : list) {
                if (gioHang.getSoLuong() == 0) {
                    Toast.makeText(getContext(), "Sản phẩm " + gioHang.getTenSanPham() + " đã hết hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // Ép kiểu tổng tiền thành kiểu Integer
            int totalAmount = Integer.parseInt(binding.txtTongTienThanhToan.getText().toString());
            // lấy ra thông tin người dùng đã được lưu trong sharedPreferences
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
            //Lấy ra mã người dùng
            int mand = sharedPreferences.getInt("mataikhoan", 0);
            //Lấy ra số tiền hiện có trong tài khoản của người dùng
            int tienHienCo = sharedPreferences.getInt("sotien", 0);

            //Lấy ra ngày tháng năm hiện tại
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngayHienTai = currentDate.format(formatter);

            //Kiểm tra số tiền hiện có trong tài khoản của người dùng có đủ để thanh toán hay không
            if (tienHienCo >= totalAmount) {

                //nếu tiền trong tài khoản đủ thì sẽ thực hiện trừ tiền
                int soTienConLai = tienHienCo - totalAmount;
                NguoiDungDao nguoiDungDao = new NguoiDungDao(getContext());

                //Update lại tiền trong tài khoản của người dùng
                if (nguoiDungDao.updateSoTien(mand, soTienConLai)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // put lại số tiền còn lại lên SharedPreferences
                    editor.putInt("sotien", soTienConLai);
                    editor.apply();
                    // khởi tạo đơn hàng mới khi đã lấy ra được thông tin cần thiết
                    // mặc định trạng thái khi thanh toán thành công là chờ phê duyệt
                    DonHang donHang = new DonHang(mand, ngayHienTai, totalAmount, "Chờ phê duyệt");
                    //insert đơn hàng vào database
                    int orderId = donHangDao.insertDonHang(donHang);
                    if (orderId != 0) {
                        listDonHang.clear();
                        listDonHang.addAll(donHangDao.getDsDonHang());

                        //Kiểm tra xem đã có item nào được chọn hay chưa
                        boolean ktraItemDuocChon = false;
                        for (GioHang gioHang : list) {
                            if (gioHang.isSelected()) {
                                ktraItemDuocChon = true;
                                break;
                            }
                        }
                        //nếu đã có item được chọn để thanh toán thì thực hiện tiếp công việc
                        if (ktraItemDuocChon) {
                            // duyệt qua list gioHang và kiểm tra xem những item nào được chọn
                            for (GioHang gioHang : list) {
                                if (gioHang.isSelected()) {
                                    // lấy ra những sản phẩm được chọn để thanh toán
                                    SanPhamDao sanPhamDao = new SanPhamDao(getContext());
                                    SanPham sanPham = sanPhamDao.getSanPhamById(gioHang.getMaSanPham());
                                    if (sanPham != null) {
                                        //thực hiện tạo chi tiết đơn hàng bằng những item giỏ hàng đã được chọn
                                        DonHangChiTiet chiTietDonHan = new DonHangChiTiet(orderId, gioHang.getMaSanPham(), gioHang.getSoLuongMua(), sanPham.getGia(), gioHang.getSoLuongMua() * sanPham.getGia());
                                        chiTietDao.insertDonHangChiTiet(chiTietDonHan);
                                    } else {
                                        Toast.makeText(getContext(), "Sản phẩm không tìm thấy trong cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Vui lòng chọn sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                            return;
                        }



                        // Cập nhật số lượng sản phẩm sau khi thanh toán thành công
                        for (GioHang gioHang : list) {
                            int newQuantity = gioHang.getSoLuong() - gioHang.getSoLuongMua();
                            int newSoLuongBanRa = gioHang.getSoluongbanra() + gioHang.getSoLuongMua();
                            if (newQuantity < 0) {
                                Toast.makeText(getContext(), "Sản phẩm " + gioHang.getTenSanPham() + " không đủ số lượng trong kho", Toast.LENGTH_SHORT).show();
                                return;
                            }
                                sanPhamDao.updateSlSanPham(gioHang.getMaSanPham(), newQuantity,newSoLuongBanRa);

                        }
                        //khi thanh toán thành công thì xóa những item đc chọn
                        for (GioHang selected : list) {
                            if (selected.isSelected()) {
                                gioHangDao.deleteGioHang(selected);
                            }
                        }

                        binding.txtTongTienThanhToan.setText(String.valueOf(0));
                        list = gioHangDao.getDSGioHang();
                        gioHangAdapter.updateCartList(list);
                        displayCart(list);

                        Snackbar.make(getView(), "Thanh toán thành công", Snackbar.LENGTH_SHORT).show();

                        //Sau khi thanh toán thành công
                        // Set mã đơn hàng vừa tạo ở trên và chuyển qua màn hình frgConfilmThanhToan
                        // để hiển thị những đơn hàng chi tiết mà người dùng vừa tạo
                        Bundle bundle = new Bundle();
                        bundle.putInt("maDonHang", orderId);

                        frgConfilmThanhToan frgConfilmThanhToan = new frgConfilmThanhToan();
                        frgConfilmThanhToan.setArguments(bundle);
                        FragmentManager fragmentManager = getParentFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.frameLayoutMain, frgConfilmThanhToan);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thất bại khi thêm đơn hàng!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Thất bại khi cập nhật tài khoản!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Số tiền trong tài khoản không đủ!", Toast.LENGTH_SHORT).show();
            }
        });
        thanhToanBinding.btnThoat.setOnClickListener(view -> dialog.dismiss());

    }
}

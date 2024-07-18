package com.example.pet_pet.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.R;
import com.example.pet_pet.databinding.DialogSuasanphamBinding;
import com.example.pet_pet.databinding.ItemSanphamBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class adapter_san_pham extends RecyclerView.Adapter<adapter_san_pham.viewH> {
    private ArrayList<SanPham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    SanPhamDao dao;

    public adapter_san_pham(ArrayList<SanPham> list, Context context, ArrayList<HashMap<String, Object>> listHM) {
        this.list = list;
        this.context = context;
        this.listHM = listHM;
        dao = new SanPhamDao(context);
    }

    @NonNull
    @Override
    public viewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamBinding txt = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewH(txt);
    }

    @Override
    public void onBindViewHolder(@NonNull viewH holder, int position) {
        holder.txt.txtmaSanPham.setText("MÃ sản phẩm: " + String.valueOf(list.get(position).getMasanpham()));
        holder.txt.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTensanpham());
        holder.txt.txtgiaSanPham.setText("Giá sản phẩm: " + String.valueOf(list.get(position).getGia()));
        holder.txt.txtmaLoaiSanPham2.setText("MÃ loại sản phẩm: " + String.valueOf(list.get(position).getMaloaisanpham()));
        holder.txt.txttenLoaiSanPham2.setText("Tên loại sản phẩm: " + list.get(position).getTenloaisanpham());
        holder.txt.txtmoTa.setText("Mô tả: "+list.get(position).getMota());
        holder.txt.txtsoluong.setText("Số lượng: "+String.valueOf(list.get(position).getSoluong()));
        holder.txt.txtSoLuongBanRa.setText("Số lượng đã bán: " + list.get(position).getSoLuotBanRa());
        if (list.get(position).getSoluong() == 0){
            holder.txt.txttrangThaiSanPham.setVisibility(View.GONE);
            holder.txt.txttrangThaiSanPham1.setVisibility(View.VISIBLE);

        }else {
            holder.txt.txttrangThaiSanPham.setVisibility(View.VISIBLE);
            holder.txt.txttrangThaiSanPham1.setVisibility(View.GONE);
        }
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.txt.imgItemAnhSanPham);
        SanPham sp = list.get(position);
        holder.txt.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm có " + holder.txt.txtTenSanPham.getText() + " không?");

                // Tạo RelativeLayout để chứa nút "Không" và "Đồng ý"
                RelativeLayout layout = new RelativeLayout(context);

                // Tạo nút "Không"
                Button btnCancel = new Button(context);
                RelativeLayout.LayoutParams paramsCancel = new RelativeLayout.LayoutParams(
                        190, // Độ dài
                        75  // Chiều cao
                );
                paramsCancel.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                paramsCancel.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsCancel.setMargins(20, 10, 0, 10);
                btnCancel.setLayoutParams(paramsCancel);
                btnCancel.setText("Không");
                btnCancel.setBackgroundResource(R.drawable.khung_button);
                btnCancel.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                btnCancel.setAllCaps(false);

                // Tạo nút "Đồng ý"
                Button btnAdd = new Button(context);
                RelativeLayout.LayoutParams paramsAdd = new RelativeLayout.LayoutParams(
                        190, // Độ dài
                        75  // Chiều cao
                );
                paramsAdd.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
                paramsAdd.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsAdd.setMargins(0, 10, 20, 10);
                btnAdd.setLayoutParams(paramsAdd);
                btnAdd.setText("Đồng ý");
                btnAdd.setBackgroundResource(R.drawable.khung_button);
                btnAdd.setTextColor(ContextCompat.getColor(context, R.color.xanh_doan));
                btnAdd.setAllCaps(false);

                // Thêm nút "Không" và "Đồng ý" vào RelativeLayout
                layout.addView(btnCancel);
                layout.addView(btnAdd);

                // Thiết lập RelativeLayout làm nội dung cho AlertDialog
                builder.setView(layout);

                // Thiết lập background cho AlertDialog
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);

                // Hiển thị AlertDialog
                dialog.show();

                // Sử dụng biến dialog trong phương thức onClick của nút "Không"
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Đóng AlertDialog khi nút "Không" được nhấn
                        dialog.dismiss();
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Xử lý khi nút "Đồng ý" được nhấn
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getMasanpham());
                        switch (check) {
                            case 1:
                                list.clear();
                                list.addAll(dao.getsanphamall());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sản phẩm này vì đang còn tồn tại trong chi tiết hóa đơn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        // Đóng AlertDialog sau khi xử lý
                        dialog.dismiss();
                    }
                });
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
                DialogSuasanphamBinding suaspbiding = DialogSuasanphamBinding.inflate(layoutInflater);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(suaspbiding.getRoot());
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
                dialog.show();
                suaspbiding.edtenSanPham.setText(sp.getTensanpham());
                suaspbiding.edgiaSanPham.setText(String.valueOf(sp.getGia()));
                suaspbiding.edmoTa.setText(sp.getMota());
                suaspbiding.edUpAnhSanPham.setText(sp.getAnhSanPham());
                suaspbiding.edsoluong.setText(String.valueOf(sp.getSoluong()));
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        context,
                        listHM,
                        (android.R.layout.simple_list_item_1),
                        new String[]{"maloaisanpham"},
                        new int[]{android.R.id.text1}
                );
                suaspbiding.spnmaLoaiSanPham.setAdapter(simpleAdapter);
                int index = 0;
                int position = -1;

                for(HashMap<String, Object> item : listHM){
                    if((int) item.get("maloaisanpham") == sp.getMaloaisanpham()){
                        position = index;
                    }
                    index ++;
                }
                suaspbiding.spnmaLoaiSanPham.setSelection(position);
                suaspbiding.btnsuasp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tensanpham = suaspbiding.edtenSanPham.getText().toString();
                        String gia = suaspbiding.edgiaSanPham.getText().toString();
                        String mota = suaspbiding.edmoTa.getText().toString();
                        String anhsanpham = suaspbiding.edUpAnhSanPham.getText().toString();
                        String soluong=suaspbiding.edsoluong.getText().toString();
                        HashMap<String, Object> hs = (HashMap<String, Object>) suaspbiding.spnmaLoaiSanPham.getSelectedItem();
                        int maloaisp = (int) hs.get("maloaisanpham");

                        if (tensanpham.isEmpty() || gia.isEmpty()||mota.isEmpty()||anhsanpham.isEmpty()||soluong.isEmpty()) {
                            if (tensanpham.equals("")) {
                                suaspbiding.edtenSanPham.setError("Vui lòng không để trống tên sản phẩm");
                            } else {
                                suaspbiding.edtenSanPham.setError(null);
                            }
                            if (gia.equals("")) {
                                suaspbiding.edgiaSanPham.setError("Vui lòng không để trống giá sản phẩm");
                            } else {
                                suaspbiding.edgiaSanPham.setError(null);
                            }
                            if (mota.equals("")) {
                                suaspbiding.edmoTa.setError("Vui lòng không để trống giá sản phẩm");
                            } else {
                                suaspbiding.edmoTa.setError(null);
                            }
                            if (anhsanpham.equals("")) {
                                suaspbiding.edUpAnhSanPham.setError("Vui lòng không để trống giá sản phẩm");
                            } else {
                                suaspbiding.edUpAnhSanPham.setError(null);
                            }if (anhsanpham.equals("")) {
                                suaspbiding.edUpAnhSanPham.setError("Vui lòng không để trống tên sản phẩm");
                            } else {
                                suaspbiding.edUpAnhSanPham.setError(null);
                            }
                            if (soluong.equals("")) {
                                suaspbiding.edsoluong.setError("Vui lòng không để trống tên sản phẩm");
                            } else {
                                suaspbiding.edsoluong.setError(null);
                            }
                        } else {
                            try {
                                int tien = Integer.parseInt(gia);
                                int soluongcheck=Integer.parseInt(soluong);
                                if (tien <= 0||soluongcheck<0) {
                                    suaspbiding.edgiaSanPham.setError("Giá sản phẩm phải lớn hơn 0");
                                    suaspbiding.edsoluong.setError("số lượng lớn hơn 0");
                                } else {
                                    suaspbiding.edgiaSanPham.setError(null);
                                    boolean check = dao.update(sp.getMasanpham(), tensanpham, tien, maloaisp,mota,anhsanpham,soluongcheck);
                                    if (check) {
                                        list.clear();
                                        list = dao.getsanphamall();
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Cập nhật thành công sách", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(context, "Cập nhật không thành công sách", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (NumberFormatException e) {
                                suaspbiding.edgiaSanPham.setError("Giá sản phẩm phải là số");
                                suaspbiding.edsoluong.setError("Số lượng sản phẩm phải là số");
                            }
                        }
                    }
                });
                suaspbiding.btnhuySuasp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewH extends RecyclerView.ViewHolder {
        //TextView txtma_san_pham,txtten_san_pham
        ItemSanphamBinding txt;

        public viewH(@NonNull ItemSanphamBinding txt) {
            super(txt.getRoot());
            this.txt = txt;
        }
    }
}

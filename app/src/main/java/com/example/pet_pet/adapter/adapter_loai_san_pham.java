package com.example.pet_pet.adapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.LoaiSanPhamDao;
import com.example.pet_pet.Model.LoaiSanPham;
import com.example.pet_pet.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;



public class adapter_loai_san_pham extends RecyclerView.Adapter<adapter_loai_san_pham.viewhl> {
    private final Context context;
    private final ArrayList<LoaiSanPham> list;
    LoaiSanPhamDao dao;

    public adapter_loai_san_pham(ArrayList<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new LoaiSanPhamDao(context);
    }

    @NonNull
    @Override
    public viewhl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanpham, parent, false);
        return new viewhl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhl holder, int position) {
        holder.txtmaloaisanpham.setText("Mã loại sản phẩm: " + String.valueOf(list.get(position).getMaloaisp()));
        holder.txttenloaisanpham.setText("Tên loại sản phẩm: " + list.get(position).getTenloaisp());
        LoaiSanPham lsp = list.get(position);
        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa loại sản phẩm có " + holder.txttenloaisanpham.getText() + " không?");

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

                        int check = dao.delete(list.get(holder.getAdapterPosition()).getMaloaisp());
                        switch (check) {
                            case 1:
                                list.clear();
                                list.addAll(dao.getalltheloai());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công loại sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công loại sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được loai sản phẩm này vì đang còn sản phẩm trong loại", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
//                        // Đóng AlertDialog sau khi xử lý
                        dialog.dismiss();


//                        if (dao.delete(lsp.getMaloaisp())) {
//                            list.clear();
//                            list.addAll(dao.getalltheloai());
//                            notifyDataSetChanged();
//                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                        }
                        // Đóng AlertDialog sau khi xử lý
//                        dialog.dismiss();
                    }
                });
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogsualsp(lsp);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewhl extends RecyclerView.ViewHolder {
        TextView txtmaloaisanpham, txttenloaisanpham;
        ImageButton btnxoa;

        public viewhl(@NonNull View itemView) {
            super(itemView);
            txtmaloaisanpham = itemView.findViewById(R.id.txtma_loai_san_pham);
            txttenloaisanpham = itemView.findViewById(R.id.txtten_loai_san_pham);
            btnxoa = itemView.findViewById(R.id.btnxoa);
        }
    }

    public void dialogsualsp(LoaiSanPham lsp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sualoaisanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        // Thiết lập background cho AlertDialog
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
        dialog.show();
        TextInputEditText edtenlsp = view.findViewById(R.id.edten_loai_san_pham_sua);
        TextView edmalsp = view.findViewById(R.id.txtma_loai_san_pham_sua);
        Button btnhuy = view.findViewById(R.id.btnhuy_sualsp);
        Button btnsua = view.findViewById(R.id.btnsualsp);
        edtenlsp.setText(lsp.getTenloaisp());
        edmalsp.setText("Mã loại sản phẩm: " + String.valueOf(lsp.getMaloaisp()));
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsp.setTenloaisp(edtenlsp.getText().toString());
                String tenlsp = edtenlsp.getText().toString();
                if (tenlsp.isEmpty()) {
                    if (tenlsp.equals("")) {
                        edtenlsp.setError("Vui lòng nhập tên loại sản phẩm");
                    } else {
                        edtenlsp.setError(null);
                    }
                } else {
                    if (dao.update(lsp)) {
                        list.clear();
                        list.addAll(dao.getalltheloai());
//                        list=dao.getalltheloai();
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}

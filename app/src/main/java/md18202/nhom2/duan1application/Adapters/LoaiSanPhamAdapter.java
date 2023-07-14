package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.LoaiSanPhamDAO;
import md18202.nhom2.duan1application.Models.LoaiSanPham;
import md18202.nhom2.duan1application.R;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.MyViewHover> {
    private Context context;
    private ArrayList<LoaiSanPham>list;
private LoaiSanPhamDAO loaiSanPhamDAO;
    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_san_pham, parent, false);

        return new MyViewHover(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHover holder, int position) {
        holder.tvSanpham.setText("Loại Sản Phẩm: "+list.get(position).getTenLoai());
        holder.tvidSanPham.setText(String.valueOf(list.get(position).getLoaiSanPham_id()));
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

   public class MyViewHover extends RecyclerView.ViewHolder {
       private TextView tvSanpham , tvidSanPham;
        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            tvSanpham  = itemView.findViewById(R.id.tvLoaiSanPham);
            tvidSanPham = itemView.findViewById(R.id.tvid_LoaiSanPham);

            // sửa loại sản phẩm
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder  = new AlertDialog.Builder(context);
                    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_sua_loai_sanpham, null);
                    EditText edTen= view.findViewById(R.id.edTenLoaiSp);
                    LoaiSanPham loaiSanPham = list.get(getLayoutPosition());
                    edTen.setText(loaiSanPham.getTenLoai());
                    loaiSanPhamDAO  =new LoaiSanPhamDAO(context);
                    builder.setView(view);
                    builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String ten = edTen.getText().toString().trim();
                            loaiSanPham.setTenLoai(ten);

                            if (ten.equals("")) {
                                Toast.makeText(context, "Không Được Bỏ Chống", Toast.LENGTH_SHORT).show();
                            }
                           else if  (loaiSanPhamDAO.SuaLoaiSP(loaiSanPham)>0){
                                list.clear();
                                list  =loaiSanPhamDAO.getDsLoaiSanPham();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Dialog dialog  =builder.create();
                    dialog.show();
                }
            });
        }
    }
}

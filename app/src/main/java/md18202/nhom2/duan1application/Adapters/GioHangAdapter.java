package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import md18202.nhom2.duan1application.Activities.DiaChiNhanHangActivity;
import md18202.nhom2.duan1application.Activities.GioHangActivity;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    List<GioHang> list;
    List<GioHang> listMuaHang;
    GioHangActivity context;
    GioHangDAO gioHangDAO;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    int itemPrice;
    public GioHangAdapter(List<GioHang> list, GioHangActivity context) {
        this.list = list;
        this.context = context;
        gioHangDAO = new GioHangDAO(context);
        sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_gio_hang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        GioHang gioHang = list.get(position);
        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgAnh_san_pham);
        holder.tvTen_san_pham.setText(list.get(position).getTenSanPham());
        holder.tvSo_luong_mua.setText(""+list.get(position).getSoLuong());
        holder.tvGia_san_pham.setText(""+list.get(position).getGiaSanPham());
        if(list.get(holder.getAdapterPosition()).getTrangThaiMua() == 0){
            holder.ckbMua_hang.setChecked(false);
        }else{
            holder.ckbMua_hang.setChecked(true);
        }
        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSanPhamKhoiGioHang(holder.getAdapterPosition(), holder.tvSo_luong_mua);
            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())+1));
                if(gioHang.getTrangThaiMua()==1){
                    context.tvTotal.setText(""+(tongTien(listMuaHang)+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()))*gioHang.getGiaSanPham()));
                }
            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvSo_luong_mua.getText().toString().matches("1")){
                    return;
                }
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())-1));
                if(gioHang.getTrangThaiMua()==1){
                    context.tvTotal.setText(""+(tongTien(listMuaHang)+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()))*gioHang.getGiaSanPham()));
                }
            }
        });

        holder.ckbMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ckbMua_hang.isChecked()){
                    gioHang.setTrangThaiMua(1);
                    listMuaHang = gioHangDAO.getDsMuaHang(nguoiDung_id, 1);
                    itemPrice = (tongTien(listMuaHang)+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()))*gioHang.getGiaSanPham());
                    context.tvTotal.setText(""+itemPrice);
                }else{
                    gioHang.setTrangThaiMua(0);
                    listMuaHang = gioHangDAO.getDsMuaHang(nguoiDung_id, 1);
                    context.tvTotal.setText(""+(tongTien(listMuaHang)));
                }

//                if(gioHang.getTrangThaiMua()==1){
//                    listMuaHang = gioHangDAO.getDsMuaHang(nguoiDung_id, 1);
//                    itemPrice = (tongTien(listMuaHang)+(Integer.parseInt(tvSoLuong.getText().toString()))*gioHang.getGiaSanPham());
//                    context.tvTotal.setText(""+itemPrice);
//                }else{
//                    listMuaHang = gioHangDAO.getDsMuaHang(nguoiDung_id, 1);
//                    context.tvTotal.setText(""+(tongTien(listMuaHang)));
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSo_luong_mua, tvTen_san_pham, tvGia_san_pham;
        ImageView imgAnh_san_pham, imgMinus, imgPlus, imgCancel;
        CheckBox ckbMua_hang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSo_luong_mua = itemView.findViewById(R.id.tvSo_luong_mua);
            tvTen_san_pham = itemView.findViewById(R.id.tvTen_san_pham);
            tvGia_san_pham = itemView.findViewById(R.id.tvGia_san_pham);
            imgAnh_san_pham = itemView.findViewById(R.id.imgAnh_san_pham);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            ckbMua_hang = itemView.findViewById(R.id.ckbMua_hang);
        }
    }

    public void xoaSanPhamKhoiGioHang(int viTri, TextView tvSoluong){
        if(gioHangDAO.xoaKhoiGioHang(list.get(viTri).getSanPham_id(), list.get(viTri).getNguoiDung_id()) > 0){
            Toast.makeText(context, "Da xoa khoi gio hang", Toast.LENGTH_SHORT).show();

            list.remove(viTri);
            notifyDataSetChanged();
            tongTien(listMuaHang);
        }else{
            Toast.makeText(context, "Chua xoa khoi gio hang", Toast.LENGTH_SHORT).show();
        }
    }

    public int tongTien(List<GioHang> listGioHang){
        int total = 0;
        for(GioHang gioHang: listGioHang){
            total += gioHang.getGiaSanPham();
        }
        return total;
    }

    public void muaHang(TextView tvSoluong){
        context.btnMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMuaHang = gioHangDAO.getDsMuaHang(nguoiDung_id, 1);
                if(listMuaHang.size() == 0){
                    return;
                }
                Intent intent = new Intent(context, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tongTien", tongTien(listMuaHang));
                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

}

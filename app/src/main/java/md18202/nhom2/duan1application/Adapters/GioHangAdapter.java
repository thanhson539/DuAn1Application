package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import md18202.nhom2.duan1application.Activities.GioHangActivity;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    List<GioHang> list;
    GioHangActivity context;
    GioHangDAO gioHangDAO;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
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
        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgAnh_san_pham);
        holder.tvTen_san_pham.setText(list.get(position).getTenSanPham());
        holder.tvSo_luong_mua.setText(""+list.get(position).getSoLuong());
        holder.tvGia_san_pham.setText(""+list.get(position).getGiaSanPham());
        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSanPhamKhoiGioHang(holder.getAdapterPosition(), holder.tvGia_san_pham);
            }
        });
        suaSoLuong(holder.getAdapterPosition(), holder.imgPlus, holder.imgMinus, holder.imgCancel_so_luong, holder.tvSo_luong_mua, holder.tvGia_san_pham);
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSo_luong_mua, tvTen_san_pham, tvGia_san_pham;
        ImageView imgAnh_san_pham, imgMinus, imgPlus, imgCancel, imgCancel_so_luong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSo_luong_mua = itemView.findViewById(R.id.tvSo_luong_mua);
            tvTen_san_pham = itemView.findViewById(R.id.tvTen_san_pham);
            tvGia_san_pham = itemView.findViewById(R.id.tvGia_san_pham);
            imgAnh_san_pham = itemView.findViewById(R.id.imgAnh_san_pham);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            imgCancel_so_luong = itemView.findViewById(R.id.imgCancel_so_luong);
        }
    }

    public void xoaSanPhamKhoiGioHang(int viTri, TextView tvGia){
        if(gioHangDAO.xoaKhoiGioHang(list.get(viTri).getGioHang_id()) > 0){
            Toast.makeText(context, "Da xoa khoi gio hang", Toast.LENGTH_SHORT).show();
            getDsGioHang();
        }else{
            Toast.makeText(context, "Chua xoa khoi gio hang", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDsGioHang(){
        list.clear();
        list = gioHangDAO.getDsGioHang(nguoiDung_id);
        tongTien(list);
        notifyDataSetChanged();
    }

    public void getSoLuong(int viTri, TextView tvGia){
        list.clear();
        sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        list = gioHangDAO.getDsGioHang(nguoiDung_id);
        tongTien(list);
        GioHang gioHang = list.get(viTri);
        tvGia.setText(""+gioHang.getGiaSanPham());
        notifyDataSetChanged();
    }

    public void tongTien(List<GioHang> listGioHang){
        int total = 0;
        for(GioHang gioHang: listGioHang){
            total += gioHang.getGiaSanPham();
        }
        context.tvTotal.setText(""+total);
    }

    public void suaSoLuong(int viTri, ImageView imgThem, ImageView imgGiam, ImageView imgJust1, TextView tvSoLuong, TextView tvGiaSanPham){
        GioHang gioHang = list.get(viTri);
        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSoLuong(gioHang.getSoLuong()+1);
                if(gioHangDAO.suaSoLuong(gioHang) > 0){
                    tvSoLuong.setText(""+gioHang.getSoLuong());
                    getSoLuong(viTri, tvGiaSanPham);
                }
            }
        });
        imgGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSoLuong(gioHang.getSoLuong()-1);
                if(gioHangDAO.suaSoLuong(gioHang) > 0){
                    tvSoLuong.setText(""+gioHang.getSoLuong());
                    getSoLuong(viTri, tvGiaSanPham);
                }
            }
        });
        imgJust1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSoLuong(1);
                if(gioHangDAO.suaSoLuong(gioHang) > 0){
                    tvSoLuong.setText(""+gioHang.getSoLuong());
                    getSoLuong(viTri, tvGiaSanPham);
                }
            }
        });
    }

}

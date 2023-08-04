package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import md18202.nhom2.duan1application.Activities.ChiTietSanPhamActivity;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.DAO.SanPhamYeuThichDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.Models.SanPhamYeuThich;
import md18202.nhom2.duan1application.R;

public class SanPhamAdapter1 extends RecyclerView.Adapter<SanPhamAdapter1.myViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;

    public SanPhamAdapter1(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        sharedPreferences= context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_san_pham1, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        SanPham sanPham = list.get(position);

        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgSanPham_item);

        holder.tvTenSanPham_item.setText(list.get(position).getTenSanPham());
        holder.tvGiaSanPham_item.setText(String.valueOf(list.get(position).getGiaSanPham()) + " vnđ");

        if (nguoiDung_id == 1) {
            holder.imgYeuThich_item.setVisibility(View.GONE);
        }
        if (validate(list.get(holder.getAdapterPosition()).getSanPham_id()) < 0) {
            holder.imgYeuThich_item.setImageResource(R.drawable.frame4_trai_tim);
        }

        //Sự kiện yêu thích cho sản phẩm
        ImageView imgYeuThich_item = holder.imgYeuThich_item;
        setImgYeuThich(holder.getAdapterPosition(), holder.imgYeuThich_item);

        //Xem chi tiết sản phẩm
        xemChiTiet(holder.tvChiTiet_item, list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham_item, imgYeuThich_item;
        TextView tvTenSanPham_item, tvGiaSanPham_item, tvChiTiet_item;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham_item = itemView.findViewById(R.id.imgSanPham_item);
            imgYeuThich_item = itemView.findViewById(R.id.imgYeuThich_item);
            tvTenSanPham_item = itemView.findViewById(R.id.tvTenSanPham_item);
            tvGiaSanPham_item = itemView.findViewById(R.id.tvGiaSanPham_item);
            tvChiTiet_item = itemView.findViewById(R.id.tvChiTiet_item);
        }
    }

    public void setImgYeuThich(int position, ImageView imgYeuThich) {
        SanPham sanPham = list.get(position);
//        if (sanPham.getIsYeuThich() == 1) {
//            imgYeuThich.setImageResource(R.drawable.frame4_trai_tim);
//        }
        imgYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imgYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
                //code chuc năng cập nhật isYeuThich
                SanPhamYeuThichDAO sanPhamYeuThichDAO = new SanPhamYeuThichDAO(context);
                if (sanPhamYeuThichDAO.boYeuThichSanPham(sanPham.getSanPham_id(), nguoiDung_id) > 0) {
                    notifyDataSetChanged();
                    list.clear();
                    list = sanPhamYeuThichDAO.getSanPhamYeuThich(nguoiDung_id);
                }
            }
        });
    }

    public void xemChiTiet(TextView textView, SanPham sanPham) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sanPham", sanPham);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public int validate(int sanPham_id){
        int check = 1;
        SanPhamYeuThichDAO spytd = new SanPhamYeuThichDAO(context);
        ArrayList<SanPham> list1 = spytd.getSanPhamYeuThich(nguoiDung_id);
        for (SanPham sp: list1){
            if(sp.getSanPham_id() == sanPham_id){
                check = -1;
            }
        }
        return check;
    }
}

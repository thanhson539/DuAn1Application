package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class SanPhamAdapter1 extends RecyclerView.Adapter<SanPhamAdapter1.myViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public SanPhamAdapter1(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
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
        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgSanPham_item);

        holder.tvTenSanPham_item.setText(list.get(position).getTenSanPham());
        holder.tvGiaSanPham_item.setText(String.valueOf(list.get(position).getGiaSanPham()) + " vnđ");

        if (list.get(position).getIsYeuThich() == 1) {
            holder.imgYeuThich_item.setImageResource(R.drawable.frame4_trai_tim);
        }

        //Sự kiện yêu thích cho sản phẩm
        int sanPham_id = list.get(position).getSanPham_id();
        int isYeuThich = list.get(position).getIsYeuThich();
        ImageView imgYeuThich_item = holder.imgYeuThich_item;
        setImgYeuThich(sanPham_id, isYeuThich, imgYeuThich_item);

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

    public void setImgYeuThich(int sanPham_id, int isYeuThich, ImageView imgYeuThich) {
        int isYeuThichDefault = 0;
        if (isYeuThich == 1) {
            imgYeuThich.setImageResource(R.drawable.frame4_trai_tim);
        }
        imgYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
                //code chuc năng cập nhật isYeuThich
                SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
                boolean check = sanPhamDAO.changeIsYeuThich(sanPham_id, 0);
                if (check) {
                    Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    list.clear();
                    list = sanPhamDAO.getDsSanPhamYeuThich();
                } else {
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
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
}

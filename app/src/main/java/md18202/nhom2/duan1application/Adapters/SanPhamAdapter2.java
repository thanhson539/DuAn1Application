package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Activities.ChiTietSanPhamActivity;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.GioHang;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class SanPhamAdapter2 extends RecyclerView.Adapter<SanPhamAdapter2.MyViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;


    public SanPhamAdapter2(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_san_pham2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String srcImg = list.get(position).getAnhSanPham();

        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
        boolean isUri = srcImg.startsWith("content://");

        if (isUri) {
            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
            Picasso.get().load(Uri.parse(srcImg)).into(holder.imgSanPham_itemGrid);
        } else {
            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
            int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
            holder.imgSanPham_itemGrid.setImageResource(resourceId);
        }

        holder.tvGiaSanPham_itemGrid.setText(String.valueOf(list.get(position).getGiaSanPham()) + " vnd");
        holder.imgGioHang_itemGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Chưa code chức năng mua hàng", Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sanPham",sanPham);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.imgGioHang_itemGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonMua(list.get(holder.getAdapterPosition()).getSanPham_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham_itemGrid, imgGioHang_itemGrid;
        TextView tvGiaSanPham_itemGrid;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham_itemGrid = itemView.findViewById(R.id.imgSanPham_itemGrid);
            imgGioHang_itemGrid = itemView.findViewById(R.id.imgGioHang_itemGrid);
            tvGiaSanPham_itemGrid = itemView.findViewById(R.id.tvGiaSanPham_itemGrid);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public void chonMua(int sanPham_id){
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
        if(sanPham.getSoLuongConLai() == 0){
            Toast.makeText(context, "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            return;
        }
        GioHangDAO gioHangDAO = new GioHangDAO(context);
        GioHang gioHang = new GioHang();
        SharedPreferences sharedPreferences = context.getSharedPreferences("NGUOIDUNG",context.MODE_PRIVATE);
        int getNguoiDung_id = sharedPreferences.getInt("nguoiDung_id", 0);
        gioHang.setNguoiDung_id(getNguoiDung_id);
        gioHang.setSanPham_id(sanPham_id);
        gioHang.setSoLuong(1);
        gioHang.setTrangThaiMua(0);
        if(gioHangDAO.themVaoGioHang(gioHang) > 0){
            Toast.makeText(context, "Da them vao gio hang", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Mặt hàng này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }
}

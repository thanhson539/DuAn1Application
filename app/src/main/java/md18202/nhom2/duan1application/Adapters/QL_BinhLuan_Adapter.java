package md18202.nhom2.duan1application.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.R;

public class QL_BinhLuan_Adapter extends RecyclerView.Adapter<QL_BinhLuan_Adapter.MyViewHover> {
    Context context;
    ArrayList<BinhLuan> list;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public interface OnitemClick{
        void onItemClickDelete(int position);
    }

    private OnitemClick onItemClickListener;

    public QL_BinhLuan_Adapter(Context context, ArrayList<BinhLuan> list, OnitemClick onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener= onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quanly_binhluan, parent, false);
        return new MyViewHover(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHover holder, @SuppressLint("RecyclerView") int position) {
        String srcImg = list.get(position).getAnhSanPham();

        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
        boolean isUri = srcImg.startsWith("content://");

        if (isUri) {
            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
            Picasso.get().load(Uri.parse(srcImg)).into(holder.imgAvata);
        } else {
            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
            int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
            holder.imgAvata.setImageResource(resourceId);
        }


        holder.tvTenSP.setText("Tên Sản Phẩm: "+list.get(position).getTenSanPham());
      holder.tvThoiGian.setText("Thời Gian: "+list.get(position).getThoiGian());
      holder.tvNoiDung.setText("Nội Dung: "+list.get(position).getNoiDung());
      holder.tvTenND.setText("Người Dùng: "+list.get(position).getTenNguoiDung());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
//             int Positio = position;
              notifyDataSetChanged();
              if (onItemClickListener != null){
                  onItemClickListener.onItemClickDelete(position);
              }else {
                  Toast.makeText(context, "Nó là null", Toast.LENGTH_SHORT).show();
              }
          }
      });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHover extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvTenND, tvNoiDung, tvThoiGian;
        ImageView imgAvata;

        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            tvThoiGian = itemView.findViewById(R.id.tv_ThoiGian_BL);
            tvNoiDung = itemView.findViewById(R.id.tv_NoiDung_BL);
            tvTenND = itemView.findViewById(R.id.tv_TenND_BL);
            tvTenSP = itemView.findViewById(R.id.tv_TenSP_BL);
            imgAvata = itemView.findViewById(R.id.img_Ql_BinhLuan);

        }
    }
}

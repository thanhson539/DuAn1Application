package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.R;

public class QL_BinhLuan_Adapter extends RecyclerView.Adapter<QL_BinhLuan_Adapter.MyViewHover> {
    Context context;
    ArrayList<BinhLuan> list;

    public QL_BinhLuan_Adapter(Context context, ArrayList<BinhLuan> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_quanly_binhluan, parent, false);
        return new MyViewHover(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHover holder, int position) {
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



        holder.tvTenSP.setText(list.get(position).getTenSanPham());
        holder.tvTongBL.setText("Tổng Bình Luận Là:  " + String.valueOf(list.get(position).getTongBinhLuan()) + "\n Xem Chi Tiết");


        holder.tvTongBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(context);
                LayoutInflater inflater  = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_hienthi_tongsp, null);
                builder.setView(view);

                RecyclerView recyclerView  =view.findViewById(R.id.recyc_ChiTiet_BinhLuan);

                BinhLuanDAO binhLuanDAO  = new BinhLuanDAO(context);
                ArrayList<BinhLuan> list1 = binhLuanDAO.getDsBinhLuanTheoSanPham_id(list.get(holder.getLayoutPosition()).getSanPham_id());
                BinhLuan_ChiTiet_Adapter adapter  =new BinhLuan_ChiTiet_Adapter(context,list1);
                recyclerView.setAdapter(adapter);

                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHover extends RecyclerView.ViewHolder {
        TextView  tvTenSP, tvTongBL;
        ImageView imgAvata;
        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            tvTongBL  =itemView.findViewById(R.id.tv_TongBinhLuan_BL);
            tvTenSP  = itemView.findViewById(R.id.tv_TenSP_BL);
            imgAvata  = itemView.findViewById(R.id.img_Ql_BinhLuan);

        }
    }
}

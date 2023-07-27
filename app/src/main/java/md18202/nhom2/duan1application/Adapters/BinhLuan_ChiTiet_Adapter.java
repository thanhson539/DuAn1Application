package md18202.nhom2.duan1application.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class BinhLuan_ChiTiet_Adapter extends RecyclerView.Adapter<BinhLuan_ChiTiet_Adapter.MyView> {
    Context context;
    ArrayList<BinhLuan> list;

    public BinhLuan_ChiTiet_Adapter(Context context, ArrayList<BinhLuan> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_ql_binhluan_2, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
    holder.tvTen.setText("Tên Người Dùng: "+list.get(position).getTenNguoiDung());
    holder.tvTenSP.setText("Tên Sản Phẩm: "+list.get(position).getTenSanPham());
    holder.tvNoiDungBL.setText("Nội Dung: "+list.get(position).getNoiDung());
    holder.tvThoiGian.setText("Thời Gian: "+list.get(position).getThoiGian());

        String srcImg = list.get(position).getAnhSanPham();

        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
        boolean isUri = srcImg.startsWith("content://");

        if (isUri) {
            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
            Picasso.get().load(Uri.parse(srcImg)).into(holder.imgAnhBL);
        } else {
            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
            int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
            holder.imgAnhBL.setImageResource(resourceId);
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView tvTen , tvTenSP, tvNoiDungBL, tvThoiGian;
        ImageView imgAnhBL;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen_CT);
            tvNoiDungBL = itemView.findViewById(R.id.tvNoiDungBL_CT);
            tvTenSP  = itemView.findViewById(R.id.tvTenSP_CT);
            tvThoiGian  = itemView.findViewById(R.id.tvThoiGian_CT);
            imgAnhBL  = itemView.findViewById(R.id.img_ChiTiet_BL);


            // Xoá Bình luận Sản Phẩm
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    AlertDialog.Builder builder  = new AlertDialog.Builder(context);
                    builder.setTitle("Thông Báo !");
                    builder.setMessage("Bạn Có Muốn Xoá Bình Luận Này Không");
                    builder.setNegativeButton("Huỷ" , null).setPositiveButton("Xoá Bình Luận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BinhLuanDAO binhLuanDAO  = new BinhLuanDAO(context);
                            int postion = list.get(getLayoutPosition()).getBinhLuan_id();
                            binhLuanDAO.xoaBinhLuanTheoBinhLuan_id(postion);
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                            Toast.makeText(context, "Xoá Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    Dialog dialog  = builder.create();
                    dialog.show();
                }

            });
        }
    }
}

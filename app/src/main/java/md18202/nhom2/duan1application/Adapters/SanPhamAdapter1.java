package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
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

import md18202.nhom2.duan1application.Model.SanPham;
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
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
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
        holder.tvChiTiet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Code thêm chứu năng xem thông tin chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
                //code here
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

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham_item;
        TextView tvTenSanPham_item, tvGiaSanPham_item, tvChiTiet_item;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham_item = itemView.findViewById(R.id.imgSanPham_item);
            tvTenSanPham_item = itemView.findViewById(R.id.tvTenSanPham_item);
            tvGiaSanPham_item = itemView.findViewById(R.id.tvGiaSanPham_item);
            tvChiTiet_item = itemView.findViewById(R.id.tvChiTiet_item);
        }
    }
}

package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
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

import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.R;

public class QLDonHangAdapter extends RecyclerView.Adapter<QLDonHangAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<HoaDonChiTiet> list;

    public QLDonHangAdapter(Context context, ArrayList<HoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_don_hang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imgAnhSanPham_ql_donhang = list.get(position).getAnhSanPham();
        boolean isUri = imgAnhSanPham_ql_donhang.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgAnhSanPham_ql_donhang)).into(holder.imgAnhSanPham_ql_donhang);
        } else {
            int idResource = context.getResources().getIdentifier(imgAnhSanPham_ql_donhang, "drawable", context.getPackageName());
            holder.imgAnhSanPham_ql_donhang.setImageResource(idResource);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhSanPham_ql_donhang;
        TextView tvMaDonHang_ql_donhang, tvTenSanPham_ql_donhang, tvSoLuongSanPham_ql_donhang,
                tvGiaSanPham_ql_donhang, tvThoiGianDatHang_ql_donhang, tvDiaChiGiaoHang_ql_donhang,
                tvTrangThaiDonHang_ql_donhang, tvDongY_ql_donhang, tvTongTien_ql_donhang;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhSanPham_ql_donhang = itemView.findViewById(R.id.imgAnhSanPham_ql_donhang);
            tvMaDonHang_ql_donhang = itemView.findViewById(R.id.tvMaDonHang_ql_donhang);
            tvTenSanPham_ql_donhang = itemView.findViewById(R.id.tvTenSanPham_ql_donhang);
            tvSoLuongSanPham_ql_donhang = itemView.findViewById(R.id.tvSoLuongSanPham_ql_donhang);
            tvGiaSanPham_ql_donhang = itemView.findViewById(R.id.tvGiaSanPham_ql_donhang);
            tvThoiGianDatHang_ql_donhang = itemView.findViewById(R.id.tvThoiGianDatHang_ql_donhang);
            tvDiaChiGiaoHang_ql_donhang = itemView.findViewById(R.id.tvDiaChiGiaoHang_ql_donhang);
            tvTrangThaiDonHang_ql_donhang = itemView.findViewById(R.id.tvTrangThaiDonHang_ql_donhang);
            tvDongY_ql_donhang = itemView.findViewById(R.id.tvDongY_ql_donhang);
            tvTongTien_ql_donhang = itemView.findViewById(R.id.tvTongTien_ql_donhang);
        }
    }
}

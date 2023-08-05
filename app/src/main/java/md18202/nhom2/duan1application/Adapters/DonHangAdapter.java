package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
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
import java.util.SplittableRandom;

import md18202.nhom2.duan1application.Activities.XacNhanThanhToanActivity;
import md18202.nhom2.duan1application.DAO.GioHangDAO;
import md18202.nhom2.duan1application.DAO.HoaDonChiTietDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<HoaDonChiTiet> list;

    public DonHangAdapter(Context context, ArrayList<HoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_don_hang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Set data cho cac widget
        String imgAnhSanPham_donhang = list.get(position).getAnhSanPham();
        boolean isUri = imgAnhSanPham_donhang.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgAnhSanPham_donhang)).into(holder.imgAnhSanPham_donhang);
        } else {
            int idResource = context.getResources().getIdentifier(imgAnhSanPham_donhang, "drawable", context.getPackageName());
            holder.imgAnhSanPham_donhang.setImageResource(idResource);
        }
        holder.tvMaDonHang_donhang.setText(String.valueOf(list.get(position).getHoaDon_id()));
        holder.tvTenSanPham_donhang.setText(list.get(position).getTenSanPham());
        holder.tvSoLuongSanPham_donhang.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.tvGiaSanPham_donhang.setText(String.valueOf(list.get(position).getGiaSanPham()));
        holder.tvThoiGianDatHang_donhang.setText(list.get(position).getNgayMua());
        holder.tvDiaChiGiaoHang_donhang.setText(list.get(position).getDiaChi());
        switch (list.get(holder.getAdapterPosition()).getTrangThaiDonHang()) {
            case 0:
                holder.tvTrangThaiDonHang_donhang.setText("Chờ xác nhận");
                holder.tvHuyDon_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn hủy đơn hàng này?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Thay đổi trạng thái đơn háng
                                HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context.getApplicationContext());
                                int newTrangThai = 4; //Hủy đơn hàng
                                int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                                int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                                int slSpCuaDH = list.get(holder.getAdapterPosition()).getSoLuong();
                                boolean check = hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThai, hoaDon_id, sanPham_id);
                                if (check) {
                                    list.clear();
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
                                    int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
                                    list = hoaDonChiTietDAO.getDonHangByHDCT(0, nguoiDung_id);

                                    //Thay đổi sl của sp khi ấn hủy
                                    SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
                                    SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
                                    sanPham.setSoLuongConLai(Integer.parseInt(String.valueOf(((sanPham.getSoLuongConLai())+(slSpCuaDH)))));
                                    sanPhamDAO.soLuongConLai(sanPham);
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
                break;
            case 1:
                holder.tvTrangThaiDonHang_donhang.setText("Đã xác nhận");
                holder.tvHuyDon_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn hủy đơn hàng này?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Thay đổi trạng thái đơn háng
                                HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context.getApplicationContext());
                                int newTrangThai = 4; //Hủy đơn hàng
                                int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                                int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                                int slSpCuaDH = list.get(holder.getAdapterPosition()).getSoLuong();
                                boolean check = hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThai, hoaDon_id, sanPham_id);
                                if (check) {
                                    list.clear();
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
                                    int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
                                    list = hoaDonChiTietDAO.getDonHangByHDCT(0, nguoiDung_id);

                                    //Thay đổi sl của sp khi ấn hủy
                                    SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
                                    SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
                                    sanPham.setSoLuongConLai(Integer.parseInt(String.valueOf(((sanPham.getSoLuongConLai())+(slSpCuaDH)))));
                                    sanPhamDAO.soLuongConLai(sanPham);
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
                break;
            case 2:
                holder.tvTrangThaiDonHang_donhang.setText("Đang giao");
                holder.tvHuyDon_donhang.setVisibility(View.GONE);
                break;
            case 3:
                holder.tvTrangThaiDonHang_donhang.setText("Đã giao");
                if (list.get(holder.getAdapterPosition()).getTrangThaiThanhToan() == 1) {
                    holder.tvHuyDon_donhang.setVisibility(View.GONE);
                } else {
                    holder.tvHuyDon_donhang.setText("Xác nhận nhận hàng");
                    holder.tvHuyDon_donhang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context.getApplicationContext());
                            int newTrangThaiThanhToan = 1; //Xác nhận đơn hàng
                            int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                            int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                            boolean check = hoaDonChiTietDAO.thayDoiTrangThaiThanhToan(newTrangThaiThanhToan, hoaDon_id, sanPham_id);
                            if (check) {
                                list.clear();
                                SharedPreferences sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
                                int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
                                list = hoaDonChiTietDAO.getDonHangByHDCT(3, nguoiDung_id);
                                Toast.makeText(context, "Đã xác nhận nhận hàng", Toast.LENGTH_SHORT).show();
                                holder.tvHuyDon_donhang.setVisibility(View.GONE);
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
                break;
        }
        holder.tvTongTien_donhang.setText(String.valueOf(list.get(position).getTongTien()) + " vnđ");
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhSanPham_donhang;
        TextView tvMaDonHang_donhang, tvTenSanPham_donhang, tvSoLuongSanPham_donhang,
                tvGiaSanPham_donhang, tvThoiGianDatHang_donhang, tvDiaChiGiaoHang_donhang,
                tvTrangThaiDonHang_donhang, tvHuyDon_donhang, tvTongTien_donhang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhSanPham_donhang = itemView.findViewById(R.id.imgAnhSanPham_donhang);
            tvMaDonHang_donhang = itemView.findViewById(R.id.tvMaDonHang_donhang);
            tvTenSanPham_donhang = itemView.findViewById(R.id.tvTenSanPham_donhang);
            tvSoLuongSanPham_donhang = itemView.findViewById(R.id.tvSoLuongSanPham_donhang);
            tvGiaSanPham_donhang = itemView.findViewById(R.id.tvGiaSanPham_donhang);
            tvThoiGianDatHang_donhang = itemView.findViewById(R.id.tvThoiGianDatHang_donhang);
            tvDiaChiGiaoHang_donhang = itemView.findViewById(R.id.tvDiaChiGiaoHang_donhang);
            tvTrangThaiDonHang_donhang = itemView.findViewById(R.id.tvTrangThaiDonHang_donhang);
            tvHuyDon_donhang = itemView.findViewById(R.id.tvHuyDon_donhang);
            tvTongTien_donhang = itemView.findViewById(R.id.tvTongTien_donhang);
        }
    }
}

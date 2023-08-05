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
import java.util.Calendar;
import java.util.SplittableRandom;

import md18202.nhom2.duan1application.DAO.HoaDonChiTietDAO;
import md18202.nhom2.duan1application.DAO.ThongBaoDAO;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.Models.ThongBao;
import md18202.nhom2.duan1application.R;

public class QLDonHangAdapter extends RecyclerView.Adapter<QLDonHangAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<HoaDonChiTiet> list;

    public QLDonHangAdapter(Context context, ArrayList<HoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
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
        holder.tvMaDonHang_ql_donhang.setText(String.valueOf(list.get(position).getHoaDon_id()));
        holder.tvTenSanPham_ql_donhang.setText(list.get(position).getTenSanPham());
        holder.tvSoLuongSanPham_ql_donhang.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.tvGiaSanPham_ql_donhang.setText(String.valueOf(list.get(position).getGiaSanPham()));
        holder.tvThoiGianDatHang_ql_donhang.setText(list.get(position).getNgayMua());
        holder.tvDiaChiGiaoHang_ql_donhang.setText(list.get(position).getDiaChi());
        holder.tvTongTien_ql_donhang.setText(list.get(position).getTongTien() + " vnđ");
        switch (list.get(holder.getAdapterPosition()).getTrangThaiDonHang()) {
            case 0:
                holder.tvTrangThaiDonHang_ql_donhang.setText("Chờ xác nhận");
                holder.tvDongY_ql_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
                        int newTrangThai = 1; //Cập nhật thành đã xác nhận
                        int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                        int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                        hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThai, hoaDon_id, sanPham_id);

                        //Them thong bao
                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
                        int nguoiDung_id = list.get(holder.getAdapterPosition()).getNguoiDung_id();
                        String tieuDe = "Thông báo đơn hàng";
                        String noiDung = "Đơn hàng của bạn đã được xác nhận!";
                        String thoiGian = getTimeNow();
                        int loaiThongBao = 0;
                        int isRead = 0;
                        String anhSanPham = list.get(holder.getAdapterPosition()).getAnhSanPham();
                        ThongBao thongBao = new ThongBao(nguoiDung_id, sanPham_id, tieuDe, noiDung, thoiGian, loaiThongBao, isRead, anhSanPham);
                        boolean check = thongBaoDAO.themThongBao(thongBao);
                        Toast.makeText(context, check + "", Toast.LENGTH_SHORT).show();
                        //reload Data
                        reloadData(0);
                    }
                });
                break;
            case 1:
                holder.tvTrangThaiDonHang_ql_donhang.setText("Đã xác nhận");
                holder.tvDongY_ql_donhang.setText("Tiếp theo");
                holder.tvDongY_ql_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
                        int newTrangThai = 2;
                        int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                        int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                        hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThai, hoaDon_id, sanPham_id);

                        //Them thong bao
                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
                        int nguoiDung_id = list.get(holder.getAdapterPosition()).getNguoiDung_id();
                        Toast.makeText(context, nguoiDung_id + "", Toast.LENGTH_SHORT).show();
                        String tieuDe = "Thông báo đơn hàng";
                        String noiDung = "Bạn có đơn hàng đang giao";
                        String thoiGian = getTimeNow();
                        int loaiThongBao = 1;
                        int isRead = 0;
                        String anhSanPham = list.get(holder.getAdapterPosition()).getAnhSanPham();
                        ThongBao thongBao = new ThongBao(nguoiDung_id, sanPham_id, tieuDe, noiDung, thoiGian, loaiThongBao, isRead, anhSanPham);
                        thongBaoDAO.themThongBao(thongBao);

                        //Load lai data
                        reloadData(1);
                    }
                });
                break;
            case 2:
                holder.tvTrangThaiDonHang_ql_donhang.setText("Đang giao");
                holder.tvDongY_ql_donhang.setText("Tiếp theo");
                holder.tvDongY_ql_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
                        int newTrangThai = 3; //Cập nhật thành đã xác nhận
                        int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                        int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                        hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThai, hoaDon_id, sanPham_id);

                        //Them thong bao
                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
                        int nguoiDung_id = list.get(holder.getAdapterPosition()).getNguoiDung_id();
                        Toast.makeText(context, nguoiDung_id + "", Toast.LENGTH_SHORT).show();
                        String tieuDe = "Thông báo đơn hàng";
                        String noiDung = "Vui lòng xác nhận đã nhận hàng";
                        String thoiGian = getTimeNow();
                        int loaiThongBao = 2;
                        int isRead = 0;
                        String anhSanPham = list.get(holder.getAdapterPosition()).getAnhSanPham();
                        ThongBao thongBao = new ThongBao(nguoiDung_id, sanPham_id, tieuDe, noiDung, thoiGian, loaiThongBao, isRead, anhSanPham);
                        thongBaoDAO.themThongBao(thongBao);

                        //load lại data
                        reloadData(2);
                    }
                });
                break;
            case 3:
                holder.tvTrangThaiDonHang_ql_donhang.setText("Đã giao");
                if (list.get(holder.getAdapterPosition()).getTrangThaiThanhToan() == 1) {
                    holder.tvDongY_ql_donhang.setVisibility(View.GONE);
                } else {
                    holder.tvDongY_ql_donhang.setText("Xác nhận thanh toán");
                    holder.tvDongY_ql_donhang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Xác nhận thanh toán cho đơn hàng này?");
                            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context.getApplicationContext());
                                    int newTrangThaiThanhToan = 1;
                                    int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                                    int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                                    boolean check = hoaDonChiTietDAO.thayDoiTrangThaiDonHang(newTrangThaiThanhToan, hoaDon_id, sanPham_id);
                                    if (check) {
                                        Toast.makeText(context, "Đơn hàng đã được xác nhận thanh toán", Toast.LENGTH_SHORT).show();
                                        holder.tvDongY_ql_donhang.setVisibility(View.GONE);
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
                }
                break;
            case 4:
                holder.tvTrangThaiDonHang_ql_donhang.setText("Đã húy");
                holder.tvDongY_ql_donhang.setText("Xóa");
                holder.tvDongY_ql_donhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
                        int hoaDon_id = list.get(holder.getAdapterPosition()).getHoaDon_id();
                        int sanPham_id = list.get(holder.getAdapterPosition()).getSanPham_id();
                        boolean check = hoaDonChiTietDAO.xoaDonHang(hoaDon_id, sanPham_id);
                        if (check) {
                            Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                            reloadData(4);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

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

    public void reloadData(int trangThaiDonHang) {
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        list.clear();
        list = hoaDonChiTietDAO.getDonHangByHDCTForAdmin(trangThaiDonHang);
        notifyDataSetChanged();
    }

    public String getTimeNow() {
        Calendar calendar = Calendar.getInstance();

        // Lấy thông tin giờ, phút, ngày, tháng, năm
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // Lấy giờ (24 giờ)
        int minute = calendar.get(Calendar.MINUTE); // Lấy phút
        int day = calendar.get(Calendar.DAY_OF_MONTH); // Lấy ngày
        int month = calendar.get(Calendar.MONTH) + 1; // Lấy tháng (tháng bắt đầu từ 0 nên cần +1)
        int year = calendar.get(Calendar.YEAR); // Lấy năm

        // Tạo chuỗi String từ thông tin giờ và ngày
        String currentDateTime = "Ngày " + day + "/" + month + "/" + year + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
        return currentDateTime;
    }
}

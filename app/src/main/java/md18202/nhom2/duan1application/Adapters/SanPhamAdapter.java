package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHover> {
    private Context context;
    private ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new MyViewHover(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHover holder, int position) {
        holder.tvID.setText("Loại: " + String.valueOf(list.get(position).getTenloaisanpham()));
        holder.tvTen.setText("Tên Sản Phẩm: " + list.get(position).getTenSanPham());
        holder.tvGia.setText("Giá Tiền: " + String.valueOf(list.get(position).getGiaSanPham()));
        holder.tvMota.setText("Mô Tă Sản Phẩm: " + list.get(position).getMoTa());
        holder.tvSoLuong.setText("Số Lượng Còn: " + String.valueOf(list.get(position).getSoLuongConLai()));
//        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.imgSanPham);
        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgSanPham);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHover extends RecyclerView.ViewHolder {
        TextView tvID, tvTen, tvGia, tvMota, tvSoLuong;
        ImageView imgSanPham;

        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.idLoaiSanPham);
            tvTen = itemView.findViewById(R.id.idTenSanPham);
            tvGia = itemView.findViewById(R.id.idGiaSanPham);
            tvMota = itemView.findViewById(R.id.idMoTaSanPham);
            tvSoLuong = itemView.findViewById(R.id.idSoLuongSanpham);
            imgSanPham = itemView.findViewById(R.id.imgSanPhamADM);

            // sửa thông tin
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_sua_sanpham, null);
                    builder.setView(view);
                    EditText edTen = view.findViewById(R.id.edSuaTenSP);
                    EditText edGia = view.findViewById(R.id.edSuaGiaSP);
                    EditText edMota = view.findViewById(R.id.edSuaMoTaSP);
                    EditText edSoLuong = view.findViewById(R.id.edSuaSoluongSP);
                    SanPham sanPham = list.get(getLayoutPosition());

                    edGia.setText(String.valueOf(sanPham.getGiaSanPham()));
                    edTen.setText(sanPham.getTenSanPham());
                    edMota.setText(sanPham.getMoTa());
                    edSoLuong.setText(String.valueOf(sanPham.getSoLuongConLai()));
                    builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    }).setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String ten = edTen.getText().toString().trim();
                            String gia = edGia.getText().toString().trim();
                            String mota = edMota.getText().toString().trim();
                            String soluong = edSoLuong.getText().toString().trim();


                            boolean check = ten.isEmpty() || gia.isEmpty() || mota.isEmpty() || soluong.isEmpty();
                            if (check) {
                                Toast.makeText(context, "Không Được Bỏ Chống", Toast.LENGTH_SHORT).show();
                            } else {
                                int soluongconai = Integer.parseInt(soluong);
                                int giaban = Integer.parseInt(gia);
                                sanPhamDAO = new SanPhamDAO(context);


                                sanPham.setTenSanPham(ten);
                                sanPham.setGiaSanPham(giaban);
                                sanPham.setMoTa(mota);
                                sanPham.setSoLuongConLai(soluongconai);
                                if (sanPhamDAO.SuaSanPham(sanPham) > 0) {
                                    Toast.makeText(context, "Sủa Thành Công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list = sanPhamDAO.getDsSanPham();
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                    });

                    Dialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }
}

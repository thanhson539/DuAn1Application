package md18202.nhom2.duan1application.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.NguoiDungDAO;

import md18202.nhom2.duan1application.Models.NguoiDung;
import md18202.nhom2.duan1application.R;


public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.MyViewHover> {

    private Context context;
    private ArrayList<NguoiDung> list;
    private NguoiDungDAO nguoidungdao;

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NguoiDungAdapter.MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoi_dung, parent,false);
        return new MyViewHover(view);
    }



    @Override
    public void onBindViewHolder(@NonNull NguoiDungAdapter.MyViewHover holder, int position) {
        if (list.get(position).getLoaiTaiKhoan() == 1){
            holder.txtLoaiNguoiDung.setText("Vai Trò : Admin");
        }else {holder.txtLoaiNguoiDung.setText("Vai Trò : Người Dùng");}

        holder.txtTenNguoiDung.setText("Họ Và Tên : "+ list.get(position).getHoTen());
        holder.txtSDTNguoiDung.setText("Số Điện Thoại : "+ list.get(position).getSoDienThoai());
        holder.txtEmailNguoiDung.setText("Email : " + list.get(position).getEmail());
        if (list.get(position).getLoaiTaiKhoan() == 1){
            holder.txtLoaiNguoiDung.setText("Trạng Thái :  Đã Xóa");
        }else {holder.txtLoaiNguoiDung.setText("Trạng Thái :  Hoạt Động");}

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHover extends RecyclerView.ViewHolder {

        TextView txtLoaiNguoiDung,txtTenNguoiDung,txtSDTNguoiDung,txtEmailNguoiDung,txtIsXoaMem;
        ImageView imgNguoiDung;
        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            txtLoaiNguoiDung = itemView.findViewById(R.id.idLoaiNguoiDung);
            txtTenNguoiDung = itemView.findViewById(R.id.idTenNguoiDung);
            txtSDTNguoiDung = itemView.findViewById(R.id.idSDTNguoiDung);
            txtEmailNguoiDung = itemView.findViewById(R.id.idEmailNguoiDung);
            txtIsXoaMem = itemView.findViewById(R.id.idIsXoaMem);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowDialogSuaTTNguoiDung();
                }
            });

        }

        private void ShowDialogSuaTTNguoiDung() {
        }
    }
}

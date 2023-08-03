package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.ThongBaoDAO;
import md18202.nhom2.duan1application.Fragments.childFrag_of_DonHangFrag.DaXacNhan_Fragment;
import md18202.nhom2.duan1application.Models.ThongBao;
import md18202.nhom2.duan1application.R;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ThongBao> list;

    //    private AdapterView.OnItemClickListener listener;
    public interface onItemClickSelected {
        void onItemClick(int position);
    }

    private onItemClickSelected mListener;

    public void setOnItemClickSelected(onItemClickSelected listener) {
        this.mListener = listener;
    }

    public ThongBaoAdapter(Context context, ArrayList<ThongBao> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thong_bao, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imgSrc = list.get(position).getAnhSanPham();
        boolean isUri = imgSrc.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgSrc)).into(holder.imgSanPham_itemThongBao);
        } else {
            int idResoure = context.getResources().getIdentifier(imgSrc, "drawable", context.getPackageName());
            holder.imgSanPham_itemThongBao.setImageResource(idResoure);
        }
        holder.tieuDe_itemThongBao.setText(list.get(holder.getAdapterPosition()).getTieuDe());
        holder.noiDung_itemThongBao.setText(list.get(holder.getAdapterPosition()).getNoiDung());
        holder.thoiGian_itemThongBao.setText(list.get(holder.getAdapterPosition()).getThoiGian());
        int isRead = list.get(position).getIsRead();
        if (isRead == 1) {
            holder.linear_itemThongBao.setBackgroundColor(Color.parseColor("#ECE1E0"));
        } else {
            holder.linear_itemThongBao.setBackgroundColor(Color.parseColor("#E2BCB7"));
        }
        holder.linear_itemThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linear_itemThongBao.setBackgroundColor(Color.parseColor("#ECE1E0"));
                ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
                boolean check = thongBaoDAO.thayDoiTrangThaiIsRead(list.get(holder.getAdapterPosition()).getThongBao_id());
                if (check) {
                    loadData();
                }
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
                //Code chức năng chuyển fragment khi ấn vài itemThongBao
            }
        });

//        //Chưa làm được phần chuyển tablayout DonHang theo trạng loại thông báo tương ứng
//        final int loaiThongBao = list.get(position).getLoaiThongBao();
//        switch (loaiThongBao) {
//            case 0:
//                holder.linear_itemThongBao.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        holder.linear_itemThongBao.setBackgroundColor(Color.parseColor("#ECE1E0"));
//                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
//                        boolean check = thongBaoDAO.thayDoiTrangThaiIsRead(list.get(holder.getAdapterPosition()).getThongBao_id());
//                        if (check) {
//                            loadData();
//                        }
//                        if (mListener != null) {
//                            mListener.onItemClick(holder.getAdapterPosition());
//                        }
//                        //Code chức năng chuyển fragment khi ấn vài itemThongBao
//                    }
//                });
//                break;
//            case 1:
//                holder.linear_itemThongBao.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        holder.linear_itemThongBao.setBackgroundColor(Color.parseColor("#ECE1E0"));
//                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context.getApplicationContext());
//                        boolean check = thongBaoDAO.thayDoiTrangThaiIsRead(list.get(holder.getAdapterPosition()).getThongBao_id());
//                        loadData();
//                        if (mListener != null) {
//                            mListener.onItemClick(holder.getAdapterPosition());
//                        }
//                        //Code chức năng chuyển fragment khi ấn vài itemThongBao
//                    }
//                });
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linear_itemThongBao;
        private ImageView imgSanPham_itemThongBao;
        private TextView tieuDe_itemThongBao, noiDung_itemThongBao, thoiGian_itemThongBao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_itemThongBao = itemView.findViewById(R.id.linear_itemThongBao);
            imgSanPham_itemThongBao = itemView.findViewById(R.id.imgSanPham_itemThongBao);
            tieuDe_itemThongBao = itemView.findViewById(R.id.tieuDe_itemThongBao);
            noiDung_itemThongBao = itemView.findViewById(R.id.noiDung_itemThongBao);
            thoiGian_itemThongBao = itemView.findViewById(R.id.thoiGian_itemThongBao);
        }
    }

    public void loadData() {
        list.clear();
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        list = thongBaoDAO.getDsThongBaoByNguoiDung_id(nguoiDung_id);
        notifyDataSetChanged();
    }
}

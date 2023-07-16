package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.R;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.ViewHolder> {
    private List<BinhLuan> list;
    private Context context;

    public BinhLuanAdapter(List<BinhLuan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BinhLuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_binh_luan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.ViewHolder holder, int position) {
        holder.tvNguoi_dung.setText(list.get(position).getNguoiDung());
        holder.tvNoi_dung.setText(list.get(position).getNoiDung());
        holder.tvThoi_gian.setText(list.get(position).getThoiGian());
        holder.linear_Binh_luan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                optionDialog();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNguoi_dung, tvNoi_dung, tvThoi_gian;
        LinearLayout linear_Binh_luan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNguoi_dung = itemView.findViewById(R.id.tvNguoi_dung);
            tvNoi_dung = itemView.findViewById(R.id.tvNoi_dung);
            tvThoi_gian = itemView.findViewById(R.id.tvThoi_gian);
            linear_Binh_luan = itemView.findViewById(R.id.linear_Binh_luan);
        }
    }

    public void optionDialog(){
        // Tạo đối tượng AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Thiết lập tiêu đề cho hộp thoại
        builder.setTitle("Lựa chọn");

        // Thiết lập danh sách các lựa chọn
        String[] options = {"Xóa", "Sửa"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Xử lý khi người dùng chọn "Xóa"
                        Toast.makeText(context, "Xoa", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // Xử lý khi người dùng chọn "Sửa"
                        Toast.makeText(context, "Sua", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

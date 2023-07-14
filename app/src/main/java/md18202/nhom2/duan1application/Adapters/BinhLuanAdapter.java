package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_binh_luan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.ViewHolder holder, int position) {
        holder.tvNguoi_dung.setText(list.get(position).getNguoiDung());
        holder.tvNoi_dung.setText(list.get(position).getNoiDung());
        holder.tvThoi_gian.setText(list.get(position).getThoiGian());
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNguoi_dung, tvNoi_dung, tvThoi_gian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNguoi_dung = itemView.findViewById(R.id.tvNguoi_dung);
            tvNoi_dung = itemView.findViewById(R.id.tvNoi_dung);
            tvThoi_gian = itemView.findViewById(R.id.tvThoi_gian);
        }
    }
}

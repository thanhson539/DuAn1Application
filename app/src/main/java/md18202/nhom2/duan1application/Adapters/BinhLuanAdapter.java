package md18202.nhom2.duan1application.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.R;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.ViewHolder> {
    private List<BinhLuan> list;
    private Context context;
    BinhLuanDAO binhLuanDAO;
    SharedPreferences sharedPreferences;

    public BinhLuanAdapter(List<BinhLuan> list, Context context) {
        this.list = list;
        this.context = context;
        binhLuanDAO = new BinhLuanDAO(context);
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
                sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
                int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
                if (nguoiDung_id == list.get(holder.getAdapterPosition()).getNguoiDung_id()){
                    optionDialog(holder.getAdapterPosition());
                }else{
                    copyPaste(holder.tvNoi_dung);
                }
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

    public void optionDialog(int viTri){
        // Tạo đối tượng AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Thiết lập tiêu đề cho hộp thoại
        builder.setTitle("Lựa chọn");

        // Thiết lập danh sách các lựa chọn
        String[] options = {"Xóa", "Sửa"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BinhLuan binhLuan = list.get(viTri);
                switch (which) {
                    case 0:
                        // Xử lý khi người dùng chọn "Xóa"
                        if (binhLuanDAO.deleteBinhLuan(String.valueOf(list.get(viTri).getBinhLuan_id())) > 0){
                            getDsBinhLuan(binhLuan.getSanPham_id());
                            Toast.makeText(context, "Da xoa", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                    case 1:
                        // Xử lý khi người dùng chọn "Sửa"
                        Dialog dialog1 = new Dialog(context);
                        dialog1.setContentView(R.layout.dialog_binh_luan);
                        EditText edBinhLuan = dialog1.findViewById(R.id.edBinh_luan);
                        ImageView imgBinhLuan = dialog1.findViewById(R.id.imgBinh_luan);
                        edBinhLuan.setText(binhLuan.getNoiDung());
                        edBinhLuan.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                // Lấy nội dung từ Clipboard
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                builder1.setTitle("Dan")
                                        .setPositiveButton("Dan", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                        if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                                            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                                            CharSequence text = item.getText();

                                            // Đặt nội dung vào EditText
                                            edBinhLuan.setText(text);
                                        }
                                        Toast.makeText(context, "Da dan", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog alertDialog = builder1.create();
                                alertDialog.show();

                                return true;
                            }
                        });
                        imgBinhLuan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (edBinhLuan.getText().length() == 0){
                                    getDsBinhLuan(binhLuan.getSanPham_id());
                                    Toast.makeText(context, "Ban nhap trong roi", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd-MM-yyyy");
                                String currentTime = format.format(calendar.getTime());
                                binhLuan.setThoiGian(currentTime);
                                binhLuan.setNoiDung(edBinhLuan.getText().toString());
                                if (binhLuanDAO.upDateBinhLuan(binhLuan) > 0){
                                    getDsBinhLuan(binhLuan.getSanPham_id());
                                    Toast.makeText(context, "Da chinh sua binh luan", Toast.LENGTH_SHORT).show();
                                    dialog1.cancel();
                                }
                            }
                        });
                        dialog1.show();
                        break;
                }
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getDsBinhLuan(int sanPham_id){
        list.clear();
        list = binhLuanDAO.getDsBinhLuan(sanPham_id);
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    public void copyPaste(TextView tvCopy){
        // Tạo đối tượng AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Thiết lập tiêu đề cho hộp thoại
        builder.setTitle("Lựa chọn");

        // Thiết lập danh sách các lựa chọn
        String[] options = {"Sao chep"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Xử lý khi người dùng chọn "Sao chep"
                        // Lấy nội dung của TextView
                        CharSequence text = tvCopy.getText();

                        // Sao chép nội dung vào Clipboard
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", text);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(context, "Da sao chep", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

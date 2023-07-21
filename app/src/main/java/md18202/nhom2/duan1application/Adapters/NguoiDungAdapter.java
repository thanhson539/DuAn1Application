package md18202.nhom2.duan1application.Adapters;

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

import md18202.nhom2.duan1application.DAO.NguoiDungDAO;

import md18202.nhom2.duan1application.Models.NguoiDung;
import md18202.nhom2.duan1application.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;



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
        String imgSrc = list.get(position).getImgSrc();
        int resourceId = context.getResources().getIdentifier(imgSrc, "drawable", context.getPackageName());




        Picasso.get()
                    .load(resourceId) // Thay thế bằng đường dẫn hoặc resource ID của ảnh đại diện
                    .transform(new MyViewHover.CircleTransform() )
                    .into(holder.imgNguoiDung);


        holder.txtTenNguoiDung.setText("Họ Và Tên : "+ list.get(position).getHoTen());
        holder.txtSDTNguoiDung.setText("Số Điện Thoại : "+ list.get(position).getSoDienThoai());
        holder.txtEmailNguoiDung.setText("Email : " + list.get(position).getEmail());
        if (list.get(position).getIsXoaMem() == 1){
            holder.txtIsXoaMem.setText("Trạng Thái :  Đã Xóa");
        }else {holder.txtIsXoaMem.setText("Trạng Thái :  Hoạt Động");}

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public static class MyViewHover extends RecyclerView.ViewHolder {

        TextView txtLoaiNguoiDung,txtTenNguoiDung,txtSDTNguoiDung,txtEmailNguoiDung,txtIsXoaMem;
        ImageView imgNguoiDung;
        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            txtLoaiNguoiDung = itemView.findViewById(R.id.idLoaiNguoiDung);
            txtTenNguoiDung = itemView.findViewById(R.id.idTenNguoiDung);
            txtSDTNguoiDung = itemView.findViewById(R.id.idSDTNguoiDung);
            txtEmailNguoiDung = itemView.findViewById(R.id.idEmailNguoiDung);
            txtIsXoaMem = itemView.findViewById(R.id.idIsXoaMem);
            imgNguoiDung = itemView.findViewById(R.id.imgNguoiDung);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowDialogSuaTTNguoiDung();
                }
            });

        }

        private void ShowDialogSuaTTNguoiDung() {
        }
        private static class CircleTransform implements Transformation {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }
    }
}

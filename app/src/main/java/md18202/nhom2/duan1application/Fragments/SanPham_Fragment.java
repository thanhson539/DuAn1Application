package md18202.nhom2.duan1application.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import md18202.nhom2.duan1application.Adapters.SanPhamAdapter;
import md18202.nhom2.duan1application.DAO.LoaiSanPhamDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Models.LoaiSanPham;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.R;


public class SanPham_Fragment extends Fragment {


    private RecyclerView recyclerView;
    private SanPhamDAO sanPhamDAO;
    private FloatingActionButton floatbtnSanpham;
    private static final int REQUEST_CODE_GALLERY_PERMISSION = 100;
    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    // Define separate request codes for "Thêm sản phẩm" and "Sửa sản phẩm" dialogs


    private Uri selectedImageUri = null; // Khởi tạo selectedImageUri bằng null hoặc một giá trị mặc định khác tùy theo yêu cầu của bạn.
    private Uri selectedImageUriSP = null;
    private ImageView imgThemAnh;
    ImageView imgSuaSP;
    Spinner spnTen;

    private String selectedImagePath = null;





    public SanPham_Fragment() {
        // Required empty public constructor
    }


    public static SanPham_Fragment newInstance() {
        SanPham_Fragment fragment = new SanPham_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Kiểm tra quyền truy cập bộ nhớ ngoài


        return inflater.inflate(R.layout.fragment_san_pham_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rycView_SanPham);
        floatbtnSanpham = view.findViewById(R.id.floatbtnSanPham);

        // Lấy ảnh từ bộ nhớ trong và hiển thị lên ImageView khi ứng dụng chạy lại

        floatbtnSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFloatButton();
            }
        });

        sanPhamDAO = new SanPhamDAO(getContext());
        loatDate(recyclerView);



    }






    public void showDialogFloatButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_floatbutton_sanpham, null);
        builder.setView(view);
        Spinner spnLoaiSanPham = view.findViewById(R.id.spnSanPham);
        imgThemAnh = view.findViewById(R.id.edSanPhamThemAnh);
        EditText edThemTen = view.findViewById(R.id.edSanPhamThemTenSP);
        EditText edThemGia = view.findViewById(R.id.edSanPhamThemGiaSP);
        EditText edThemMoTa = view.findViewById(R.id.edSanPhamMoTaLoaiSP);
        EditText edThemSoLuong = view.findViewById(R.id.edSanPhamThemSoLuongSP);

        imgThemAnh.setFocusable(false);

        getDataLoaiSanPham(spnLoaiSanPham);



        // mới
//        selectedImagePath = getSavedImagePathFromSharedPreferences();

        // Load the image from the internal storage if a path is available
//        if (selectedImagePath != null) {
//            selectedImageUri = Uri.parse(selectedImagePath);
//            imgThemAnh.setImageURI(selectedImageUri);
//        }


        imgThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    // yêu cầu cấp quyền cho ứng dụng
                    requestGalleryPermission();

                    //  ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY_PERMISSION);
                } else {
                    // quyền đã dc cấp mở thu viện hình ảnh
                    openGallery();
                    //openImagePicker();
                }
            }
        });

        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Thêm Mới", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ten = edThemTen.getText().toString().trim();
                String themgia = edThemGia.getText().toString().trim();
                String edMota = edThemMoTa.getText().toString().trim();
                String soluong = edThemSoLuong.getText().toString().trim();
                if (selectedImageUri != null) {
                    String duongDanAnh = selectedImageUri.toString();
                    imgThemAnh.setImageURI(selectedImageUri);
                    Integer giatien = Integer.parseInt(themgia);
                    Integer soLuong = Integer.parseInt(soluong);
                    HashMap<String, Object> hsTV = (HashMap<String, Object>) spnLoaiSanPham.getSelectedItem();
                    int loaisanpham_id = (int) hsTV.get("loaiSanPham_id");
                    SanPhamDAO phamDAO = new SanPhamDAO(getContext());
                    SanPham sanPham = new SanPham();
                    sanPham.setMoTa(edMota);
                    sanPham.setGiaSanPham(giatien);
                    sanPham.setSoLuongConLai(soLuong);
                    sanPham.setTenSanPham(ten);
                    sanPham.setLoaiSanPham_id(loaisanpham_id);
                    sanPham.setAnhSanPham(duongDanAnh);

                    sanPham.setIsYeuThich(0);
                    if (phamDAO.insertSanPham(sanPham) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                        loatDate(recyclerView);
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(getContext(), "Bạn chưa chọn hình ảnh", Toast.LENGTH_SHORT).show();
                }


            }





        });

        Dialog dialog = builder.create();
        dialog.show();
    }



    private void requestGalleryPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

        } else {
            // Yêu cầu quyền truy cập vào thư viện
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY_PERMISSION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, mở thư viện để chọn hình ảnh
                openGallery();
            } else {
                // Người dùng từ chối cấp quyền, bạn có thể xử lý tại đây (ví dụ: thông báo cho người dùng).
                // Toast.makeText(getContext(), "Bạn cần cấp quyền để truy cập vào thư viện", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == getActivity().RESULT_OK) {
            // Xử lý hình ảnh đã chọn tại đây.


            selectedImageUri = data.getData();
            if (selectedImageUriSP != null && imgThemAnh != null) {
                imgThemAnh.setImageURI(selectedImageUri);
            }
        }

        selectedImageUriSP = data.getData();
        if (selectedImageUriSP != null && imgSuaSP != null) {
            imgSuaSP.setImageURI(selectedImageUriSP);
        }


    }








    private void saveImageToExternalStorage(Uri imageUri) {
        try {
            String imageFileName = "my_image.jpg"; // Đặt tên tập tin ảnh tùy ý
            File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), imageFileName);
            InputStream inputStream = getContext().getContentResolver().openInputStream(imageUri);
            OutputStream outputStream = new FileOutputStream(imageFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loatDate(RecyclerView recyclerView) {
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPhamADM();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_sua_sanpham, null);
                builder.setView(view1);
                spnTen = view1.findViewById(R.id.edSuaTenLoaiSP);
                imgSuaSP = view1.findViewById(R.id.imgSuaAnhSP);
                EditText edten = view1.findViewById(R.id.edSuaTenSP);
                EditText edGia = view1.findViewById(R.id.edSuaGiaSP);
                EditText edMota = view1.findViewById(R.id.edSuaMoTaSP);
                EditText edSoLuong = view1.findViewById(R.id.edSuaSoluongSP);
                SanPham sanPham = list.get(position);


                getDataLoaiSanPham(spnTen);
                edten.setText(sanPham.getTenSanPham());
                edGia.setText(String.valueOf(sanPham.getGiaSanPham()));
//                    edTen.setText(sanPham.getTenSanPham());
                edMota.setText(sanPham.getMoTa());
                edSoLuong.setText(String.valueOf(sanPham.getSoLuongConLai()));


                imgSuaSP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                            // yêu cầu cấp quyền cho ứng dụng
                            requestGalleryPermission();

                            //  ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY_PERMISSION);
                        } else {
                            // quyền đã dc cấp mở thu viện hình ảnh
                            openGallery();
                            //openImagePicker();
                        }
                    }
                });


                builder.setNegativeButton("Xoá Mềm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sanPhamDAO = new SanPhamDAO(getContext());

                        int isCheck = sanPhamDAO.xoaMemSP(list.get(position).getSanPham_id());
                        if (isCheck > 0) {
                            Toast.makeText(getContext(), "Xoá Mềm Thành Công", Toast.LENGTH_SHORT).show();
                            sanPham.setXoamen(0);

                        } else {
                            Toast.makeText(getContext(), "Xoá Mền Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String ten = edten.getText().toString().trim();
                        String gia = edGia.getText().toString().trim();
                        String mota = edMota.getText().toString().trim();
                        String soluong = edSoLuong.getText().toString().trim();

                        String duongDanAnhh = selectedImageUriSP.toString();

                        imgSuaSP.setImageURI(selectedImageUri);
//


                        boolean check = ten.isEmpty() || gia.isEmpty() || mota.isEmpty() || soluong.isEmpty();
                        if (check) {
                            Toast.makeText(getContext(), "Không Được Bỏ Chống", Toast.LENGTH_SHORT).show();
                        } else {
                            int soluongconai = Integer.parseInt(soluong);
                            int giaban = Integer.parseInt(gia);
                            sanPhamDAO = new SanPhamDAO(getContext());

                            HashMap<String, Object> hsTV = (HashMap<String, Object>) spnTen.getSelectedItem();
                            int tenLoaiSP = (int) hsTV.get("loaiSanPham_id");
                            sanPham.setAnhSanPham(duongDanAnhh);
                            sanPham.setTenSanPham(ten);
                            sanPham.setGiaSanPham(giaban);
                            sanPham.setMoTa(mota);
                            sanPham.setSoLuongConLai(soluongconai);
                            sanPham.setLoaiSanPham_id(tenLoaiSP);

                            if (sanPhamDAO.SuaSanPham(sanPham) > 0) {
                                Toast.makeText(getContext(), "Sủa Thành Công", Toast.LENGTH_SHORT).show();
                                loatDate(recyclerView);

                            } else {
                                Toast.makeText(getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                });

                Dialog dialog = builder.create();
                dialog.show();


            }
        });


        recyclerView.setAdapter(adapter);
    }


    public void getDataLoaiSanPham(Spinner spnSach) {
        LoaiSanPhamDAO sachDao = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list = sachDao.getDsLoaiSanPham();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSanPham sc : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("loaiSanPham_id", sc.getLoaiSanPham_id());
            hs.put("tenLoai", sc.getTenLoai());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }


}
package md18202.nhom2.duan1application.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

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

    private Uri selectedImageUri = null; // Khởi tạo selectedImageUri bằng null hoặc một giá trị mặc định khác tùy theo yêu cầu của bạn.

   private ImageView imgThemAnh;

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

        return inflater.inflate(R.layout.fragment_san_pham_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rycView_SanPham);
        floatbtnSanpham = view.findViewById(R.id.floatbtnSanPham);

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
                    SanPhamDAO phamDAO   =new SanPhamDAO(getContext());
                    SanPham sanPham  =new SanPham();
                    sanPham.setMoTa(edMota);
                    sanPham.setGiaSanPham(giatien);
                    sanPham.setSoLuongConLai(soLuong);
                    sanPham.setTenSanPham(ten);
                    sanPham.setLoaiSanPham_id(loaisanpham_id);
                    sanPham.setAnhSanPham(duongDanAnh);

                    sanPham.setIsYeuThich(0);
                    if (phamDAO.insertSanPham(sanPham)>0){
                        Toast.makeText(getContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                        loatDate(recyclerView);
                    }else {
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

//            Uri selectedImageUri = data.getData();
            selectedImageUri = data.getData();

            imgThemAnh.setImageURI(selectedImageUri);
            ((SanPhamAdapter) recyclerView.getAdapter()).setSelectedImageUri(selectedImageUri);



        }
    }


    public void loatDate(RecyclerView recyclerView) {
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPhamADM();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list );
        adapter.setActivity(getActivity());

        if (selectedImageUri != null) {
            adapter.setSelectedImageUri(selectedImageUri);
        }
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



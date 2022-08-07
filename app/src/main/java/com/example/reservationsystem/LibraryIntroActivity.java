package com.example.reservationsystem;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class LibraryIntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryintro);

        findViewById(R.id.seatCheckBtn).setOnClickListener(onClickListener);
        findViewById(R.id.seatNumberBtn).setOnClickListener(onClickListener);
        findViewById(R.id.QRBtn).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.seatCheckBtn:
                    break;
                case R.id.seatNumberBtn:
                    Intent seatNumberIntent = new Intent(LibraryIntroActivity.this,LibrarySearchActivity.class);
                    startActivity(seatNumberIntent);
                    break;
                case R.id.QRBtn:
                    CameraPermission();
                    break;
            }
        }
    };

    // 카메라 접근 허용 tedpermission
    public void CameraPermission(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(LibraryIntroActivity.this, "카메라 접근 허용", Toast.LENGTH_SHORT).show();
                Intent intentQR = new Intent(LibraryIntroActivity.this, LibraryQRActivity.class);
                startActivity(intentQR);
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(LibraryIntroActivity.this, "카메라 접근 불허\n", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("접근권한을 허용하지 않을시 이용이 불가합니다\n\n권한 허용을 해주세요 [설정] > [권한]")
                .setPermissions(Manifest.permission.CAMERA)
                .check();
    }

}

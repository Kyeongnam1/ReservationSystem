package com.example.reservationsystem;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class LibraryIntroActivity extends AppCompatActivity {

    //좌석생성 버튼
    Button seatcreate;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryintro);

        findViewById(R.id.seatCheckBtn).setOnClickListener(onClickListener);
        findViewById(R.id.seatNumberBtn).setOnClickListener(onClickListener);
        findViewById(R.id.QRBtn).setOnClickListener(onClickListener);

        // 좌석 임의 생성위한 테스트용
        seatcreate = findViewById(R.id.testbutton);
        findViewById(R.id.testbutton).setOnClickListener(onClickListener);


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
                case R.id.testbutton:
                    createSeat();
                    Toast.makeText(LibraryIntroActivity.this, "좌석생성완료", Toast.LENGTH_SHORT).show();
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

    public void createSeat(){
        for(int i = 1; i<=40; i++) {
            DocumentReference docref = db.collection("1층 제1노트북열람실").document("1F_SEATNO" + i);
            LibrarySeatInfo librarySeatInfo = new LibrarySeatInfo("1F_SEATNO" + i,"이용가능", "");
            docref.set(librarySeatInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });
        }
    }

}
